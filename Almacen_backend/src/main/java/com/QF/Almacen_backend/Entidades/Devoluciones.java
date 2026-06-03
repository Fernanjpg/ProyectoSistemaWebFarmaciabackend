package com.QF.Almacen_backend.Entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "devoluciones")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Devoluciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_devolucion")
    private Integer idDevolucion;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "motivo", nullable = false, length = 255)
    private String motivo;

    @Column(name = "fecha_devolucion")
    private LocalDateTime fechaDevolucion = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", nullable = false)
    private Productos productos;
}
