package com.letrasypapeles.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "clientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(name = "correo", nullable = false, unique = true)
    private String email;       // Lombok generará getEmail/setEmail

    // —— contraseña ——
    @Column(nullable = false)
    private String contraseña;

    // —— fidelidad ——
    @Column(nullable = false)
    private Integer puntosFidelidad = 0;

    // —— roles ——
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name               = "cliente_roles",
        joinColumns        = @JoinColumn(name = "cliente_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();
}