package com.QF.Almacen_backend.Servicios;

import com.QF.Almacen_backend.Dto.EntradaResponseDTO;
import com.QF.Almacen_backend.Dto.EntradasRequestDTO;
import com.QF.Almacen_backend.Entidades.*;
import com.QF.Almacen_backend.Repositorios.RepositoriosEntradas;
import com.QF.Almacen_backend.Repositorios.RepsitorioAlmacenes;
import com.QF.Almacen_backend.Repositorios.RepsitorioProductos;
import com.QF.Almacen_backend.Repositorios.RepsitorioProveedores;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EntradasService {
    private final RepositoriosEntradas entradasRepository;
    private final RepsitorioProductos productosRepository;
    private final RepsitorioProveedores proveedoresRepository;
    private final RepsitorioAlmacenes almacenesRepository;
    private final MovimientosService movimientosService;

    public EntradasService(RepositoriosEntradas entradasRepository,
                           RepsitorioProductos productosRepository,
                           RepsitorioProveedores proveedoresRepository,
                           RepsitorioAlmacenes almacenesRepository,
                           MovimientosService movimientosService) {
        this.entradasRepository = entradasRepository;
        this.productosRepository = productosRepository;
        this.proveedoresRepository = proveedoresRepository;
        this.almacenesRepository = almacenesRepository;
        this.movimientosService = movimientosService;
    }

    // RF-16: Registrar entrada de inventario (ej. compras) y sumar al stock
    @Transactional
    public void registrarEntrada(EntradasRequestDTO dto) {
        if (dto.cantidad() == null || dto.cantidad() <= 0) {
            throw new RuntimeException("La cantidad de la entrada debe ser mayor a cero.");
        }

        Productos producto = productosRepository.findById(dto.idProducto())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        Proveedores proveedor = null;
        if (dto.idProveedor() != null) {
            proveedor = proveedoresRepository.findById(dto.idProveedor())
                    .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));
        }

        Almacenes almacen = null;
        if (dto.idAlmacen() != null) {
            almacen = almacenesRepository.findById(Long.valueOf(dto.idAlmacen()))
                    .orElseThrow(() -> new RuntimeException("Almacén no encontrado"));
        }

        // 1. Registramos la entrada
        Entradas entrada = new Entradas();
        entrada.setProductos(producto);
        entrada.setProveedores(proveedor);
        entrada.setAlmacen(almacen);
        entrada.setCantidad(dto.cantidad());
        entrada.setObservacion(dto.observacion());
        entradasRepository.save(entrada);

        // 2. La entrada suma unidades al stock del producto
        producto.setStock(producto.getStock() + dto.cantidad());
        productosRepository.save(producto);

        // 3. RF-18: Dejamos rastro auditable en el historial de movimientos
        movimientosService.registrarMovimiento(
                Movimientos.TipoMovimiento.ENTRADA,
                dto.cantidad(),
                producto,
                null,
                almacen,
                null
        );
    }

    public List<EntradaResponseDTO> listarEntradas() {
        return entradasRepository.findAll().stream()
                .map(e -> new EntradaResponseDTO(
                        e.getIdEntrada(),
                        e.getProductos() != null ? e.getProductos().getNombre() : "PRODUCTO DESCONOCIDO",
                        e.getProveedores() != null ? e.getProveedores().getNombre() : "SIN PROVEEDOR",
                        e.getAlmacen() != null ? e.getAlmacen().getNombre() : "SIN ALMACÉN",
                        e.getCantidad(),
                        e.getObservacion(),
                        e.getFechaEntrada()
                ))
                .toList();
    }
}
