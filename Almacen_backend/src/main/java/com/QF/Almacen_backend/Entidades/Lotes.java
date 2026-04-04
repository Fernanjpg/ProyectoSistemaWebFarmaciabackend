package com.QF.Almacen_backend.Entidades;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "lotes")

public class Lotes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_lotes")
    private Integer idlotes;

    @Column(name = "numero_lote",length = 50)
    private String numerolote;

    @Column(name = "fecha_vecimiento")
    private LocalDate fechavencimiento;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Productos productos;

    public Lotes() {
    }

    public Integer getId_lotes() {
        return idlotes;
    }

    public void setId_lotes(Integer id_lotes) {
        this.idlotes = id_lotes;
    }

    public String getNumerolote() {
        return numerolote;
    }

    public void setNumerolote(String numerolote) {
        this.numerolote = numerolote;
    }

    public LocalDate getFechavecimiento() {
        return fechavencimiento;
    }

    public void setFechavencimiento(LocalDate fechavencimiento) {
        this.fechavencimiento = fechavencimiento;
    }

    public Productos getProductos() {
        return productos;
    }

    public void setProductos(Productos productos) {
        this.productos = productos;
    }

}
