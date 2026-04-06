package com.QF.Almacen_backend.Entidades;

import jakarta.persistence.*;

@Entity
@Table(name= "usuarios")
public class Usuarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuarios")
    private Integer idUsuarios;

    @Column(name = "Nombre",nullable = false,length = 100)
    private String nombre;

    @Column(name = "Apellido",nullable = false,length = 50)
    private String apellido;

    @Column(name = "user",nullable = false, unique = true,length = 50)
    private String username;

    @Column(name = "email",nullable = false,unique = true,length = 100)
    private String email;

    @Column(name = "password", nullable = false,length = 250)
    private String password;

    @ManyToOne
    @JoinColumn(name = "id_roles")
    private Roles rol;

    public Usuarios() {
    }

    public Usuarios(Integer idusuarios, String nombre, String apellido, String username, String email, String password, Roles rol) {
        this.idUsuarios = idusuarios;
        this.nombre = nombre;
        this.apellido = apellido;
        this.username= username;
        this.email = email;
        this.password= password;
        this.rol = rol;
    }

    public Integer getIdUsuarios() {
        return idUsuarios;
    }

    public void setIdUsuarios(Integer idUsuarios) {
        this.idUsuarios = idUsuarios;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Roles getRoles() {
        return rol;
    }

    public void setRoles(Roles roles) {
        this.rol = roles;
    }
}


