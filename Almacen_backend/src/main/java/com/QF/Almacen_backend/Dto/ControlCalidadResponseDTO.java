package com.QF.Almacen_backend.Dto;

import java.time.LocalDateTime;

public record ControlCalidadResponseDTO(
        Integer id,
        String producto,              // ← cambiar de: nombreProducto
        String usuario,               // ← cambiar de: nombreUsuario (opcional)
        String resultado,
        String observaciones,
        LocalDateTime fecha           // ← cambiar de: fechaInspeccion
) {
}