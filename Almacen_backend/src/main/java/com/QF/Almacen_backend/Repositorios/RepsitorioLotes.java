package com.QF.Almacen_backend.Repositorios;


import com.QF.Almacen_backend.Entidades.Lotes;
import com.QF.Almacen_backend.Entidades.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepsitorioLotes extends JpaRepository<Lotes,Long> {
}
