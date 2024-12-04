package ManyWorker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import ManyWorker.entity.Patrocinador;
import ManyWorker.entity.Roles;
import ManyWorker.repository.PatrocinadorRepository;
import jakarta.transaction.Transactional;

public class PatrocinadorService {
	@Autowired
    private PatrocinadorRepository patrocinadorRepository;
    
    @Autowired
	private PasswordEncoder passwordEncoder;
    
    @Transactional
    public Patrocinador savePatrocinador(Patrocinador patrocinador) {
    	patrocinador.setRol(Roles.PATROCINADOR);
    	patrocinador.setPassword(passwordEncoder.encode(patrocinador.getPassword()));
        return patrocinadorRepository.save(patrocinador);
    }

    @Transactional
    public Patrocinador updatePatrocinador(int id, Patrocinador patrocinador) {
        Optional<Patrocinador> patrocinador0 = patrocinadorRepository.findById(id);
        if (patrocinador0.isPresent()) {
        	patrocinador0.get().setNombre(patrocinador.getNombre());
        	patrocinador0.get().setPrimerApellido(patrocinador.getPrimerApellido());
        	patrocinador0.get().setSegundoApellido(patrocinador.getSegundoApellido());
        	patrocinador0.get().setFoto(patrocinador.getFoto());
			patrocinador0.get().setEmail(patrocinador.getEmail());
			patrocinador0.get().setTelefono(patrocinador.getTelefono());
			patrocinador0.get().setDireccion(patrocinador.getDireccion());
            return patrocinadorRepository.save(patrocinador0.get());
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
    public boolean deletePatrocinador(int id) {
        if (patrocinadorRepository.existsById(id)) {
            patrocinadorRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
