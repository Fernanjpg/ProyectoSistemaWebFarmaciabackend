package com.QF.Almacen_backend.RestControladores;

import com.QF.Almacen_backend.Dto.DevolucionesRequestDTO;
import com.QF.Almacen_backend.Dto.DevolucionesResponseDTO;
import com.QF.Almacen_backend.Servicios.DevolucionesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devoluciones")
@CrossOrigin(origins = "http://localhost:5173")
public class DevolucionesController {
    private final DevolucionesService devolucionesService;

    public DevolucionesController(DevolucionesService devolucionesService) {
        this.devolucionesService = devolucionesService;
    }


    @PostMapping("/guardar")
    public ResponseEntity<String> guardarDevolucion(@RequestBody DevolucionesRequestDTO request) {
        devolucionesService.registrarDevolucion(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("Devolución procesada y registrada.");
    }

    @GetMapping("/historial")
    public ResponseEntity<List<DevolucionesResponseDTO>> obtenerHistorial() {
        List<DevolucionesResponseDTO> historial = devolucionesService.listarDevoluciones();
        return ResponseEntity.ok(historial);
    }
}
