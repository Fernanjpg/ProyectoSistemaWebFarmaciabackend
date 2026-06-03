package com.QF.Almacen_backend.RestControladores;


import com.QF.Almacen_backend.Dto.LoginRequestDTO;
import com.QF.Almacen_backend.Dto.LoginResponseDTO;
import com.QF.Almacen_backend.Dto.UsuarioRequestDTO;
import com.QF.Almacen_backend.Dto.UsuarioResponseDTO;
import com.QF.Almacen_backend.Entidades.Usuarios;
import com.QF.Almacen_backend.Repositorios.RepsitorioUsuarios;
import com.QF.Almacen_backend.Servicios.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// esto es un restcontroller
@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/usuarios")
public class UsuariosController {

    @Autowired
    private RepsitorioUsuarios repsitorioUsuarios;

    private final UsuariosService usuariosService;

    public UsuariosController(UsuariosService usuariosService) {
        this.usuariosService = usuariosService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequest) {
        // 1. Buscas al usuario (Como en tu query de Hibernate)
        Usuarios usuario = repsitorioUsuarios.findByUsername(loginRequest.username()).orElse(null);

        if (usuario == null || !loginRequest.password().equals(usuario.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
        }


        // 2. Mapeas los datos al Record DTO de forma segura
        LoginResponseDTO respuesta = new LoginResponseDTO(
                usuario.getIdUsuarios(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getEmail(),
                usuario.getUsername(),
                usuario.getRoles().getNombre() // 👈 Aquí despiertas el LAZY sin errores
        );

        // 3. Envías el DTO libre de contraseñas y libre de Proxies de Hibernate
        return ResponseEntity.ok(respuesta);
    }

        @GetMapping("/Listar")
       public ResponseEntity<List<UsuarioResponseDTO>> listarUsuarios() {
          List< UsuarioResponseDTO> lista = usuariosService.listarUsarios();
          return ResponseEntity.ok(lista);


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



