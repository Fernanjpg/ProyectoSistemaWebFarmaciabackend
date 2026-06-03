package com.QF.Almacen_backend.Dto;

public record LoginResponseDTO(Integer idUsuarios,
                               String nombre,
                               String apellido,
                               String email,
                               String username,
                               String nombreRo) {
}
