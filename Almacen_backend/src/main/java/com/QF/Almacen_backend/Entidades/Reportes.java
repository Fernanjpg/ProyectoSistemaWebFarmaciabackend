package com.QF.Almacen_backend.Entidades;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reportes")
public class Reportes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String tipo; // Ej: FALTANTE, DAÑADO, VENCIMIENTO

    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id")
    private Productos producto; // Relación con tu tabla de productos

    private LocalDateTime fechaReg = LocalDateTime.now();

    private String usuario; // Para guardar quién lo reportó (ej: RootF)

}
