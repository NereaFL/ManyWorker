package ManyWorker.examen;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;

import ManyWorker.entity.Administrador;
import ManyWorker.entity.DomainEntity;
import jakarta.persistence.Entity;

public class Examen {
	
	// Crear un usuario
	
	/*
	 * 1. Se crea la entidad en ManyWorker.entity
	 * 
	 * @Entity
	public class nombreActor extends Actor{
		
		// propiedades Ejemplo:
		 * private String propiedad;
		 * 
		 * relaciones: 
		 * 
		 * De uno a muchos:
		 *  @OneToMany
		 *  private Entidad entidad;
		 *  
		 *  @ManyToOne
		 *  private List<Entidad> entidad;
	
		// CONSTRUCTOR, GETTER Y SETTERS ( source generate Constructur using fields y soruce generate getters and setters)
	}
	

@Service
public class AdministradorService {
	@Autowired
    private AdministradorRepository adminRepository;
    
    @Autowired
	private PasswordEncoder passwordEncoder;
    
    private JWTUtils JWTUtils;
    
    @Transactional
    public Administrador saveAdmin(Administrador admin) {
    	admin.setRol(Roles.ADMINISTRADOR);
    	admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        return adminRepository.save(admin);
    }

    @Transactional
    public Administrador updateAdmin(Administrador admin) {
    	Administrador adminO = JWTUtils.userLogin();
		if (admin != null) {
        	adminO.setNombre(admin.getNombre());
        	adminO.setPrimerApellido(admin.getPrimerApellido());
        	adminO.setSegundoApellido(admin.getSegundoApellido());
        	adminO.setFoto(admin.getFoto());
			adminO.setEmail(admin.getEmail());
			adminO.setTelefono(admin.getTelefono());
			adminO.setDireccion(admin.getDireccion());
			.....
            return adminRepository.save(adminO);
        }
        return null;
    }

    public List<Administrador> getAllAdmins() {
        return adminRepository.findAll();
    }

    public Optional<Administrador> getAdminById(int id) {
        return adminRepository.findById(id);
    }

	public Optional<Administrador> findByUsername(String username) {
		return adminRepository.findByUsername(username);
	}

    @Transactional
    public boolean deleteAdmin() {
    	Administrador administrador = JWTUtils.userLogin();
		if (administrador != null) {
			adminRepository.deleteById(administrador.getId());
            return true;
        }
        return false;
    }
}


@RestController
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
	public ResponseEntity<String> saveAdmin(@RequestBody Administrador admin) {
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

    @PutMapping
    @Operation(summary = "Actualizar un administrador existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Administrador actualizado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Administrador no encontrado"),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida, el ID no puede ser nulo o administrador no válido")
    })
    public ResponseEntity<String> updateAdmin(@RequestBody Administrador updatedAdmin) {
        Administrador response = adminService.updateAdmin(updatedAdmin);
        if (response != null) {
            return ResponseEntity.status(HttpStatus.OK).body("Administrador actualizado exitosamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Administrador no encontrado");
        }
    }

    @DeleteMapping
    @Operation(summary = "Eliminar un administrador")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Administrador eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Administrador no encontrado")
    })
    public ResponseEntity<String> deleteAdmin() {
        if (adminService.deleteAdmin()) {
            return ResponseEntity.status(HttpStatus.OK).body("Administrador eliminado exitosamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Administrador no encontrado");
        }
    }
}

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Integer> {
	public Optional<Administrador> findByUsername(String username);

}

// SECURIDAD: Vas a JWTUtils.java
 * Método de userLogin, abajo
 * 
 * Copiais uno de ellos, como este: 
 * case ADMINISTRADOR:
					Optional<Administrador> adminOptional = administradorService.findByUsername(username);
					if (adminOptional.isPresent()) {
						res = (T) adminOptional.get();
					}
					break;
			
			Y modificais donde ponga ADMINISTRADOR, dentro del codigo tambien para el nuevo actor
	
	
	En SecurityConfiguration
	// PATROCINADOR
				.requestMatchers(HttpMethod.GET, "/patrocinador").permitAll()
				.requestMatchers(HttpMethod.GET, "/patrocinador/{id}").permitAll()
				.requestMatchers(HttpMethod.POST, "/patrocinador").permitAll()
				.requestMatchers(HttpMethod.PUT, "/patrocinador").hasAnyAuthority("PATROCINADOR")
				.requestMatchers(HttpMethod.DELETE, "/patrocinador").hasAuthority("PATROCINADOR")

}*/
	
	
	/*
	 * 
	 * RUTAS:
	 * 
	 * 1º Vais al controlador de donde este la ruta pedida y lo cambiais segun se haya dicho
	 * 2ºVais a la seguridad y vais a la clase de SecurityConfiguration y buscais la ruta que habeis cambiado en el controlador y la cambiais también ahi
	 * 
	 * Si piden crear una, creas el servicio y el controlador y después poneis la ruta nueva en la misma clase de seguridad
	 * poniendo hashAutority si solo lo hace 1. hasAnyAutoeity para más de un actor pero no todos, permitAll en caso de que lo permitan todos y un authenticated para
	 * solo actores autenticados
	 * 
	 * 
	 */
	
	
	/**
	 * 
	 * Si pide volcar datos en una base de datos os vais a CategoriaDaraInitializer que esta en las entidades y cambiais para la entidad que os pida
	 */
	
	/*
	 * PERMISOS DE SEGURIDAD EN SecurityConfigurarion
	 * hashAutority si solo lo hace 1. hasAnyAutoeity para más de un actor pero no todos, permitAll en caso de que lo permitan todos y un authenticated para
	 * solo actores autenticados
	 */
	 

}
