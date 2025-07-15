package com.letrasypapeles.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data                       // getters, setters, toString, equals, hashCode
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Se inicializa con la fecha de creación */
    private LocalDate fecha = LocalDate.now();

    /** Relación muchos‑a‑uno con Cliente */
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    /** Productos que componen el pedido */
    @ManyToMany
    @JoinTable(
        name = "pedidos_productos",
        joinColumns        = @JoinColumn(name = "pedido_id"),
        inverseJoinColumns = @JoinColumn(name = "producto_id")
    )
    private List<Producto> productos = new ArrayList<>();

    /** Estado actual del pedido (nuevo campo) */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoPedido estado = EstadoPedido.PENDIENTE;   // valor por defecto
}