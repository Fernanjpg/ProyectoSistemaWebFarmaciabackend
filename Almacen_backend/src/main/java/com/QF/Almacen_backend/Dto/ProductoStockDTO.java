package com.QF.Almacen_backend.Dto;

public record ProductoStockDTO(Integer idProducto,
                               String nombre,
                               Integer stock,
                               Integer stockMinimo,
                               String unidadMedida,
                               boolean alerta) {
}
