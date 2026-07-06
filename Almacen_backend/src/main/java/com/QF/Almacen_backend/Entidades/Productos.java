package com.QF.Almacen_backend.Entidades;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "producto")
@Data
@AllArgsConstructor
@NoArgsConstructor

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

    @Column(name = "stock_minimo")
    private Integer stockMinimo;

    // RF-20: Unidad de medida del producto (KG, L, UNIDADES, etc.)
    @Column(name = "unidad_medida", length = 20)
    private String unidadMedida;



}
