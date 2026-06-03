package com.QF.Almacen_backend.Repositorios;

import com.QF.Almacen_backend.Entidades.Transporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoriosTransporte extends JpaRepository<Transporte,Integer> {
}
