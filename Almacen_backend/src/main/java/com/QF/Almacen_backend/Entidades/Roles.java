package com.QF.Almacen_backend.Entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id_roles")
    private Integer id_roles;

    @Column(name = "nombre",nullable = false,unique = true,length = 50)
    private String nombre;

    public Roles() {
    }

    public Roles(Integer id_roles, String nombre) {
        this.id_roles = id_roles;
        this.nombre = nombre;
    }

    public Roles(String nombre) {
        this.nombre = nombre;
    }

    public Integer getId_roles() {
        return id_roles;
    }

    public void setId_roles(Integer id_roles) {
        this.id_roles = id_roles;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
