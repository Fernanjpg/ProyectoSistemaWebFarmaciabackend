package com.QF.Almacen_backend.Servicios;

import com.QF.Almacen_backend.Dto.ProductoDanadoResponseDTO;
import com.QF.Almacen_backend.Dto.ProductosDanadoRequestDTO;
import com.QF.Almacen_backend.Entidades.Movimientos;
import com.QF.Almacen_backend.Entidades.Productos;
import com.QF.Almacen_backend.Entidades.ProductosDanados;
import com.QF.Almacen_backend.Entidades.Usuarios;
import com.QF.Almacen_backend.Repositorios.RepositoriosProductosDanados;
import com.QF.Almacen_backend.Repositorios.RepsitorioProductos;
import com.QF.Almacen_backend.Repositorios.RepsitorioUsuarios;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductosDanadosService {

    private final RepositoriosProductosDanados productosDanadosRepository;
    private final RepsitorioProductos productosRepository;
    private final RepsitorioUsuarios usuariosRepository;
    private final MovimientosService movimientosService;

    public ProductosDanadosService(RepositoriosProductosDanados productosDanadosRepository,
                                   RepsitorioProductos productosRepository,
                                   RepsitorioUsuarios usuariosRepository,
                                   MovimientosService movimientosService) {
        this.productosDanadosRepository = productosDanadosRepository;
        this.productosRepository = productosRepository;
        this.usuariosRepository = usuariosRepository;
        this.movimientosService = movimientosService;
    }

    // RF-19: Registrar mercancía dañada/mermada y descontarla del stock disponible
    @Transactional
    public void registrarProductoDanado(ProductosDanadoRequestDTO dto) {
        if (dto.cantidad() == null || dto.cantidad() <= 0) {
            throw new RuntimeException("La cantidad dañada debe ser mayor a cero.");
        }

        Productos producto = productosRepository.findById(dto.idProducto())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        if (producto.getStock() == null || producto.getStock() < dto.cantidad()) {
            throw new RuntimeException("No se puede reportar más unidades dañadas que el stock disponible ("
                    + (producto.getStock() != null ? producto.getStock() : 0) + ").");
        }

        Usuarios usuario = null;
        if (dto.idUsuario() != null) {
            usuario = usuariosRepository.findById(dto.idUsuario())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        }

        // 1. Registramos el reporte de daño
        ProductosDanados reporte = new ProductosDanados();
        reporte.setProductos(producto);
        reporte.setUsuarios(usuario);
        reporte.setCantidad(dto.cantidad());
        reporte.setMotivo(dto.motivo());
        reporte.setObservaciones(dto.observaciones());
        productosDanadosRepository.save(reporte);

        // 2. La mercancía dañada deja de estar disponible como stock vendible
        producto.setStock(producto.getStock() - dto.cantidad());
        productosRepository.save(producto);

        // 3. RF-18: Dejamos rastro auditable en el historial de movimientos
        movimientosService.registrarMovimiento(
                Movimientos.TipoMovimiento.MERMA,
                dto.cantidad(),
                producto,
                null,
                null,
                usuario
        );
    }

    public List<ProductoDanadoResponseDTO> listarReportes() {
        return productosDanadosRepository.findAll().stream()
                .map(r -> new ProductoDanadoResponseDTO(
                        r.getId(),
                        r.getProductos() != null ? r.getProductos().getNombre() : "PRODUCTO DESCONOCIDO",
                        r.getUsuarios() != null ? r.getUsuarios().getNombre() : "SISTEMA",
                        r.getCantidad(),
                        r.getMotivo(),
                        r.getObservaciones(),
                        r.getFechaReporte()
                ))
                .toList();
    }
}
