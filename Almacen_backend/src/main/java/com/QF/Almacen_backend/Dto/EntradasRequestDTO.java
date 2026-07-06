package com.QF.Almacen_backend.Dto;

public record EntradasRequestDTO(Integer idProducto,
                                 Integer idProveedor,
                                 Integer idAlmacen,
                                 Integer cantidad,
                                 String observacion) {
}
