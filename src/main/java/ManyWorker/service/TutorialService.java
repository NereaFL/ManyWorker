package ManyWorker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ManyWorker.entity.Tutorial;
import ManyWorker.repository.TutorialRepository;

@Service
public class TutorialService {

    @Autowired
    private TutorialRepository tutorialRepository;

    // Crear un nuevo tutorial
    public Tutorial crearTutorial(Tutorial tutorial) {
        return tutorialRepository.save(tutorial);
    }

    // Editar un tutorial existente
    public Tutorial editarTutorial(Long tutorialId, Tutorial nuevosDatos) {
        Optional<Tutorial> tutorialOptional = tutorialRepository.findById(tutorialId);

        if (tutorialOptional.isPresent()) {
            Tutorial tutorialExistente = tutorialOptional.get();
            tutorialExistente.setTitulo(nuevosDatos.getTitulo());
            tutorialExistente.setFechaHoraActualizacion(nuevosDatos.getFechaHoraActualizacion());
            tutorialExistente.setResumen(nuevosDatos.getResumen());
            tutorialExistente.setImagen(nuevosDatos.getImagen());
            tutorialExistente.setTextoTutorial(nuevosDatos.getTextoTutorial());
            return tutorialRepository.save(tutorialExistente);
        } else {
            throw new RuntimeException("Tutorial no encontrado");
        }
    }

    // Listar todos los tutoriales
    public List<Tutorial> listarTutoriales() {
        return tutorialRepository.findAll();
    }

    // Obtener un tutorial por ID
    public Optional<Tutorial> obtenerTutorialPorId(Long tutorialId) {
        return tutorialRepository.findById(tutorialId);
    }

    // Eliminar un tutorial
    public void eliminarTutorial(Long tutorialId) {
        if (tutorialRepository.existsById(tutorialId)) {
            tutorialRepository.deleteById(tutorialId);
        } else {
            throw new RuntimeException("Tutorial no encontrado para eliminar");
        }
    }
}
