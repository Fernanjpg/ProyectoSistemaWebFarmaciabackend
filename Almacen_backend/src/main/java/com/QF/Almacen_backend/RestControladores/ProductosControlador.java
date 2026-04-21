package com.QF.Almacen_backend.RestControladores;


import com.QF.Almacen_backend.Entidades.Productos;
import com.QF.Almacen_backend.Repositorios.RepsitorioProductos;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/productos")


public class ProductosControlador {


@Autowired
private RepsitorioProductos repsitorioProductos;

@GetMapping("/Listar")
public List<Productos> listarProductos() {
   return  repsitorioProductos.findAll();
}

@GetMapping("/Buscar/{nombre}")
public List<Productos> BuscarProdcutos(@PathVariable String nombre) {
    // Llama al repositorio con el texto buscado
    List<Productos> resultados = repsitorioProductos
            .findByNombreContainingIgnoreCase(nombre);

    // Si encontró productos → devuelve la lista con 200 OK
    if (!resultados.isEmpty()) {
        return (List<Productos>) ResponseEntity.ok(resultados);
    }

    // Si no encontró nada → devuelve 404 con mensaje
    return (List<Productos>) ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("No se encontraron productos con el nombre: " + nombre);


}

@PostMapping("/Guardar")
public Productos guardar(@RequestBody Productos productos) {
      return repsitorioProductos.save(productos);
}

@DeleteMapping("/Eliminar/{id}")
public void eliminar(@PathVariable Integer id) {
   repsitorioProductos.deleteById(id);
   }



}
