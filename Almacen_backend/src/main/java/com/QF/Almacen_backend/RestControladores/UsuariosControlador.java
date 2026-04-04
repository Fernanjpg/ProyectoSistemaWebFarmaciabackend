package com.QF.Almacen_backend.RestControladores;


import com.QF.Almacen_backend.Entidades.Usuarios;
import com.QF.Almacen_backend.Repositorios.RepsitorioUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// esto es un restcontroller
@RestController
//
@CrossOrigin(origins = "http://localhost:5173")
//
@RequestMapping("api/usuarios")
public class UsuariosControlador {

    @Autowired
    private RepsitorioUsuarios repsitorioUsuarios;
    @PostMapping ("/login")
    public ResponseEntity<?>login(@RequestBody Usuarios datoslogin)  {
        return repsitorioUsuarios.findByUsername(datoslogin.getUsername()).map( usuarioencotrado -> {
            //una condicion:
            if (usuarioencotrado.getPassword().equals(datoslogin.getPassword())) {

                // 3. ¡Éxito! Limpiamos la clave por seguridad y enviamos el objeto
                usuarioencotrado.setContraseña(null);
                return ResponseEntity.ok(usuarioencotrado);

            } else {
                // 4. Contraseña mal
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Contraseña incorrecta para QF");
            }

        } )
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("El usuario no existe en el sistema"));
    }

    }

