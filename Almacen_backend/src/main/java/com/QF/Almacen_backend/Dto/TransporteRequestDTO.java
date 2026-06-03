package com.QF.Almacen_backend.Dto;

public record TransporteRequestDTO(String placa,
                                   String descripcionMetodo, // Mapea a descripcionmetodo
                                   String capacidad,
                                   String estado // Opcional, por si quieren mandar un estado específico desde React
){
}
