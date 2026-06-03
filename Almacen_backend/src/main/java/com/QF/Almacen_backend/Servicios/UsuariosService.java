package com.QF.Almacen_backend.Servicios;

import com.QF.Almacen_backend.Dto.UsuarioRequestDTO;
import com.QF.Almacen_backend.Dto.UsuarioResponseDTO;
import com.QF.Almacen_backend.Entidades.Roles;
import com.QF.Almacen_backend.Entidades.Usuarios;
import com.QF.Almacen_backend.Repositorios.RepsitorioUsuarios;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuariosService {


    private final RepsitorioUsuarios repsitorioUsuarios;


    public UsuariosService(RepsitorioUsuarios repsitorioUsuarios) {
        this.repsitorioUsuarios = repsitorioUsuarios;
    }


    public List<UsuarioResponseDTO> listarUsarios() {
       return repsitorioUsuarios.findAll().stream().map(
                usuarios -> new UsuarioResponseDTO(
                        usuarios.getIdUsuarios(),
                        usuarios.getNombre(),
                        usuarios.getRoles() != null ? usuarios.getRoles().getNombre() : "Sin Rol",
                        usuarios.getEmail()

                )

        ).toList();
    }
}
