package com.QF.Almacen_backend.RestControladores;

import com.QF.Almacen_backend.Dto.ProductoDanadoResponseDTO;
import com.QF.Almacen_backend.Dto.ProductosDanadoRequestDTO;
import com.QF.Almacen_backend.Servicios.ProductosDanadosService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/productos-danados")
@CrossOrigin(origins = "http://localhost:5173")
public class ProductoDanadosControlador {
    private final ProductosDanadosService productosDanadosService;

    public ProductoDanadosControlador(ProductosDanadosService productosDanadosService) {
        this.productosDanadosService = productosDanadosService;
    }


    @PostMapping("/Guardar")
    public ResponseEntity<?> guardarReporte(@RequestBody ProductosDanadoRequestDTO request) {
        try {
            productosDanadosService.registrarProductoDanado(request);
            return ResponseEntity.status(HttpStatus.CREATED).body("Reporte de producto dañado registrado y stock actualizado.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/Listar")
    public ResponseEntity<List<ProductoDanadoResponseDTO>> listarReportes() {
        return ResponseEntity.ok(productosDanadosService.listarReportes());
    }

}
