package com.QF.Almacen_backend.Entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "puntos_venta")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PuntosVentas {

@Id
@Column(name = "id_punto_venta")
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer idpuntosventa;

@Column(name = "nombre",nullable = false , length = 150)
private String nombre;

@Column(name = "direccion" , nullable = false , length = 255)
private String direccion;



}
