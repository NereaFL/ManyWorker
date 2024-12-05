package ManyWorker.controller;

import java.util.List;
import java.util.Set;

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

import ManyWorker.entity.Solicitud;
import ManyWorker.entity.TareaReparacion;
import ManyWorker.service.TareaReparacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/tareaReparacion")
public class TareaReparacionController {

	@Autowired
    private TareaReparacionService tareaReparacionService;
	
	@GetMapping("/deCliente")
    @Operation(summary = "Obtener todas las tareas de reparacion de cliente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de solicitudes de cliente obtenida exitosamente"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<Set<TareaReparacion>> getAllTareasReparacionByCliente() {
    	Set<TareaReparacion> listSolicitud = tareaReparacionService.getAllTareasReparacionesByCliente();
        return ResponseEntity.ok(listSolicitud);
    }
	
	@GetMapping("/{id}")
    @Operation(summary = "Buscar una tarea de reparacion por ID filtrado por emisor y receptor propietario")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Solicitud encontrada"),
        @ApiResponse(responseCode = "404", description = "Solicitud no encontrada, o emisor/receptor no logueado")
    })
    public ResponseEntity<TareaReparacion> findOneSolicitud(@PathVariable int id) {
        TareaReparacion tareaReparacion = tareaReparacionService.getTareaReparacionById(id);
        if (tareaReparacion == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            return ResponseEntity.ok(tareaReparacion);
        }
    }
	
	//Crear una Tarea Reparacion 
	@PostMapping
    public TareaReparacion crearTareaReparacion(@RequestBody TareaReparacion tareaReparacion) {
        return tareaReparacionService.crearTareaReparacion(tareaReparacion);
    }
	
	//Editar la Tarea Reparacion
	@PutMapping("/{id}")
    public TareaReparacion editarTareaReparacion(@PathVariable int id, @RequestBody TareaReparacion nuevosDatos) {
        return tareaReparacionService.editarTareaReparacion(id, nuevosDatos);
    }
	  
	//Eliminar una Tarea Reparacion 
	@DeleteMapping("/{id}")
    public void eliminarTareaReparacion(@PathVariable int id) {
		tareaReparacionService.eliminarTareaReparacion(id);
    }
	
	//Listar una Tarea Reparacion 
	@GetMapping
    public List<TareaReparacion> listarTareaReparacion() {
        return tareaReparacionService.listarTareaReparacion();
    }
}
