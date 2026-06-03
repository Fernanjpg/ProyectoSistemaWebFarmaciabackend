package com.QF.Almacen_backend.RestControladores;

import com.QF.Almacen_backend.Dto.TransporteRequestDTO;
import com.QF.Almacen_backend.Dto.TransporteResponseDTO;
import com.QF.Almacen_backend.Servicios.TransporteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/transporte")
@CrossOrigin(origins = "http://localhost:5173")
public class TransporteController {

    private final TransporteService transporteService;

    public TransporteController(TransporteService transporteService) {
        this.transporteService = transporteService;
    }


    @PostMapping("/guardar")
    public ResponseEntity<String> guardarTransporte(@RequestBody TransporteRequestDTO request) {
        transporteService.registrarTransporte(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("Unidad de transporte guardada con éxito.");
    }

    @GetMapping("/listar")
    public ResponseEntity<List<TransporteResponseDTO>> obtenerTransportes() {
        List<TransporteResponseDTO> lista = transporteService.listarTransportes();
        return ResponseEntity.ok(lista);
    }





}
