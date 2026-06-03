package com.QF.Almacen_backend.Dto;

import lombok.Data;

@Data
public class ReporteRequestDTO {
    private String tipo;
    private String producto;
    private String descripcion;
    private String fecha;
    private String usuario;
}