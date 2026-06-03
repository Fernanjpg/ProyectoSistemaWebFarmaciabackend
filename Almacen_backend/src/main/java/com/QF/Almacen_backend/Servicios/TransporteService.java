package com.QF.Almacen_backend.Servicios;

import com.QF.Almacen_backend.Dto.TransporteRequestDTO;
import com.QF.Almacen_backend.Dto.TransporteResponseDTO;
import com.QF.Almacen_backend.Entidades.Transporte;
import com.QF.Almacen_backend.Repositorios.RepositoriosTransporte;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class TransporteService {
    private final RepositoriosTransporte transporteRepository;

    public TransporteService(RepositoriosTransporte transporteRepository) {
        this.transporteRepository = transporteRepository;
    }

    // RF-13: Registrar un vehículo/método de transporte
    public void registrarTransporte(@RequestBody  TransporteRequestDTO dto) {
        Transporte transporte = new Transporte();
        transporte.setPlaca(dto.placa());
        transporte.setDescripcionmetodo(dto.descripcionMetodo());
        transporte.setCapacidad(dto.capacidad());

        // Regla: Si el frontend no manda estado, nace listo para trabajar
        if (dto.estado() == null || dto.estado().isBlank()) {
            transporte.setEstado("DISPONIBLE");
        } else {
            transporte.setEstado(dto.estado());
        }

        transporteRepository.save(transporte);
    }

    // RF-13: Listar toda la flota para la tabla de React
    public List<TransporteResponseDTO> listarTransportes() {
        return transporteRepository.findAll().stream()
                .map(t -> new TransporteResponseDTO(
                        t.getIdtransporte(), // Usamos el nombre exacto de tu atributo privado
                        t.getPlaca(),
                        t.getDescripcionmetodo(),
                        t.getCapacidad(),
                        t.getEstado()
                ))
                .toList();
    }
}
