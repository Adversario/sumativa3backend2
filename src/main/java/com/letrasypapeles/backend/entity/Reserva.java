package com.letrasypapeles.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fechaRetiro;

    /* Relaciones */
    @ManyToOne @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne @JoinColumn(name = "producto_id")
    private Producto producto;

    @ManyToOne @JoinColumn(name = "sucursal_id")
    private Sucursal sucursal;

    /* -------- nuevo -------- */
    @Enumerated(EnumType.STRING)
    private EstadoReserva estado = EstadoReserva.PENDIENTE;
}