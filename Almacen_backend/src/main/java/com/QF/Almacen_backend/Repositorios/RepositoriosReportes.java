package com.QF.Almacen_backend.Repositorios;

import com.QF.Almacen_backend.Entidades.Reportes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoriosReportes extends JpaRepository< Reportes,Integer > {
}
