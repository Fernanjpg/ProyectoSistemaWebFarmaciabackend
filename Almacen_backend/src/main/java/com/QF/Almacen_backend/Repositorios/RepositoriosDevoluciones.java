package com.QF.Almacen_backend.Repositorios;

import com.QF.Almacen_backend.Entidades.Devoluciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoriosDevoluciones extends JpaRepository<Devoluciones,Integer> {
}
