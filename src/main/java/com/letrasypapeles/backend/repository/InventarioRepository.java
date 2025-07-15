package com.letrasypapeles.backend.repository;

import com.letrasypapeles.backend.entity.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Long> {

    // ✔️ Spring interpreta "producto.id"
    List<Inventario> findByProductoId(Long productoId);

    // ✔️ Spring interpreta "sucursal.id"
    List<Inventario> findBySucursalId(Long sucursalId);

    // 🔄 Cambiado: stock < umbral
    List<Inventario> findByStockLessThan(Integer umbral);
}