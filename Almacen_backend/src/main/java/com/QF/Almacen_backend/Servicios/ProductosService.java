package com.QF.Almacen_backend.Servicios;

import com.QF.Almacen_backend.Dto.ProductoStockDTO;
import com.QF.Almacen_backend.Entidades.Productos;
import com.QF.Almacen_backend.Repositorios.RepsitorioProductos;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductosService {


private final RepsitorioProductos repsitorioProductos;

public ProductosService(RepsitorioProductos repsitorioProductos) {
        this.repsitorioProductos = repsitorioProductos;
    }


private static final List<String> UNIDADES_VALIDAS = List.of("KG","L","UNIDADES");
private static final String UNIDAD_POR_DEFECTO = "UNIDADES";
private static final int STOCK_MINIMO_POR_DEFECTO = 10;

    public Productos guardarProducto(Productos producto) {
        producto.setUnidadMedida(normalizarUnidadMedida(producto.getUnidadMedida()));

        if (producto.getStockMinimo() == null) {
            producto.setStockMinimo(STOCK_MINIMO_POR_DEFECTO);
        }
        if (producto.getStock() == null) {
            producto.setStock(0);
        }

        return repsitorioProductos.save(producto);
    }

    private String normalizarUnidadMedida(String unidadMedida) {
        if (unidadMedida == null || unidadMedida.isBlank()) {
            return UNIDAD_POR_DEFECTO;
        }
        String unidad = unidadMedida.trim().toUpperCase();
        if (!UNIDADES_VALIDAS.contains(unidad)) {
            throw new RuntimeException("Unidad de medida inválida. Use una de: " + UNIDADES_VALIDAS);
        }
        return unidad;
    }

    // RF-17: Registro de Stock con Alerta
    // Monitorea los niveles de stock en tiempo real y marca los productos en alerta.
    public List<ProductoStockDTO> listarStockConAlerta() {
        return repsitorioProductos.findAll().stream()
                .map(this::mapearStockDTO)
                .toList();
    }

    public List<ProductoStockDTO> listarSoloAlertas() {
        return repsitorioProductos.findAll().stream()
                .map(this::mapearStockDTO)
                .filter(ProductoStockDTO::alerta)
                .toList();
    }

    private ProductoStockDTO mapearStockDTO(Productos p) {
        int stock = p.getStock() != null ? p.getStock() : 0;
        int minimo = p.getStockMinimo() != null ? p.getStockMinimo() : STOCK_MINIMO_POR_DEFECTO;
        boolean enAlerta = stock <= minimo;

        return new ProductoStockDTO(
                (int) p.getIdproducto(),
                p.getNombre(),
                stock,
                minimo,
                p.getUnidadMedida(),
                enAlerta
        );
    }





}
