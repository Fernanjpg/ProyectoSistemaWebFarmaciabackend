package com.QF.Almacen_backend.RestControladores;


import com.QF.Almacen_backend.Entidades.Proveedores;
import com.QF.Almacen_backend.Repositorios.RepsitorioProveedores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/proveedores")
@CrossOrigin(origins = "http://localhost:5173")
public class ProveedoresControlador {

 @Autowired
    private RepsitorioProveedores repsitorioProveedores;

 @GetMapping("/Listar")
    public List<Proveedores> ListarProveedores() {
     return repsitorioProveedores.findAll();
 }




}
