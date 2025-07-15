package com.letrasypapeles.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    private String telefono;
    private String email;

    @OneToMany(mappedBy = "proveedor")
    private List<Producto> productos = new ArrayList<>();
}