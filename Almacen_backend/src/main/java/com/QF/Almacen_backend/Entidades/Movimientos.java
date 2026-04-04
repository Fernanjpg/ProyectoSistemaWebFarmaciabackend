package com.QF.Almacen_backend.Entidades;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "movimientos")
public class Movimientos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movimiento")
    private Integer idMovimiento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoMovimiento tipo;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(insertable = false, updatable = false)
    private LocalDateTime fecha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id")
    private Productos producto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "almacen_origen_id")
    private Almacenes almacenOrigen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "almacen_destino_id")
    private Almacenes almacenDestino;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuarios usuario;

    // Enum interno o en archivo separado
    public enum TipoMovimiento {
        ENTRADA, SALIDA, TRANSFERENCIA
    }

    // Constructores, Getters y Setters...


    public Movimientos() {
    }

    public Movimientos(Integer idMovimiento, TipoMovimiento tipo, Integer cantidad, LocalDateTime fecha, Productos producto, Almacenes almacenOrigen, Almacenes almacenDestino, Usuarios usuario) {
        this.idMovimiento = idMovimiento;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.producto = producto;
        this.almacenOrigen = almacenOrigen;
        this.almacenDestino = almacenDestino;
        this.usuario = usuario;
    }

    public Integer getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(Integer idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public TipoMovimiento getTipo() {
        return tipo;
    }

    public void setTipo(TipoMovimiento tipo) {
        this.tipo = tipo;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }

    public Almacenes getAlmacenOrigen() {
        return almacenOrigen;
    }

    public void setAlmacenOrigen(Almacenes almacenOrigen) {
        this.almacenOrigen = almacenOrigen;
    }

    public Almacenes getAlmacenDestino() {
        return almacenDestino;
    }

    public void setAlmacenDestino(Almacenes almacenDestino) {
        this.almacenDestino = almacenDestino;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }
}