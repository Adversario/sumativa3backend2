package com.letrasypapeles.backend.repository;

import com.letrasypapeles.backend.entity.EstadoReserva;
import com.letrasypapeles.backend.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    List<Reserva> findByClienteId(Long clienteId);

    /* el tipo del parámetro ahora es el enum */
    List<Reserva> findByEstado(EstadoReserva estado);
}