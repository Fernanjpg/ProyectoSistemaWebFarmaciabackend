package com.QF.Almacen_backend.Entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "transporte")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transporte {

@Id
@Column(name = "id_transporte")
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer idtransporte;

@Column(name = "placa",unique = true)
private  String placa;

@Column(name = "descripcion_metodo", nullable = false,length = 100)
private String descripcionmetodo;

@Column(name = "capacidad")
private String capacidad;

@Column(name = "estado")
private String estado;

}
