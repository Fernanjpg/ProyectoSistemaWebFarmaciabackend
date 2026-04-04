package com.QF.Almacen_backend.Entidades;


import jakarta.persistence.*;

@Entity
@Table(name = "producto")

public class Productos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Integer idproducto;

    @Column(name ="nombre",length = 100)
    private String nombre;

    @Column(name = "stock")
    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "proveedor_id")
    private Proveedores proveedores;
    @ManyToOne
    @JoinColumn(name = "Tproductos_id")
    private Tipoproductos tipoProductos;

    public Productos() {
    }

    public Productos(Integer idproducto, String nombre, Integer stock, Proveedores proveedores, Tipoproductos tipoProductos) {
        this.idproducto = idproducto;
        this.nombre = nombre;
        this.stock = stock;
        this.proveedores = proveedores;
        this.tipoProductos = tipoProductos;
    }

    public long getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(Integer idproducto) {
        this.idproducto = idproducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Proveedores getProveedores() {
        return proveedores;
    }

    public void setProveedores(Proveedores proveedores) {
        this.proveedores = proveedores;
    }

    public Tipoproductos getTipoProductos() {
        return tipoProductos;
    }

    public void setTipoProductos(Tipoproductos tipoProductos) {
        this.tipoProductos = tipoProductos;
    }
}
