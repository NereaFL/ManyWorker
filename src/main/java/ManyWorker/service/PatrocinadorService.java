package ManyWorker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ManyWorker.entity.Patrocinador;
import ManyWorker.entity.Roles;
import ManyWorker.repository.PatrocinadorRepository;
import ManyWorker.security.JWTUtils;
import jakarta.transaction.Transactional;

@Service
public class PatrocinadorService {
	@Autowired
    private PatrocinadorRepository patrocinadorRepository;
    
    @Autowired
	private PasswordEncoder passwordEncoder;
    
    private JWTUtils JWTUtils;
    
    @Transactional
    public Patrocinador savePatrocinador(Patrocinador patrocinador) {
    	patrocinador.setRol(Roles.PATROCINADOR);
    	patrocinador.setPassword(passwordEncoder.encode(patrocinador.getPassword()));
        return patrocinadorRepository.save(patrocinador);
    }

    @Transactional
    public Patrocinador updatePatrocinador(Patrocinador patrocinador) {
    	Patrocinador patrocinador0 = JWTUtils.userLogin();
		if (patrocinador != null) {
        	patrocinador0.setNombre(patrocinador.getNombre());
        	patrocinador0.setPrimerApellido(patrocinador.getPrimerApellido());
        	patrocinador0.setSegundoApellido(patrocinador.getSegundoApellido());
        	patrocinador0.setFoto(patrocinador.getFoto());
			patrocinador0.setEmail(patrocinador.getEmail());
			patrocinador0.setTelefono(patrocinador.getTelefono());
			patrocinador0.setDireccion(patrocinador.getDireccion());
            return patrocinadorRepository.save(patrocinador0);
        }
        return null;
    }

    public List<Patrocinador> getAllPatrocinadores() {
        return patrocinadorRepository.findAll();
    }

    public Optional<Patrocinador> getPatrocinadorById(int id) {
        return patrocinadorRepository.findById(id);
    }

	public Optional<Patrocinador> findByUsername(String username) {
		return patrocinadorRepository.findByUsername(username);
	}

    @Transactional
    public boolean deletePatrocinador() {
    	Patrocinador patrocinador = JWTUtils.userLogin();
		if (patrocinador != null) {
			patrocinadorRepository.deleteById(patrocinador.getId());
            return true;
        }
        return false;
    }

}
