package com.QF.Almacen_backend.Repositorios;


import com.QF.Almacen_backend.Entidades.Almacenes;
import com.QF.Almacen_backend.Entidades.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepsitorioAlmacenes extends JpaRepository<Almacenes,Long> {
}
