package com.letrasypapeles.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @PositiveOrZero
    private double precio;

    /* --- Relaciones --- */
    @ManyToOne @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @ManyToOne @JoinColumn(name = "proveedor_id")
    private Proveedor proveedor;

    @OneToMany(mappedBy = "producto")
    private List<Inventario> inventarios = new ArrayList<>();

    @ManyToMany(mappedBy = "productos")
    private List<Pedido> pedidos = new ArrayList<>();

    @OneToMany(mappedBy = "producto")
    private List<Reserva> reservas = new ArrayList<>();
}