package ManyWorker.controller;

import ManyWorker.entity.Mensaje;
import ManyWorker.service.MensajeService;
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
@RequestMapping("/mensaje")
@Tag(name = "Mensaje", description = "Operaciones relacionadas con la gestión de mensajes")
public class MensajeController {

    @Autowired
    private MensajeService mensajeService;

    @GetMapping("/deTrabajador")
    @Operation(summary = "Obtener todos los mensajes del trabajador autenticado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Mensajes del trabajador obtenidos exitosamente"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<Set<Mensaje>> getAllMensajesByTrabajador() {
        Set<Mensaje> mensajes = mensajeService.getAllMensajesByTrabajador();
        return ResponseEntity.ok(mensajes);
    }

    @GetMapping("/deCliente")
    @Operation(summary = "Obtener todos los mensajes del cliente autenticado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Mensajes del cliente obtenidos exitosamente"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<Set<Mensaje>> getAllMensajesByCliente() {
        Set<Mensaje> mensajes = mensajeService.getAllMensajesByCliente();
        return ResponseEntity.ok(mensajes);
    }

    @GetMapping("/deAdministrador")
    @Operation(summary = "Obtener todos los mensajes del administrador autenticado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Mensajes del administrador obtenidos exitosamente"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<Set<Mensaje>> getAllMensajesByAdministrador() {
        Set<Mensaje> mensajes = mensajeService.getAllMensajesByAdministrador();
        return ResponseEntity.ok(mensajes);
    }

    @GetMapping("/dePatrocinador")
    @Operation(summary = "Obtener todos los mensajes del patrocinador autenticado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Mensajes del patrocinador obtenidos exitosamente"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<Set<Mensaje>> getAllMensajesByPatrocinador() {
        Set<Mensaje> mensajes = mensajeService.getAllMensajesByPatrocinador();
        return ResponseEntity.ok(mensajes);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar un mensaje por ID asociado al usuario autenticado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Mensaje encontrado"),
        @ApiResponse(responseCode = "404", description = "Mensaje no encontrado o no asociado al usuario")
    })
    public ResponseEntity<Mensaje> getMensajeById(@PathVariable int id) {
        Mensaje mensaje = mensajeService.getMensajeById(id);
        if (mensaje == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            return ResponseEntity.ok(mensaje);
        }
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo mensaje")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Mensaje creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Error al crear el mensaje")
    })
    public ResponseEntity<Mensaje> saveMensaje(@RequestBody Mensaje mensaje) {
        Mensaje nuevoMensaje = mensajeService.saveMensaje(mensaje);
        if (nuevoMensaje == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoMensaje);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un mensaje por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "202", description = "Mensaje eliminado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Error al eliminar el mensaje")
    })
    public ResponseEntity<String> deleteMensaje(@PathVariable int id) {
        Mensaje mensaje = mensajeService.getMensajeById(id);
        if (mensaje == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mensaje no encontrado o no autorizado");
        }
        mensajeService.deleteMensaje(mensaje);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Mensaje eliminado correctamente");
    }
    
    @PostMapping("/broadcast")
    public ResponseEntity<String> enviarBroadcast(@RequestBody Mensaje mensaje) {
        mensajeService.enviarMensajeBroadcast(mensaje);
        return ResponseEntity.ok("Mensaje enviado a todos los actores.");
    }
}
