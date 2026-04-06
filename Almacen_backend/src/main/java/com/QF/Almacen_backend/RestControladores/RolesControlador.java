package com.QF.Almacen_backend.RestControladores;


import com.QF.Almacen_backend.Entidades.Roles;
import com.QF.Almacen_backend.Repositorios.RepsitorioRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin(origins = "http://localhost:5173")

public class RolesControlador {
    @Autowired
    private RepsitorioRoles repsitorioRoles;
    @GetMapping("/Listar")
    public List<Roles> ListarRoles() {
        return repsitorioRoles.findAll();
    }

    @PostMapping("/Guardar")
    public Roles guardar(@RequestBody Roles rol) {
        return repsitorioRoles.save(rol);
    }
    @DeleteMapping("/Eliminar/{id}")
    public void eliminar(@PathVariable Integer id) {
        repsitorioRoles.deleteById(id);
    }

}
