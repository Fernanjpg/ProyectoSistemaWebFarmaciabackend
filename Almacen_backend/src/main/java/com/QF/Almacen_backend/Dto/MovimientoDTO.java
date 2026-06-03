package com.QF.Almacen_backend.Dto;

import com.QF.Almacen_backend.Entidades.Movimientos;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class MovimientoDTO {

    private Integer idMovimiento;
    private String tipo;
    private Integer cantidad;
    private String productoNombre;
    private String almacenOrigenNombre;
    private String almacenDestinoNombre;

    // Constructor que mapea desde la entidad
    public MovimientoDTO(Movimientos m) {
        this.idMovimiento = m.getIdMovimiento();
        this.tipo = m.getTipo() != null ? m.getTipo().name() : null;
        this.cantidad = m.getCantidad();
        this.productoNombre = m.getProducto() != null ? m.getProducto().getNombre() : null;
        this.almacenOrigenNombre = m.getAlmacenOrigen() != null ? m.getAlmacenOrigen().getNombre() : null;
        this.almacenDestinoNombre = m.getAlmacenDestino() != null ? m.getAlmacenDestino().getNombre() : null;
    }
}
