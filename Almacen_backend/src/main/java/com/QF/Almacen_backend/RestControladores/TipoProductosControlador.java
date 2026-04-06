package com.QF.Almacen_backend.RestControladores;


import com.QF.Almacen_backend.Entidades.Productos;
import com.QF.Almacen_backend.Entidades.Tipoproductos;
import com.QF.Almacen_backend.Repositorios.RepsitorioTipoProductos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipoproductos")
@CrossOrigin(origins = "http://localhost:5173")

public class TipoProductosControlador {
    @Autowired
    private RepsitorioTipoProductos repsitorioTipoProductos;
    @GetMapping("/Listar")
    public List<Tipoproductos> listarTipoProductos() {
        return repsitorioTipoProductos.findAll();

    }
    @PostMapping("/Guardar")
    public Tipoproductos guardar(@RequestBody Tipoproductos tipoproductos) {
        return repsitorioTipoProductos.save(tipoproductos);
    }

    @DeleteMapping("/Eliminar/{id}")
    public void eliminar(@PathVariable Integer id) {
        repsitorioTipoProductos.deleteById(id);
    }


}
