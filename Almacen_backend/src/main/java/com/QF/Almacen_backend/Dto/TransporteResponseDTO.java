package com.QF.Almacen_backend.Dto;

public record TransporteResponseDTO(Integer idTransporte, // Mapea a intransporte
                                    String placa,
                                    String descripcionMetodo,
                                    String capacidad,
                                    String estado) {
}
