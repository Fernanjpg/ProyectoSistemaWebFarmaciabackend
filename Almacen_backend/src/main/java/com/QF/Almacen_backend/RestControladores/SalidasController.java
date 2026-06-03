package com.QF.Almacen_backend.RestControladores;

import com.QF.Almacen_backend.Dto.SalidasRequestDTO;
import com.QF.Almacen_backend.Dto.SalidasResponseDTO;
import com.QF.Almacen_backend.Servicios.SalidasService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/salidas")
@CrossOrigin(origins = "http://localhost:5173")
public class SalidasController {

    private final SalidasService salidasService;

    public SalidasController(SalidasService salidasService) {
        this.salidasService = salidasService;
    }


    @PostMapping("/guardar")
    public ResponseEntity<?> guardarSalida(@RequestBody SalidasRequestDTO request) {
        try {
            salidasService.registrarSalida(request);
            return ResponseEntity.status(HttpStatus.CREATED).body("Salida registrada y stock actualizado.");
        } catch (RuntimeException e) {
            // Atrapamos el error de "Stock insuficiente" y le mandamos un mensaje claro a React
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/historial")
    public ResponseEntity<List<SalidasResponseDTO>> obtenerHistorial() {
        List<SalidasResponseDTO> historial = salidasService.listarSalidas();
        return ResponseEntity.ok(historial);
    }















}
