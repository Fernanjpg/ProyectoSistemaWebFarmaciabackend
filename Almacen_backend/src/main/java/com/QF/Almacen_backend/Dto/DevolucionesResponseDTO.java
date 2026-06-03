package com.QF.Almacen_backend.Dto;

import java.time.LocalDateTime;

public record DevolucionesResponseDTO(Integer idDevolucion,
                                      String nombreProducto,
                                      Integer cantidad,
                                      String motivo,
                                      LocalDateTime fechaDevolucion) {
}
