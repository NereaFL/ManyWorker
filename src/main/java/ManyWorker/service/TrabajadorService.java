package ManyWorker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import ManyWorker.entity.Trabajador;
import ManyWorker.repository.TrabajadorRepository;
import jakarta.transaction.Transactional;

public class TrabajadorService {

    @Autowired
    private TrabajadorRepository trabajadorRepository;

    @Transactional
    public Trabajador saveTrabajador(Trabajador trabajador) {
        return trabajadorRepository.save(trabajador);
    }

    @Transactional
    public Trabajador updateTrabajador(int id, Trabajador trabajador) {
        Optional<Trabajador> trabajadorO = trabajadorRepository.findById(id);
        if (trabajadorO.isPresent()) {
            trabajadorO.get().setNombre(trabajador.getNombre());
            trabajadorO.get().setPrimerApellido(trabajador.getPrimerApellido());
            trabajadorO.get().setNombreComercial(trabajador.getNombreComercial());
            trabajadorO.get().setCurriculos(trabajador.getCurriculos());
            trabajadorO.get().setTutoriales(trabajador.getTutoriales());
            return trabajadorRepository.save(trabajadorO.get());
        }
        return null;
    }

    public List<Trabajador> getAllTrabajadores() {
        return trabajadorRepository.findAll();
    }

    public Optional<Trabajador> getTrabajadorById(int id) {
        return trabajadorRepository.findById(id);
    }

    @Transactional
    public boolean deleteTrabajador(int id) {
        if (trabajadorRepository.existsById(id)) {
            trabajadorRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
