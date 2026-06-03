package com.QF.Almacen_backend.Dto;

public record DevolucionesRequestDTO(Integer idProducto,
                                     Integer cantidad,
                                     String motivo) {
}
