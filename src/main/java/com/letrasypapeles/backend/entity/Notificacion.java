package com.letrasypapeles.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mensaje;
    private boolean leida = false;
    private LocalDateTime fecha = LocalDateTime.now();

    @ManyToOne @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}