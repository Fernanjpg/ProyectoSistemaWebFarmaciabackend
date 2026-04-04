package com.QF.Almacen_backend.Entidades;


import jakarta.persistence.*;

@Entity
@Table(name = "almacenes")

public class Almacenes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_almacenes")
    private Integer idalmacenes;

    @Column(name = "nombre",nullable = false,length = 100)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "id_tipo")
    private TipoAlmacen tipoAlmacen;

    public Almacenes() {
    }

    public Almacenes(Integer idalmacenes, String nombre, TipoAlmacen tipoAlmacen) {
        this.idalmacenes = idalmacenes;
        this.nombre = nombre;
        this.tipoAlmacen = tipoAlmacen;
    }

    public long getIdalmacenes() {
        return idalmacenes;
    }

    public void setIdalmacenes(Integer idalmacenes) {
        this.idalmacenes =  idalmacenes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoAlmacen getTipoAlmacen() {
        return tipoAlmacen;
    }

    public void setTipoAlmacen(TipoAlmacen tipoAlmacen) {
        this.tipoAlmacen = tipoAlmacen;
    }
}