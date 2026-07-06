package com.QF.Almacen_backend.Dto;

public record ProductosDanadoRequestDTO(Integer idProducto,
                                        Integer idUsuario,
                                        Integer cantidad,
                                        String motivo,
                                        String observaciones) {
}
