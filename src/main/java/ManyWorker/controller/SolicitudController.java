package ManyWorker.controller;

import ManyWorker.entity.Solicitud;
import ManyWorker.service.SolicitudService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/solicitud")
@Tag(name = "Solicitud", description = "Operaciones relacionadas con la gestión de las solicitudes")
public class SolicitudController {

    @Autowired
    private SolicitudService solicitudService;

    @GetMapping("/deTrabajador")
    @Operation(summary = "Obtener todas las solicitudes de trabajador")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de solicitudes de trabajador obtenida exitosamente"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<Set<Solicitud>> getAllSolicitudesByTrabajador() {
    	Set<Solicitud> listSolicitud = solicitudService.getAllSolicitudesByTrabajador();
        return ResponseEntity.ok(listSolicitud);
    }
    
    @GetMapping("/deCliente")
    @Operation(summary = "Obtener todas las solicitudes de cliente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de solicitudes de cliente obtenida exitosamente"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<Set<Solicitud>> getAllSolicitudesByCliente() {
    	Set<Solicitud> listSolicitud = solicitudService.getAllSolicitudesByCliente();
        return ResponseEntity.ok(listSolicitud);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar una solicitud por ID filtrado por emisor y receptor propietario")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Solicitud encontrada"),
        @ApiResponse(responseCode = "404", description = "Solicitud no encontrada, o emisor/receptor no logueado")
    })
    public ResponseEntity<Solicitud> findOneSolicitud(@PathVariable int id) {
        Solicitud solicitud = solicitudService.getSolicitudById(id);
        if (solicitud == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            return ResponseEntity.ok(solicitud);
        }
    }

    @GetMapping("/accept/{id}")
    @Operation(summary = "Aceptar una solicitud por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "202", description = "Solicitud aceptada exitosamente"),
        @ApiResponse(responseCode = "400", description = "Error al aceptar la solicitud")
    })
    public ResponseEntity<String> acceptSolicitud(@PathVariable int id) {
        Boolean verEstado = solicitudService.acceptSolicitud(id);
        if (verEstado == false) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al aceptar la solicitud");
        } else {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Solicitud aceptada correctamente");
        }
    }

    @GetMapping("/refuse/{id}")
    @Operation(summary = "Rechazar una solicitud por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "202", description = "Solicitud rechazada exitosamente"),
        @ApiResponse(responseCode = "400", description = "Error al rechazar la solicitud")
    })
    public ResponseEntity<String> refuseSolicitud(@PathVariable int id) {
        Boolean verEstado = solicitudService.refuseSolicitud(id);
        if (verEstado == false) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al rechazar la solicitud");
        } else {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Solicitud rechazada correctamente");
        }
    }

    @PostMapping("/{idCliente}")
    @Operation(summary = "Crear una nueva solicitud para un ayuntamiento")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "202", description = "Solicitud creada exitosamente"),
        @ApiResponse(responseCode = "400", description = "Error al crear la solicitud")
    })
    public ResponseEntity<String> save(@RequestBody Solicitud s, @PathVariable int idAyunt) {
        Solicitud solicitudSave = solicitudService.save(s, idAyunt);
        if (solicitudSave == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al crear la solicitud");
        } else {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Solicitud creada correctamente");
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una solicitud por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "202", description = "Solicitud eliminada exitosamente"),
        @ApiResponse(responseCode = "400", description = "Error al borrar la solicitud")
    })
    public ResponseEntity<String> delete(@PathVariable int id) {
        Boolean verEstadoBorrado = solicitudService.deleteSolicitud(id);
        if (verEstadoBorrado == false) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al borrar la solicitud");
        } else {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Solicitud borrada correctamente");
        }
    }
}