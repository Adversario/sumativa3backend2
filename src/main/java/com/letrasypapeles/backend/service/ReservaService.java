package com.letrasypapeles.backend.service;

import com.letrasypapeles.backend.entity.EstadoReserva;
import com.letrasypapeles.backend.entity.Reserva;
import com.letrasypapeles.backend.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    public List<Reserva> obtenerTodos() {
        return reservaRepository.findAll();
    }

    public Optional<Reserva> obtenerPorId(Long id) {
        return reservaRepository.findById(id);
    }

    public List<Reserva> obtenerPorCliente(Long clienteId) {
        return reservaRepository.findByClienteId(clienteId);
    }

    public List<Reserva> obtenerPorEstado(String estado) {
        /* convierte la cadena a enum (en mayúsculas) */
        EstadoReserva er = EstadoReserva.valueOf(estado.toUpperCase());
        return reservaRepository.findByEstado(er);
    }

    public Reserva guardar(Reserva r) {
        return reservaRepository.save(r);
    }

    public void eliminar(Long id) {
        reservaRepository.deleteById(id);
    }
}