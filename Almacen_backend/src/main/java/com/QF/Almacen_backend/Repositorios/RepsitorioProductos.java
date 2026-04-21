package com.QF.Almacen_backend.Repositorios;


import com.QF.Almacen_backend.Entidades.Productos;
import com.QF.Almacen_backend.Entidades.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepsitorioProductos extends JpaRepository<Productos,Integer> {
    List<Productos> findByNombreContainingIgnoreCase(String Productos);
}
