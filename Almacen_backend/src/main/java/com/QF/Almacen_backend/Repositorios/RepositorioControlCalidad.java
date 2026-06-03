package com.QF.Almacen_backend.Repositorios;

import com.QF.Almacen_backend.Entidades.ControlCalidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioControlCalidad extends JpaRepository<ControlCalidad , Integer> {
}
