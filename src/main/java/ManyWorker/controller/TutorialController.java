package ManyWorker.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ManyWorker.entity.Tutorial;
import ManyWorker.service.TutorialService;

@RestController
@RequestMapping("/tutorial")
public class TutorialController {

	    @Autowired
	    private TutorialService tutorialService;

	    //Crear un tutorial
	    @PostMapping
	    public Tutorial crearTutorial(@RequestBody Tutorial turorial) {
	        return tutorialService.crearTutorial(turorial);
	    }

	    //Editar un tutorial
	    @PutMapping("/{id}")
	    public Tutorial editarTutorial(@PathVariable int id, @RequestBody Tutorial nuevosDatos) {
	        return tutorialService.editarTutorial(id, nuevosDatos);
	    }

	    //Listar un tutorial
	    @GetMapping
	    public List<Tutorial> listarTutorial() {
	        return tutorialService.listarTutoriales();
	    }

	    //Eliminar un tutorial
	    @DeleteMapping("/{id}")
	    public void eliminarTutorial(@PathVariable int id) {
	    	tutorialService.eliminarTutorial(id);
	    }

	    //Obtener un Tutorial
	    @GetMapping("/{id}")
	    public Optional<Tutorial> obtenerTutorial(@PathVariable int id) {
	        return tutorialService.obtenerTutorialPorId(id);
	    }
}
