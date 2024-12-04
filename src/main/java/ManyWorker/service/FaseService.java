package ManyWorker.service;

import ManyWorker.entity.Fase;
import ManyWorker.repository.FaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FaseService {

    @Autowired
    private FaseRepository faseRepository;

    // Crear un nuevo perfil social
    public Fase crearFase(Fase Fase) {
        // Asegúrate de que el actor esté asignado
        if (Fase.getPlanTrabajo() == null) {
            throw new IllegalArgumentException("La fase debe tener un plan de trabajo asignado.");
        }
        return faseRepository.save(Fase);
    }


    // Editar un perfil social
    public Fase editarFase(int faseId, Fase nuevosDatos) {
        Optional<Fase> faseOptional = faseRepository.findById(faseId);

        if (faseOptional.isPresent()) {
            Fase faseExistente = faseOptional.get();
            faseExistente.setTitulo(nuevosDatos.getTitulo());
            faseExistente.setDescripcion(nuevosDatos.getDescripcion());
            faseExistente.setFechaInicio(nuevosDatos.getFechaInicio());
            faseExistente.setFechaFin(nuevosDatos.getFechaFin());
            faseExistente.setPlanTrabajo(nuevosDatos.getPlanTrabajo());
            return faseRepository.save(faseExistente);
        } else {
            throw new RuntimeException("Fase no encontrada");
        }
    }

    // Listar todos los perfiles sociales
    public List<Fase> listarFases() {
        return faseRepository.findAll();
    }

    // Eliminar un perfil social
    public void eliminarFase(int faseId) {
        faseRepository.deleteById(faseId);
    }

    // Obtener un perfil social por ID
    public Optional<Fase> obtenerFasePorId(int faseId) {
        return faseRepository.findById(faseId);
    }
}