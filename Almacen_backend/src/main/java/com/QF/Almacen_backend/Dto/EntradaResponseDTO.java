package com.QF.Almacen_backend.Dto;

import java.time.LocalDateTime;

public record EntradaResponseDTO(Integer idEntrada,
                                 String nombreProducto,
                                 String nombreProveedor,
                                 String nombreAlmacen,
                                 Integer cantidad,
                                 String observacion,
                                 LocalDateTime fechaEntrada) {
}
