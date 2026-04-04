package com.QF.Almacen_backend.Entidades;


import jakarta.persistence.*;

@Entity
@Table(name = "tipo_producto")

public class Tipoproductos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tproductos")
    private Integer idtproductos;

    @Column(name = "nombre",nullable = false,length = 100)
    private String nombre;


    public Tipoproductos() {
    }

    public Tipoproductos(Integer id_tproductos, String nombre) {
        this.idtproductos = id_tproductos;
        this.nombre = nombre;
    }

    public long getId_tproductos() {
        return idtproductos;
    }
    public void setId_tproductos(Integer id_tproductos) {
        this.idtproductos = id_tproductos;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
