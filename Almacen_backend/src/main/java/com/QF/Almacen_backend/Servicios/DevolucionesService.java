package com.QF.Almacen_backend.Servicios;

import com.QF.Almacen_backend.Dto.DevolucionesRequestDTO;
import com.QF.Almacen_backend.Dto.DevolucionesResponseDTO;
import com.QF.Almacen_backend.Entidades.Devoluciones;
import com.QF.Almacen_backend.Entidades.Productos;
import com.QF.Almacen_backend.Repositorios.RepositoriosDevoluciones;
import com.QF.Almacen_backend.Repositorios.RepsitorioProductos;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DevolucionesService {

    private final RepositoriosDevoluciones devolucionesRepository;
    private final RepsitorioProductos productosRepository;

    public DevolucionesService(RepositoriosDevoluciones devolucionesRepository, RepsitorioProductos productosRepository) {
        this.devolucionesRepository = devolucionesRepository;
        this.productosRepository = productosRepository;
    }


    // RF-14: Registrar Devolución y actualizar stock
    @Transactional
    public void registrarDevolucion(DevolucionesRequestDTO dto) {
        Productos producto = productosRepository.findById(dto.idProducto())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        // 1. Guardamos el registro de la devolución
        Devoluciones devolucion = new Devoluciones();
        devolucion.setProductos(producto);
        devolucion.setCantidad(dto.cantidad());
        devolucion.setMotivo(dto.motivo());
        devolucionesRepository.save(devolucion);

        // 2. Lógica automática: Devolvemos las unidades al stock del producto
        // (Ajusta 'getStock()' y 'setStock()' según los nombres reales en tu entidad Productos)
        // int nuevoStock = producto.getStock() + dto.cantidad();
        // producto.setStock(nuevoStock);
        // productosRepository.save(producto);
    }

    // RF-14: Listar el historial de devoluciones para React
    public List<DevolucionesResponseDTO> listarDevoluciones() {
        return devolucionesRepository.findAll().stream()
                .map(d -> new DevolucionesResponseDTO(
                        d.getIdDevolucion(),
                        d.getProductos() != null ? d.getProductos().getNombre() : "PRODUCTO INEXISTENTE",
                        d.getCantidad(),
                        d.getMotivo(),
                        d.getFechaDevolucion()
                ))
                .toList();
    }
}
