package ManyWorker.controller;

import ManyWorker.entity.Fase;
import ManyWorker.service.FaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/fase")
@Tag(name = "Fase", description = "Operaciones relacionadas con la gestión de las fases de los planes de trabajo")
public class FaseController {

    @Autowired
    private FaseService faseService;

    @GetMapping("/{planTrabajoId}")
    @Operation(summary = "Obtener todas las fases de un plan de trabajo")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Fases obtenidas exitosamente"),
        @ApiResponse(responseCode = "403", description = "No tienes permisos para acceder a las fases de este plan"),
        @ApiResponse(responseCode = "404", description = "Plan de trabajo no encontrado")
    })
    public ResponseEntity<Set<Fase>> listarFases(@PathVariable int planTrabajoId) {
        try {
            Set<Fase> fases = faseService.listarFases(planTrabajoId);
            return ResponseEntity.ok(fases);
        } catch (IllegalStateException | IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/{planTrabajoId}")
    @Operation(summary = "Crear una nueva fase en un plan de trabajo")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Fase creada exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos o restricciones de negocio no cumplidas"),
        @ApiResponse(responseCode = "403", description = "No tienes permisos para añadir fases a este plan de trabajo"),
        @ApiResponse(responseCode = "404", description = "Plan de trabajo no encontrado")
    })
    public ResponseEntity<String> crearFase(@RequestBody Fase fase, @PathVariable int planTrabajoId) {
        try {
            Fase nuevaFase = faseService.crearFase(fase, planTrabajoId);
            return ResponseEntity.status(HttpStatus.CREATED).body("Fase creada correctamente con ID: " + nuevaFase.getId());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{faseId}")
    @Operation(summary = "Actualizar una fase existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Fase actualizada exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos o restricciones de negocio no cumplidas"),
        @ApiResponse(responseCode = "403", description = "No tienes permisos para editar esta fase"),
        @ApiResponse(responseCode = "404", description = "Fase no encontrada")
    })
    public ResponseEntity<String> editarFase(@PathVariable int faseId, @RequestBody Fase nuevosDatos) {
        try {
            Fase faseActualizada = faseService.editarFase(faseId, nuevosDatos);
            return ResponseEntity.ok("Fase actualizada correctamente con ID: " + faseActualizada.getId());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{faseId}")
    @Operation(summary = "Eliminar una fase")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "202", description = "Fase eliminada exitosamente"),
        @ApiResponse(responseCode = "403", description = "No tienes permisos para eliminar esta fase"),
        @ApiResponse(responseCode = "404", description = "Fase no encontrada")
    })
    public ResponseEntity<String> eliminarFase(@PathVariable int faseId) {
        try {
            faseService.eliminarFase(faseId);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Fase eliminada correctamente");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
