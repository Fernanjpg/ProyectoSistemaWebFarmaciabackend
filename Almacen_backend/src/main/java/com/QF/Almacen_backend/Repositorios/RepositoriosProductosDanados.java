package com.QF.Almacen_backend.Repositorios;

import com.QF.Almacen_backend.Entidades.ProductosDanados;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoriosProductosDanados extends JpaRepository<ProductosDanados,Integer>
{
}
