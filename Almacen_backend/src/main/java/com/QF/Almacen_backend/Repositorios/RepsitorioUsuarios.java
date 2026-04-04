package com.QF.Almacen_backend.Repositorios;


import com.QF.Almacen_backend.Entidades.Roles;
import com.QF.Almacen_backend.Entidades.Usuarios;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RepsitorioUsuarios extends JpaRepository<Usuarios,Integer> {
    Optional<Usuarios> findByUsername(String username);
}
