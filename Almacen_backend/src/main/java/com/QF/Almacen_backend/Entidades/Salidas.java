package com.QF.Almacen_backend.Entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "salidas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Salidas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_salida")
    private Integer idSalida;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "motivo", nullable = false, length = 100)
    private String motivo;

    @Column(name = "fecha_salida")
    private LocalDateTime fechaSalida = LocalDateTime.now();

    // Relación perezosa (LAZY) con Productos para evitar sobrecargar la BD
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", nullable = false)
    private Productos productos;

    // Relación perezosa con PuntosVenta (Las sucursales como la farmacia)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "punto_venta_id", nullable = false)
    private PuntosVentas puntosVenta;
}
