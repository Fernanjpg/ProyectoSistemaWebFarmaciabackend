package com.QF.Almacen_backend.RestControladores;

import com.QF.Almacen_backend.Dto.GeolocalizacionResponseDTO;
import com.QF.Almacen_backend.Servicios.GeocalizacionService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/geolocalizacion")
@CrossOrigin(origins = "http://localhost:5173")
public class GeolocalizacionController {

private final GeocalizacionService geocalizacionService;


public GeolocalizacionController(GeocalizacionService geocalizacionService) {
        this.geocalizacionService = geocalizacionService;
    }

@GetMapping("/puntos")
public ResponseEntity<List<GeolocalizacionResponseDTO>> obtenerPuntosMapa() {
   List<GeolocalizacionResponseDTO> puntos = geocalizacionService.obtenerPuntosParaMapa();
      return ResponseEntity.ok(puntos);

}


}
