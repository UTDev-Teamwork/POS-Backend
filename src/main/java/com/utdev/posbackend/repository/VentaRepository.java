package com.utdev.posbackend.repository;

import com.utdev.posbackend.model.Venta;
import com.utdev.posbackend.model.compositePK.VentaPK;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface VentaRepository extends JpaRepository<Venta, VentaPK> {

    Page<Venta> findByFolio(long folio, Pageable pageable);

    Page<Venta> findByFechaVenta(LocalDateTime fecha, Pageable pageable);

}
