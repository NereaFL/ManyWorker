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

import ManyWorker.entity.Patrocinador;
import ManyWorker.service.PatrocinadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/patrocinador")
public class PatrocinadorController {

    @Autowired
    private PatrocinadorService patrocinadorService;

    @GetMapping
    @Operation(summary = "Obtener todos los patrocinadores")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de patrocinadores obtenida exitosamente"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<List<Patrocinador>> getAllPatrocinadores() {
        List<Patrocinador> patrocinadores = patrocinadorService.getAllPatrocinadores();
        return ResponseEntity.ok(patrocinadores);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar un Patrocinador por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Patrocinador encontrado"),
        @ApiResponse(responseCode = "404", description = "Patrocinador no encontrado")
    })
    public ResponseEntity<Patrocinador> findOnePatrocinador(@PathVariable int id) {
        Optional<Patrocinador> patrocinador = patrocinadorService.getPatrocinadorById(id);
        if (patrocinador.isPresent()) {
            return ResponseEntity.ok(patrocinador.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
	@ApiResponses(value = {
		    @ApiResponse(responseCode = "201", description = "Patrocinador creado exitosamente"),
		    @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
		    @ApiResponse(responseCode = "409", description = "El username ya está en uso")
	})
	public ResponseEntity<String> savePatrocinador(@RequestBody Patrocinador patrocinador) {
		if (patrocinadorService.findByUsername(patrocinador.getUsername()).isPresent()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El username ya está en uso");
		} else {
			Patrocinador a = patrocinadorService.savePatrocinador(patrocinador);
			if (a != null) {
				return ResponseEntity.status(HttpStatus.CREATED).body("Patrocinador creado exitosamente");
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo crear el Patrocinador");
			}
		}
	}

    @PutMapping
    @Operation(summary = "Actualizar un Patrocinador existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Patrocinador actualizado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Patrocinador no encontrado"),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida, el ID no puede ser nulo o Patrocinador no válido")
    })
    public ResponseEntity<String> updatePatrocinador(@RequestBody Patrocinador updatedPatrocinador) {
        Patrocinador response = patrocinadorService.updatePatrocinador(updatedPatrocinador);
        if (response != null) {
            return ResponseEntity.status(HttpStatus.OK).body("Patrocinador actualizado exitosamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patrocinador no encontrado");
        }
    }

    @DeleteMapping
    @Operation(summary = "Eliminar un Patrocinador")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Patrocinador eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Patrocinador no encontrado")
    })
    public ResponseEntity<String> deletePatrocinador() {
        if (patrocinadorService.deletePatrocinador()) {
            return ResponseEntity.status(HttpStatus.OK).body("Patrocinador eliminado exitosamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patrocinador no encontrado");
        }
    }
}