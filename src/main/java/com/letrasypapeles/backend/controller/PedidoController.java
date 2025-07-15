package com.letrasypapeles.backend.controller;

import com.letrasypapeles.backend.entity.EstadoPedido;
import com.letrasypapeles.backend.entity.Pedido;
import com.letrasypapeles.backend.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    /* ---------- lecturas ---------- */

    @GetMapping
    public List<Pedido> obtenerTodos() {
        return pedidoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> obtenerPorId(@PathVariable Long id) {
        return pedidoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cliente/{clienteId}")
    public List<Pedido> obtenerPorCliente(@PathVariable Long clienteId) {
        // 🔴 Aquí cambió el nombre del método
        return pedidoService.obtenerPorCliente(clienteId);
    }

    @GetMapping("/estado/{estado}")
    public List<Pedido> obtenerPorEstado(@PathVariable String estado) {
        return pedidoService.obtenerPorEstado(estado);
    }

    /* ---------- escrituras ---------- */

    @PostMapping
    public ResponseEntity<Pedido> crear(@RequestBody Pedido pedido) {
        Pedido guardado = pedidoService.guardar(pedido);
        return ResponseEntity
                .created(URI.create("/api/pedidos/" + guardado.getId()))
                .body(guardado);
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<Pedido> cambiarEstado(@PathVariable Long id,
                                                @RequestBody EstadoPedido estado) {
        return pedidoService.obtenerPorId(id)
                .map(p -> {
                    p.setEstado(estado);
                    return ResponseEntity.ok(pedidoService.guardar(p));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        pedidoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}