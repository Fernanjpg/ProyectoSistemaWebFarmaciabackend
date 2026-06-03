package com.QF.Almacen_backend.Dto;

import com.QF.Almacen_backend.Entidades.Roles;

public record UsuarioResponseDTO(Integer idUsuarios, String nombre ,String nombreRol, String email) {
}
