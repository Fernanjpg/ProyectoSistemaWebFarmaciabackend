package com.QF.Almacen_backend.Entidades;


import jakarta.persistence.*;

@Entity
@Table(name = "tipo_almacen")
public class TipoAlmacen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo")
    private Integer idtipo;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    public TipoAlmacen() {
    }

    public TipoAlmacen(Integer idtipo, String nombre) {
        this.idtipo = idtipo;
        this.nombre = nombre;
    }

    public Integer getIdtipo() {
        return idtipo;
    }

    public void setIdtipo(Integer idtipo) {
        this.idtipo = idtipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
