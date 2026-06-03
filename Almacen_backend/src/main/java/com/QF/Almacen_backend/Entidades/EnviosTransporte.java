package com.QF.Almacen_backend.Entidades;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "envios_transporte")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnviosTransporte {

@Id
@Column(name = "id")
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer idenviostransporte;

@ManyToOne(fetch = FetchType.LAZY) // trae lo relacionado de la tabla, lo esencial.
@JoinColumn(name = "movimiento_id", referencedColumnName = "id_movimiento", nullable = false)
private Movimientos movimiento;

// Relación con la nueva tabla de Transporte
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "transporte_id", referencedColumnName = "id_transporte", nullable = false)
private Transporte transporte;

@Column(name = "fecha_despacho")
private LocalDateTime fechaDespacho = LocalDateTime.now();

@Column(name = "estado_entrega", length = 50)
private String estadoEntrega = "EN CAMINO";







}
