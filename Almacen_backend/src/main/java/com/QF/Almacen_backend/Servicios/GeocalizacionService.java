package com.QF.Almacen_backend.Servicios;


import com.QF.Almacen_backend.Dto.GeolocalizacionResponseDTO;
import com.QF.Almacen_backend.Entidades.PuntosVentas;
import com.QF.Almacen_backend.Repositorios.RepositoriosPuntosVenta;

import org.springframework.stereotype.Service;



import java.util.List;


@Service
public class GeocalizacionService {

    private final RepositoriosPuntosVenta repositoriosPuntosVenta;

    public GeocalizacionService(RepositoriosPuntosVenta repositoriosPuntosVenta) {
        this.repositoriosPuntosVenta = repositoriosPuntosVenta;
    }
    public List<GeolocalizacionResponseDTO> obtenerPuntosParaMapa() {
        List<PuntosVentas> puntosBD = repositoriosPuntosVenta.findAll();
        return puntosBD.stream().map(pv ->
                new GeolocalizacionResponseDTO(
                    pv.getIdpuntosventa(),
                    pv.getNombre(),
                    pv.getDireccion(),
                    null,
                    null

            )).toList();





    }
}









