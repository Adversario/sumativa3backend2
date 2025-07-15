package com.letrasypapeles.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;          // BCrypt

    private boolean enabled = true;

    /* --- Relaciones --- */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuarios_roles",
               joinColumns = @JoinColumn(name = "usuario_id"),
               inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
}