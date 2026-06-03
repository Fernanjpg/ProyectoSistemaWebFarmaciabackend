package com.QF.Almacen_backend.Servicios;

import com.QF.Almacen_backend.Dto.SalidasRequestDTO;
import com.QF.Almacen_backend.Dto.SalidasResponseDTO;
import com.QF.Almacen_backend.Entidades.Productos;
import com.QF.Almacen_backend.Entidades.PuntosVentas;
import com.QF.Almacen_backend.Entidades.Salidas;
import com.QF.Almacen_backend.Repositorios.RepositorioSalidas;
import com.QF.Almacen_backend.Repositorios.RepositoriosPuntosVenta;
import com.QF.Almacen_backend.Repositorios.RepsitorioProductos;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SalidasService {

    private final RepositorioSalidas salidasRepository;
    private final RepsitorioProductos productosRepository;
    private final RepositoriosPuntosVenta puntoVentaRepository;


    public SalidasService(RepositorioSalidas salidasRepository, RepsitorioProductos productosRepository, RepositoriosPuntosVenta puntoVentaRepository) {
        this.salidasRepository = salidasRepository;
        this.productosRepository = productosRepository;
        this.puntoVentaRepository = puntoVentaRepository;
    }

    @Transactional
    public void registrarSalida(SalidasRequestDTO dto) {
        Productos producto = productosRepository.findById(dto.idProducto())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        PuntosVentas puntoVenta = puntoVentaRepository.findById(dto.idPuntoVenta())
                .orElseThrow(() -> new RuntimeException("Punto de venta destino no encontrado"));


        if (producto.getStock() < dto.cantidad()) {
            throw new RuntimeException("Stock insuficiente. Solo quedan " + producto.getStock() + " unidades.");
        }

        // 1. Guardamos la salida
        Salidas salida = new Salidas();
        salida.setProductos(producto);
        salida.setPuntosVenta(puntoVenta);
        salida.setCantidad(dto.cantidad());
        salida.setMotivo(dto.motivo());
        salida.setFechaSalida(LocalDateTime.now());
        salidasRepository.save(salida);


        producto.setStock(producto.getStock() - dto.cantidad());
        productosRepository.save(producto);

    }

    public List<SalidasResponseDTO> listarSalidas() {
        return salidasRepository.findAll().stream()
                .map(s -> new SalidasResponseDTO(
                        s.getIdSalida(),
                        s.getProductos() != null ? s.getProductos().getNombre() : "PRODUCTO DESCONOCIDO",
                        s.getPuntosVenta() != null ? s.getPuntosVenta().getNombre() : "DESTINO INTERNO",
                        s.getCantidad(),
                        s.getMotivo(),
                        s.getFechaSalida()
                ))
                .toList();





}
}
