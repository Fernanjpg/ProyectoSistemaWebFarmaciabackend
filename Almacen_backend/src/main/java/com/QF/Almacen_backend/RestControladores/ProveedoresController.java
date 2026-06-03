package com.QF.Almacen_backend.RestControladores;


import com.QF.Almacen_backend.Entidades.Proveedores;
import com.QF.Almacen_backend.Repositorios.RepsitorioProveedores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proveedores")
@CrossOrigin(origins = "http://localhost:5173")
public class ProveedoresController {

 @Autowired
    private RepsitorioProveedores repsitorioProveedores;

 @GetMapping("/Listar")
    public List<Proveedores> ListarProveedores() {
     return repsitorioProveedores.findAll();
 }

 @PostMapping("/Guardar")
    public Proveedores guardar (@RequestBody Proveedores proveedores){
     return repsitorioProveedores.save(proveedores);
 }
@DeleteMapping("/Eliminar/{id}")
public void eliminar(@PathVariable Integer id) {
      repsitorioProveedores.deleteById(id);
    }

}
