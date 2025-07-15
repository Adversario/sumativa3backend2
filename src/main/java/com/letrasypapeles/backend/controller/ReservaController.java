package com.letrasypapeles.backend.controller;

import com.letrasypapeles.backend.entity.Reserva;
import com.letrasypapeles.backend.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    /* ────────── GET ────────── */

    @GetMapping
    public List<Reserva> obtenerTodos() {
        return reservaService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Optional<Reserva> obtenerPorId(@PathVariable Long id) {
        return reservaService.obtenerPorId(id);
    }

    @GetMapping("/cliente/{clienteId}")
    public List<Reserva> obtenerPorCliente(@PathVariable Long clienteId) {
        return reservaService.obtenerPorCliente(clienteId);
    }

    @GetMapping("/estado/{estado}")
    public List<Reserva> obtenerPorEstado(@PathVariable String estado) {
        return reservaService.obtenerPorEstado(estado);
    }

    /* ────────── POST / PUT ────────── */

    @PostMapping
    public Reserva crear(@RequestBody Reserva reserva) {
        return reservaService.guardar(reserva);
    }

    @PutMapping
    public Reserva actualizar(@RequestBody Reserva reserva) {
        return reservaService.guardar(reserva);
    }

    /* ────────── DELETE ────────── */

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        reservaService.eliminar(id);
    }
}