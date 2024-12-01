package ManyWorker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import ManyWorker.entity.Administrador;
import ManyWorker.entity.Roles;
import ManyWorker.repository.AdministradorRepository;
import jakarta.transaction.Transactional;

public class AdministradorService {
	@Autowired
    private AdministradorRepository adminRepository;
    
    @Autowired
	private PasswordEncoder passwordEncoder;
    
    @Transactional
    public Administrador saveAdmin(Administrador admin) {
    	admin.setRol(Roles.ADMINISTRADOR);
    	admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        return adminRepository.save(admin);
    }

    @Transactional
    public Administrador updateAdmin(int id, Administrador admin) {
        Optional<Administrador> adminO = adminRepository.findById(id);
        if (adminO.isPresent()) {
        	adminO.get().setNombre(admin.getNombre());
        	adminO.get().setPrimerApellido(admin.getPrimerApellido());
        	adminO.get().setSegundoApellido(admin.getSegundoApellido());
        	adminO.get().setFoto(admin.getFoto());
			adminO.get().setEmail(admin.getEmail());
			adminO.get().setTelefono(admin.getTelefono());
			adminO.get().setDireccion(admin.getDireccion());
            return adminRepository.save(adminO.get());
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
    public boolean deleteAdmin(int id) {
        if (adminRepository.existsById(id)) {
            adminRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
