package ManyWorker.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import ManyWorker.entity.Administrador;
import ManyWorker.service.AdministradorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Controller
@RequestMapping("/administrador")
public class AdministradorController {

    @Autowired
    private AdministradorService adminService;

    @GetMapping
    @Operation(summary = "Obtener todos los administradores")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de administradores obtenida exitosamente"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<List<Administrador>> getAllAdmins() {
        List<Administrador> admins = adminService.getAllAdmins();
        return ResponseEntity.ok(admins);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar un administrador por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Administrador encontrado"),
        @ApiResponse(responseCode = "404", description = "Administrador no encontrado")
    })
    public ResponseEntity<Administrador> findOneAdmin(@PathVariable int id) {
        Optional<Administrador> admin = adminService.getAdminById(id);
        if (admin.isPresent()) {
            return ResponseEntity.ok(admin.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

	@ApiResponses(value = {
		    @ApiResponse(responseCode = "201", description = "Administrador creado exitosamente"),
		    @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
		    @ApiResponse(responseCode = "409", description = "El username ya está en uso")
	})
	public ResponseEntity<String> saveSocio(@RequestBody Administrador admin) {
		if (adminService.findByUsername(admin.getUsername()).isPresent()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El username ya está en uso");
		} else {
			Administrador a = adminService.saveAdmin(admin);
			if (a != null) {
				return ResponseEntity.status(HttpStatus.CREATED).body("Administrador creado exitosamente");
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo crear el administrador");
			}
		}
	}

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un administrador existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Administrador actualizado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Administrador no encontrado"),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida, el ID no puede ser nulo o administrador no válido")
    })
    public ResponseEntity<String> updateAdmin(@PathVariable int id, @RequestBody Administrador updatedAdmin) {
        Administrador response = adminService.updateAdmin(id, updatedAdmin);
        if (response != null) {
            return ResponseEntity.status(HttpStatus.OK).body("Administrador actualizado exitosamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Administrador no encontrado");
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un administrador por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Administrador eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Administrador no encontrado")
    })
    public ResponseEntity<String> deleteAdmin(@PathVariable int id) {
        Optional<Administrador> a = adminService.getAdminById(id);
        if (a.isPresent()) {
            adminService.deleteAdmin(id);
            return ResponseEntity.status(HttpStatus.OK).body("Administrador eliminado exitosamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Administrador no encontrado");
        }
    }
}
