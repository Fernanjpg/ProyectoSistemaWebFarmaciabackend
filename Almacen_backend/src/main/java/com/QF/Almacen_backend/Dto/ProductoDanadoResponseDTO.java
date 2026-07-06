package com.QF.Almacen_backend.Dto;

import java.time.LocalDateTime;

public record ProductoDanadoResponseDTO(Integer id,
                                        String producto,
                                        String usuario,
                                        Integer cantidad,
                                        String motivo,
                                        String observaciones,
                                        LocalDateTime fechaReporte) {
}
