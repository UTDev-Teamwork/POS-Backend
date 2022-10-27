package com.utdev.posbackend.service;

import com.utdev.posbackend.model.Venta;
import com.utdev.posbackend.model.compositePK.VentaPK;
import com.utdev.posbackend.repository.VentaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Service
public class VentaServiceImpl implements IVentaService{

    private final VentaRepository ventaRepository;

    public VentaServiceImpl(VentaRepository ventaRepository) { this.ventaRepository = ventaRepository; }

    // CREATE & UPDATE
    @Override
    public Venta saveVenta(Venta venta){
        return ventaRepository.save(venta);
    }

    @Override
    public Venta saveVenta(String id, double total, int productos){
        return ventaRepository.save(new Venta(
                new VentaPK(1, id),
                "ClienteReact",
                Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime())),
                LocalDateTime.now(),
                total,
                total,
                0,
                0,
                0,
                "BKPOS",
                false,
                (short) productos,
                0
                )
        );
    }

    // READ
    @Override
    public List<Venta> findAllVentas(){ return ventaRepository.findAll(); }

    @Override
    public Venta findById(VentaPK id) { return ventaRepository.findById(id).orElse(null); }

    @Override
    public Page<Venta> findTopTen(){
        Pageable limit = PageRequest.of(0,10);
        return ventaRepository.findAll(limit);
    }

    @Override
    public Page<Venta> findPaging(Pageable pageable){
        return ventaRepository.findAll(pageable);
    }

    @Override
    public Page<Venta> findByFolio(long folio, Pageable pageable){ return ventaRepository.findByFolio(folio, pageable); }

    @Override
    public Page<Venta> findByFecha(LocalDateTime fecha, Pageable pageable){ return ventaRepository.findByFechaVenta(fecha, pageable); }

    // DELETE
    @Override
    public void deleteVenta(VentaPK ventaPK){ ventaRepository.deleteById(ventaPK); }

}
