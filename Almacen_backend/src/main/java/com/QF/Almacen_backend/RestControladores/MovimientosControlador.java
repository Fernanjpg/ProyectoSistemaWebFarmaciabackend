package com.QF.Almacen_backend.RestControladores;

import com.QF.Almacen_backend.Dto.MovimientoDTO;
import com.QF.Almacen_backend.Entidades.Movimientos;
import com.QF.Almacen_backend.Repositorios.RepsitorioMovimientos;
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

    @Autowired
    private RepsitorioMovimientos repsitorioMovimientos;

    // GET - Listar todos los movimientos como DTO
    @GetMapping("/Listar")
    public List<MovimientoDTO> listar() {
        return repsitorioMovimientos.findAll()
                .stream()
                .map(MovimientoDTO::new)
                .collect(Collectors.toList());
    }

    // GET - Filtrar por tipo: ENTRADA, SALIDA, TRANSFERENCIA
    @GetMapping("/ListarPorTipo/{tipo}")
    public ResponseEntity<?> listarPorTipo(@PathVariable String tipo) {
        try {
            Movimientos.TipoMovimiento tipoEnum = Movimientos.TipoMovimiento.valueOf(tipo.toUpperCase());

            List<MovimientoDTO> resultado = repsitorioMovimientos.findAll()
                    .stream()
                    .filter(m -> m.getTipo() == tipoEnum)
                    .map(MovimientoDTO::new)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(resultado);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Tipo inválido. Use: ENTRADA, SALIDA o TRANSFERENCIA");
        }
    }

    // POST - Guardar un nuevo movimiento
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