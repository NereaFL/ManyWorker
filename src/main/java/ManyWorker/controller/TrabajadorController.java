package ManyWorker.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ManyWorker.entity.Trabajador;
import ManyWorker.service.TrabajadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/trabajador")
public class TrabajadorController {

    @Autowired
    private TrabajadorService trabajadorService;

    @GetMapping
    @Operation(summary = "Obtener todos los trabajadores")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de trabajadores obtenida exitosamente"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<List<Trabajador>> getAllTrabajadores() {
        List<Trabajador> trabajadores = trabajadorService.getAllTrabajadores();
        return ResponseEntity.ok(trabajadores);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar un trabajador por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Trabajador encontrado"),
        @ApiResponse(responseCode = "404", description = "Trabajador no encontrado")
    })
    public ResponseEntity<Trabajador> findOneTrabajador(@PathVariable int id) {
        Optional<Trabajador> trabajador = trabajadorService.getTrabajadorById(id);
        if (trabajador.isPresent()) {
            return ResponseEntity.ok(trabajador.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    @Operation(summary = "Crear un trabajador nuevo")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Trabajador creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    public ResponseEntity<String> saveTrabajador(@RequestBody Trabajador trabajador) {
        Trabajador t = trabajadorService.saveTrabajador(trabajador);
        if (t != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Trabajador creado exitosamente");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo crear el trabajador");
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un trabajador existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Trabajador actualizado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Trabajador no encontrado"),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida, el ID no puede ser nulo o trabajador no válido")
    })
    public ResponseEntity<String> updateTrabajador(@PathVariable int id, @RequestBody Trabajador updatedTrabajador) {
        Trabajador response = trabajadorService.updateTrabajador(id, updatedTrabajador);
        if (response != null) {
            return ResponseEntity.status(HttpStatus.OK).body("Trabajador actualizado exitosamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Trabajador no encontrado");
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un trabajador por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Trabajador eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Trabajador no encontrado")
    })
    public ResponseEntity<String> deleteTrabajador(@PathVariable int id) {
        Optional<Trabajador> t = trabajadorService.getTrabajadorById(id);
        if (t.isPresent()) {
            trabajadorService.deleteTrabajador(id);
            return ResponseEntity.status(HttpStatus.OK).body("Trabajador eliminado exitosamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Trabajador no encontrado");
        }
    }
}
