package com.QF.Almacen_backend.RestControladores;

import com.QF.Almacen_backend.Dto.MovimientoDTO;
import com.QF.Almacen_backend.Entidades.Movimientos;
import com.QF.Almacen_backend.Repositorios.RepsitorioMovimientos;
import com.QF.Almacen_backend.Servicios.MovimientosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/movimientos")
@CrossOrigin(origins = "http://localhost:5173")
public class MovimientosControlador {
    private final MovimientosService movimientosService;
    private final RepsitorioMovimientos repsitorioMovimientos;

    public MovimientosControlador(MovimientosService movimientosService, RepsitorioMovimientos repsitorioMovimientos) {
        this.movimientosService = movimientosService;
        this.repsitorioMovimientos = repsitorioMovimientos;
    }
    // GET - Listar todos los movimientos como DTO (RF-18)
    @GetMapping("/Listar")
    public List<MovimientoDTO> listar() {
        return movimientosService.listar();
    }

    // GET - Filtrar por tipo: ENTRADA, SALIDA, TRANSFERENCIA, MERMA
    @GetMapping("/ListarPorTipo/{tipo}")
    public ResponseEntity<?> listarPorTipo(@PathVariable String tipo) {
        try {
            return ResponseEntity.ok(movimientosService.listarPorTipo(tipo));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // GET - Historial auditable de un producto específico (RF-18)
    @GetMapping("/ListarPorProducto/{idProducto}")
    public ResponseEntity<List<MovimientoDTO>> listarPorProducto(@PathVariable Integer idProducto) {
        return ResponseEntity.ok(movimientosService.listarPorProducto(idProducto));
    }

    // POST - Guardar un nuevo movimiento manual (ej. transferencias entre almacenes)
    @PostMapping("/Guardar")
    public ResponseEntity<?> guardar(@RequestBody Movimientos movimiento) {
        try {
            Movimientos guardado = repsitorioMovimientos.save(movimiento);
            return ResponseEntity.status(HttpStatus.CREATED).body(new MovimientoDTO(guardado));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al guardar el movimiento: " + e.getMessage());
        }
    }

    // DELETE - Eliminar por id
    @DeleteMapping("/Eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        if (!repsitorioMovimientos.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Movimiento no encontrado con id: " + id);
        }
        repsitorioMovimientos.deleteById(id);
        return ResponseEntity.ok("Movimiento eliminado correctamente");
    }

}