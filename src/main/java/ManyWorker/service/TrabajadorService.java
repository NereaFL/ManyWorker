package ManyWorker.service;

import ManyWorker.entity.Trabajador;
import ManyWorker.repository.TrabajadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrabajadorService {

    @Autowired
    private TrabajadorRepository trabajadorRepository;

    // Registrar un nuevo Trabajador
    public Trabajador registrarTrabajador(Trabajador trabajador) {
        return trabajadorRepository.save(trabajador);
    }

    // Editar un Trabajador existente
    public Trabajador editarTrabajador(int id, Trabajador nuevosDatos) {
        Optional<Trabajador> trabajadorOptional = trabajadorRepository.findById(id);

        if (trabajadorOptional.isPresent()) {
            Trabajador trabajadorExistente = trabajadorOptional.get();
            trabajadorExistente.setNombreComercial(nuevosDatos.getNombreComercial());
            trabajadorExistente.setCurriculos(nuevosDatos.getCurriculos());
            trabajadorExistente.setTutoriales(nuevosDatos.getTutoriales());
            return trabajadorRepository.save(trabajadorExistente);
        } else {
            throw new RuntimeException("Trabajador no encontrado");
        }
    }

    // Listar todos los Trabajadores
    public List<Trabajador> listarTrabajadores() {
        return trabajadorRepository.findAll();
    }

    // Obtener un Trabajador por ID
    public Optional<Trabajador> obtenerTrabajadorPorId(int id) {
        return trabajadorRepository.findById(id);
    }

    // Eliminar un Trabajador
    public void eliminarTrabajador(int id) {
        if (trabajadorRepository.existsById(id)) {
            trabajadorRepository.deleteById(id);
        } else {
            throw new RuntimeException("Trabajador no encontrado");
        }
    }
}
