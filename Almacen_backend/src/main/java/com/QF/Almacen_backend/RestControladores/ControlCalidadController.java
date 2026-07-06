package com.QF.Almacen_backend.RestControladores;


import com.QF.Almacen_backend.Dto.ControlCalidadRequestDTO;
import com.QF.Almacen_backend.Dto.ControlCalidadResponseDTO;
import com.QF.Almacen_backend.Servicios.ControlCalidadService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/control-calidad")
@CrossOrigin(origins = "http://localhost:5173")
public class ControlCalidadController {


private final ControlCalidadService controlCalidadService;

public ControlCalidadController(ControlCalidadService controlCalidadService) {
        this.controlCalidadService = controlCalidadService;
    }

    @PostMapping("/Guardar")      // ← Cambiar de: /guardar
    public ResponseEntity<?> guardarInspeccion(@RequestBody ControlCalidadRequestDTO requestDTO) {
        controlCalidadService.registrarInspeccion(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Inspección de calidad registrada con éxito");
    }

    @GetMapping("/Listar")        // ← Cambiar de: /historial
    public ResponseEntity<List<ControlCalidadResponseDTO>> obtenerhistorial() {
        List<ControlCalidadResponseDTO> historial = controlCalidadService.listarHistorial();
        return ResponseEntity.ok(historial);
    }









}
