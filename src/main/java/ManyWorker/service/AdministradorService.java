package ManyWorker.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ManyWorker.entity.Administrador;
import ManyWorker.entity.Mensaje;
import ManyWorker.entity.Patrocinador;
import ManyWorker.entity.Roles;
import ManyWorker.repository.AdministradorRepository;
import ManyWorker.security.JWTUtils;
import jakarta.transaction.Transactional;

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

    public void adminPorDefecto() {
		if (this.getAllAdmins().size() <= 0) {
			Administrador defaultAdmin = new Administrador();
			defaultAdmin.setUsername("admin");
			defaultAdmin.setPassword(passwordEncoder.encode("1234"));
			defaultAdmin.setNombre("admin");
			defaultAdmin.setPrimerApellido("Admin");
			defaultAdmin.setSegundoApellido("Admin");
			defaultAdmin.setEmail("admin@default.com");
			defaultAdmin.setTelefono("623456789");
			defaultAdmin.setDireccion("Dirección por defecto");
			defaultAdmin.setFoto("http://default.png");
			defaultAdmin.setRol(Roles.ADMINISTRADOR);

			System.out.println("Usuario Admin creado por defecto");
			adminRepository.save(defaultAdmin);
		}
	}
}
