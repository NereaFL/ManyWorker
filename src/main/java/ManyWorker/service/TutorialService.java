package ManyWorker.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ManyWorker.entity.Trabajador;
import ManyWorker.entity.Tutorial;
import ManyWorker.repository.TutorialRepository;
import ManyWorker.security.JWTUtils;

@Service
public class TutorialService {

    @Autowired
    private TutorialRepository tutorialRepository;

    @Autowired
    private JWTUtils jwtUtils;

    // Validar que el usuario autenticado sea un Trabajador
    private Trabajador validarUsuarioEsTrabajador() {
        Object userLogin = jwtUtils.userLogin();
        if (userLogin instanceof Trabajador) {
            return (Trabajador) userLogin;
        } else {
            throw new IllegalStateException("Solo los trabajadores pueden gestionar tutoriales.");
        }
    }

    // Listar todos los tutoriales
    public List<Tutorial> listarTutoriales() {
        return tutorialRepository.findAll();
    }

    // Obtener un tutorial por ID
    public Tutorial obtenerTutorialPorId(int tutorialId) {
        Optional<Tutorial> tutorialOptional = tutorialRepository.findById(tutorialId);

        if (tutorialOptional.isPresent()) {
            return tutorialOptional.get();
        } else {
            throw new RuntimeException("Tutorial no encontrado");
        }
    }

    // Crear un nuevo tutorial (solo Trabajadores)
    @Transactional
    public Tutorial crearTutorial(Tutorial tutorial) {
        Trabajador trabajador = validarUsuarioEsTrabajador();

        tutorial.setFechaHoraActualizacion(LocalDateTime.now());
        tutorial.setFechaHoraActualizacion(LocalDateTime.now());
        tutorial.setTrabajador(trabajador);
        return tutorialRepository.save(tutorial);
    }

    // Editar un tutorial existente (solo Trabajadores)
    @Transactional
    public Tutorial editarTutorial(int tutorialId, Tutorial nuevosDatos) {
        Trabajador trabajador = validarUsuarioEsTrabajador();

        Optional<Tutorial> tutorialOptional = tutorialRepository.findById(tutorialId);

        if (tutorialOptional.isPresent()) {
            Tutorial tutorialExistente = tutorialOptional.get();

            if (!tutorialExistente.getTrabajador().equals(trabajador)) {
                throw new IllegalArgumentException("No tiene permiso para editar este tutorial.");
            }

            tutorialExistente.setTitulo(nuevosDatos.getTitulo());
            tutorialExistente.setResumen(nuevosDatos.getResumen());
            tutorialExistente.setImagen(nuevosDatos.getImagen());
            tutorialExistente.setTextoTutorial(nuevosDatos.getTextoTutorial());
            tutorialExistente.setFechaHoraActualizacion(LocalDateTime.now());

            return tutorialRepository.save(tutorialExistente);
        } else {
            throw new RuntimeException("Tutorial no encontrado");
        }
    }

    // Eliminar un tutorial (solo Trabajadores)
    @Transactional
    public void eliminarTutorial(int tutorialId) {
        Trabajador trabajador = validarUsuarioEsTrabajador();

        Optional<Tutorial> tutorialOptional = tutorialRepository.findById(tutorialId);

        if (tutorialOptional.isPresent()) {
            Tutorial tutorial = tutorialOptional.get();

            if (!tutorial.getTrabajador().equals(trabajador)) {
                throw new IllegalArgumentException("No tiene permiso para eliminar este tutorial.");
            }

            tutorialRepository.deleteById(tutorialId);
        } else {
            throw new RuntimeException("Tutorial no encontrado para eliminar");
        }
    }
}
