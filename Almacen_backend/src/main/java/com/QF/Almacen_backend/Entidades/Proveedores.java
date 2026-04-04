package com.QF.Almacen_backend.Entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "proveedores")

public class Proveedores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proveedores")
    private Integer id;

    @Column(name = "Nombre",nullable = false,length = 100)
    private String Nombre;

    @Column(name = "contacto", unique = true,nullable = false,length = 50)
    private String Contacto;

    public Proveedores() {
    }

    public Proveedores(Integer id, String nombre, String contacto) {
        this.id = id;
        this.Nombre = nombre;
        this.Contacto = contacto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getContacto() {
        return Contacto;
    }

    public void setContacto(String contacto) {
        Contacto = contacto;
    }
}
