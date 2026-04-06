package com.QF.Almacen_backend.RestControladores;


import com.QF.Almacen_backend.Entidades.Productos;
import com.QF.Almacen_backend.Repositorios.RepsitorioProductos;
import org.springframework.beans.factory.annotation.Autowired;

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

@PostMapping("/Guardar")
public Productos guardar(@RequestBody Productos productos) {
      return repsitorioProductos.save(productos);
}

@DeleteMapping("/Eliminar/{id}")
public void eliminar(@PathVariable Integer id) {
   repsitorioProductos.deleteById(id);
   }



}
