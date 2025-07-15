package com.letrasypapeles.backend.repository;

import com.letrasypapeles.backend.entity.Pedido;
import com.letrasypapeles.backend.entity.EstadoPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    /** Buscar por estado (enum) */
    List<Pedido> findByEstado(EstadoPedido estado);

    /** Buscar todos los pedidos de un cliente concreto */
    List<Pedido> findByClienteId(Long clienteId);
}