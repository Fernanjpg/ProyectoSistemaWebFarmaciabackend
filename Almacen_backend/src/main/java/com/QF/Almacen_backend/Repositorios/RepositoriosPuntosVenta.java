package com.QF.Almacen_backend.Repositorios;

import com.QF.Almacen_backend.Entidades.PuntosVentas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoriosPuntosVenta extends JpaRepository <PuntosVentas,Integer> {
}
