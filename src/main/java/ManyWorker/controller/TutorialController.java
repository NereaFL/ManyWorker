package ManyWorker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ManyWorker.entity.Tutorial;
import ManyWorker.service.TutorialService;

@RestController
@RequestMapping("/api/tutoriales")
public class TutorialController {

    @Autowired
    private TutorialService tutorialService;

    // Listar todos los tutoriales
    @GetMapping
    public ResponseEntity<List<Tutorial>> listarTutoriales() {
        List<Tutorial> tutoriales = tutorialService.listarTutoriales();
        return ResponseEntity.ok(tutoriales);
    }

    // Obtener un tutorial por ID
    @GetMapping("/{id}")
    public ResponseEntity<Tutorial> obtenerTutorialPorId(@PathVariable int id) {
        try {
            Tutorial tutorial = tutorialService.obtenerTutorialPorId(id);
            return ResponseEntity.ok(tutorial);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear un nuevo tutorial (solo trabajadores)
    @PostMapping
    public ResponseEntity<Tutorial> crearTutorial(@RequestBody Tutorial tutorial) {
        try {
            Tutorial nuevoTutorial = tutorialService.crearTutorial(tutorial);
            return ResponseEntity.ok(nuevoTutorial);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(403).body(null); // Prohibido
        }
    }

    // Editar un tutorial existente (solo trabajadores)
    @PutMapping("/{id}")
    public ResponseEntity<Tutorial> editarTutorial(
            @PathVariable int id, 
            @RequestBody Tutorial nuevosDatos) {
        try {
            Tutorial tutorialActualizado = tutorialService.editarTutorial(id, nuevosDatos);
            return ResponseEntity.ok(tutorialActualizado);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(403).body(null); // Prohibido
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un tutorial (solo trabajadores)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTutorial(@PathVariable int id) {
        try {
            tutorialService.eliminarTutorial(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.status(403).build(); // Prohibido
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
