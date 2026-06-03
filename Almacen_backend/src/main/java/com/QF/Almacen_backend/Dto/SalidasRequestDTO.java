package com.QF.Almacen_backend.Dto;

public record SalidasRequestDTO(Integer idProducto,
                                Integer idPuntoVenta,
                                Integer cantidad,
                                String motivo) {
}
