package ManyWorker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ManyWorker.entity.Roles;
import ManyWorker.entity.Trabajador;
import ManyWorker.repository.TrabajadorRepository;
import ManyWorker.security.JWTUtils;
import jakarta.transaction.Transactional;

@Service
public class TrabajadorService {

    @Autowired
    private TrabajadorRepository trabajadorRepository;
    
    @Autowired
	private PasswordEncoder passwordEncoder;
    
    private JWTUtils JWTUtils;

    @Transactional
    public Trabajador saveTrabajador(Trabajador trabajador) {
    	trabajador.setRol(Roles.TRABAJADOR);
    	trabajador.setPassword(passwordEncoder.encode(trabajador.getPassword()));
        return trabajadorRepository.save(trabajador);
    }

    @Transactional
    public Trabajador updateTrabajador(Trabajador trabajador) {
    	Trabajador trabajadorO = JWTUtils.userLogin();
		if (trabajador != null) {
            trabajadorO.setNombre(trabajador.getNombre());
            trabajadorO.setPrimerApellido(trabajador.getPrimerApellido());
            trabajadorO.setNombreComercial(trabajador.getNombreComercial());
            trabajadorO.setCurriculos(trabajador.getCurriculos());
            trabajadorO.setTutoriales(trabajador.getTutoriales());
            return trabajadorRepository.save(trabajadorO);
        }
        return null;
    }

    public List<Trabajador> getAllTrabajadores() {
        return trabajadorRepository.findAll();
    }

    public Optional<Trabajador> getTrabajadorById(int id) {
        return trabajadorRepository.findById(id);
    }
    
    public Optional<Trabajador> findByUsername(String username) {
		return trabajadorRepository.findByUsername(username);
	} 


    @Transactional
    public boolean deleteTrabajador() {
    	Trabajador trabajador = JWTUtils.userLogin();
		if (trabajador != null) {
			trabajadorRepository.deleteById(trabajador.getId());
            return true;
        }
        return false;
    }
}
