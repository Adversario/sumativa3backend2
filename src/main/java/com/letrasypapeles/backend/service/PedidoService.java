package com.letrasypapeles.backend.service;

import com.letrasypapeles.backend.entity.EstadoPedido;
import com.letrasypapeles.backend.entity.Pedido;
import com.letrasypapeles.backend.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    /* ==== Operaciones CRUD básicas ==== */

    public List<Pedido> obtenerTodos() {
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> obtenerPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    public Pedido guardar(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public void eliminar(Long id) {
        pedidoRepository.deleteById(id);
    }

    /* ==== Búsquedas específicas ==== */

    /** Pedidos de un cliente */
    public List<Pedido> obtenerPorCliente(Long clienteId) {
        return pedidoRepository.findByClienteId(clienteId);
    }

    /** Pedidos por estado — acepta tanto enum como String */
    public List<Pedido> obtenerPorEstado(String estado) {
        EstadoPedido estadoEnum = EstadoPedido.valueOf(estado.toUpperCase());
        return pedidoRepository.findByEstado(estadoEnum);
    }

    public List<Pedido> obtenerPorEstado(EstadoPedido estado) {
        return pedidoRepository.findByEstado(estado);
    }
}