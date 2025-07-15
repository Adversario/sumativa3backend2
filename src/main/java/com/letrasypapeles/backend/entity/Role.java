package com.letrasypapeles.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "roles")
@Data                     // getters, setters, toString, equals…
@NoArgsConstructor        // constructor vacío
@AllArgsConstructor       // constructor con todos los campos
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;

    // 👉  constructor «rápido» que tu servicio necesita
    public Role(String nombre) {
        this.nombre = nombre;
    }
}