package com.QF.Almacen_backend.RestControladores;

import com.QF.Almacen_backend.Dto.ReporteRequestDTO;
import com.QF.Almacen_backend.Entidades.Productos;
import com.QF.Almacen_backend.Entidades.Reportes;
import com.QF.Almacen_backend.Repositorios.RepositoriosReportes;
import com.QF.Almacen_backend.Repositorios.RepsitorioProductos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reportes")
@CrossOrigin(origins = "http://localhost:5173") // Permite conexión con tu frontend en Vite
public class ReportesController {

    @Autowired
    private RepositoriosReportes repositoriosReportes;
    @Autowired
    private RepsitorioProductos repsitorioProductos;

    @PostMapping("/guardar")
    public ResponseEntity<?> registrarReporte(@RequestBody ReporteRequestDTO reporteDTO) {
        try {
            Reportes reporte = new Reportes();
            reporte.setTipo(reporteDTO.getTipo());
            reporte.setDescripcion(reporteDTO.getDescripcion());
            reporte.setUsuario(reporteDTO.getUsuario());
            Productos prod = repsitorioProductos.findByNombre(reporteDTO.getProducto());
            reporte.setProducto(prod);
            Reportes guardado = repositoriosReportes.save(reporte);

            return new ResponseEntity<>(guardado, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error en la base de datos: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listar")
    public List<Reportes> listarTodos() {
        return repositoriosReportes.findAll();
    }
}