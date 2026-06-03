package com.QF.Almacen_backend.Dto;

import java.time.LocalDateTime;

public record SalidasResponseDTO (Integer idSalida,
                                  String nombreProducto,
                                  String destinoPuntoVenta,
                                  Integer cantidad,
                                  String motivo,
                                  LocalDateTime fechaSalida){
}
