package com.letrasypapeles.backend.security;

import com.letrasypapeles.backend.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;

import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    private UsuarioService usuarioService;

    /* ---------- Beans básicos ---------- */

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(usuarioService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    /* ---------- Seguridad HTTP ---------- */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            /* Desactiva CSRF SOLO en desarrollo o para APIs REST */
            .csrf(csrf -> csrf.disable())

            /* Autorizaciones -------------------------------------------------- */
            .authorizeHttpRequests(auth -> auth
                /* -------------------  Rutas públicas  ------------------------ */
                /* Login / registro propios */
                .requestMatchers("/api/auth/**").permitAll()
                /* Swagger & H2 */
                .requestMatchers(
                    "/swagger-ui/**","/swagger-ui.html",
                    "/v3/api-docs/**","/swagger-resources/**",
                    "/webjars/**",
                    "/h2-console/**"
                ).permitAll()
                /* Endpoint que quieres mostrar sin login */
                .requestMatchers(HttpMethod.GET, "/api/productos/**").permitAll()

                /* -------------------  Rutas con rol  ------------------------- */
                .requestMatchers("/api/admin/**").hasRole("ADMIN")

                /* Cualquier otra petición necesita autenticación */
                .anyRequest().authenticated()
            )

            /* Stateless → tokens o Basic sin sesión */
            .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

            /* HTTP Basic para que Swagger pueda enviar las credenciales */
            .httpBasic(httpBasic -> {})   // Customizer.withDefaults() -> "() -> {}" con λ vacía

            /* Registrar tu proveedor de autenticación */
            .authenticationProvider(authenticationProvider())

            /* H2 necesita permitir frames */
            .headers(headers -> headers.frameOptions().disable());

        return http.build();
    }
}