package com.QF.Almacen_backend.RestControladores;


import com.QF.Almacen_backend.Entidades.Usuarios;
import com.QF.Almacen_backend.Repositorios.RepsitorioUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// esto es un restcontroller
@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/usuarios")
public class UsuariosControlador {

    @Autowired
    private RepsitorioUsuarios repsitorioUsuarios;

    @PostMapping("/login")
        public ResponseEntity<?> login(@RequestBody Usuarios loginData) {
            return repsitorioUsuarios.findByUsername(loginData.getUsername())
                    .map(user -> {
                        if (user.getPassword().equals(loginData.getPassword())) {
                            user.setPassword(null); // Seguridad
                            return ResponseEntity.ok(user);
                        }
                        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Clave incorrecta");
                    }).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no existe"));
        }


        @GetMapping("/Listar")
       public List<Usuarios> listarUsuarios() {
          return repsitorioUsuarios.findAll();


    }

      @PostMapping("/Guardar")
         public Usuarios Guardar (@RequestBody Usuarios user) {
            return repsitorioUsuarios.save(user);
       }
    @DeleteMapping("/Eliminar/{id}")
    public void eliminar(@PathVariable Integer id) {
        repsitorioUsuarios.deleteById(id);
    }



 



    }



