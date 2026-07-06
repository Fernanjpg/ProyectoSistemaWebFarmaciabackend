package com.QF.Almacen_backend.RestControladores;

import com.QF.Almacen_backend.Dto.EntradaResponseDTO;
import com.QF.Almacen_backend.Dto.EntradasRequestDTO;
import com.QF.Almacen_backend.Servicios.EntradasService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entradas")
@CrossOrigin(origins = "http://localhost:5173")
public class EntradasController {
    private final EntradasService entradasService;

    public EntradasController(EntradasService entradasService) {
        this.entradasService = entradasService;
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarEntrada(@RequestBody EntradasRequestDTO request) {
        try {
            entradasService.registrarEntrada(request);
            return ResponseEntity.status(HttpStatus.CREATED).body("Entrada registrada y stock actualizado.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/historial")
    public ResponseEntity<List<EntradaResponseDTO>> obtenerHistorial() {
        return ResponseEntity.ok(entradasService.listarEntradas());
    }
}
