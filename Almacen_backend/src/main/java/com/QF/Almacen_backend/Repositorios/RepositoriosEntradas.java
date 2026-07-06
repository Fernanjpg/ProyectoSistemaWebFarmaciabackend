package com.QF.Almacen_backend.Repositorios;

import com.QF.Almacen_backend.Entidades.Entradas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoriosEntradas extends JpaRepository<Entradas,Integer> {
}
