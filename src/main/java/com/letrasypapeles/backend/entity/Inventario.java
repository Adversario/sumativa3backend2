package com.letrasypapeles.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @PositiveOrZero
    private int stock;

    @ManyToOne @JoinColumn(name = "producto_id")
    private Producto producto;

    @ManyToOne @JoinColumn(name = "sucursal_id")
    private Sucursal sucursal;
}