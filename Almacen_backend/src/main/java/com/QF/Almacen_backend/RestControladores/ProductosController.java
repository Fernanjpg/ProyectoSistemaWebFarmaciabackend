package com.QF.Almacen_backend.RestControladores;


import com.QF.Almacen_backend.Dto.ProductoStockDTO;
import com.QF.Almacen_backend.Entidades.Productos;
import com.QF.Almacen_backend.Repositorios.RepsitorioProductos;
import com.QF.Almacen_backend.Servicios.ProductosService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/productos")
public class ProductosController {


private final ProductosService productosService;
private final RepsitorioProductos repsitorioProductos;

public ProductosController(ProductosService productosService, RepsitorioProductos repsitorioProductos) {
        this.productosService = productosService;
    this.repsitorioProductos = repsitorioProductos;
}
    @GetMapping("/Listar")
    public List<Productos> listarProductos() {
        return  repsitorioProductos.findAll();
    }

    @GetMapping("/Buscar/{nombre}")
    public ResponseEntity<?> BuscarProdcutos(@PathVariable String nombre) {
        // Llama al repositorio con el texto buscado
        List<Productos> resultados = repsitorioProductos
                .findByNombreContainingIgnoreCase(nombre);

        // Si encontró productos → devuelve la lista con 200 OK
        if (!resultados.isEmpty()) {
            return ResponseEntity.ok(resultados);
        }

        // Si no encontró nada → devuelve 404 con mensaje
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("No se encontraron productos con el nombre: " + nombre);


    }

    // RF-20: Guardar valida y normaliza la unidad de medida (KG, L, UNIDADES)
    @PostMapping("/Guardar")
    public ResponseEntity<?> guardar(@RequestBody Productos productos) {
        try {
            return ResponseEntity.ok(productosService.guardarProducto(productos));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/Eliminar/{id}")
    public void eliminar(@PathVariable Integer id) {
        repsitorioProductos.deleteById(id);
    }

    // RF-17: Registro de Stock con Alerta - vista completa con estado de alerta
    @GetMapping("/Stock")
    public ResponseEntity<List<ProductoStockDTO>> listarStock() {
        return ResponseEntity.ok(productosService.listarStockConAlerta());
    }

    // RF-17: Solo los productos que están por debajo (o igual) de su stock mínimo
    @GetMapping("/Stock/Alertas")
    public ResponseEntity<List<ProductoStockDTO>> listarAlertas() {
        return ResponseEntity.ok(productosService.listarSoloAlertas());
    }




}
