package com.QF.Almacen_backend.Entidades;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "control_calidad")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class ControlCalidad {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;


@Column(name = "fecha_inspeccion")
private LocalDateTime fechaInspeccion = LocalDateTime.now();

@Column(name = "resultado", length = 50, nullable = false)
private String resultado;

@Column (name = "observaciones", columnDefinition = "TEXT")
private String observaciones;

@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "usuario_id")
private Usuarios usuarios;

@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "producto_id")
private  Productos productos;







}
