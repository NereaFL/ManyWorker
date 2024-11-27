package ManyWorker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ManyWorker.entity.TareaReparacion;
import ManyWorker.service.TareaReparacionService;

@RestController
@RequestMapping("/tareaReparacion")
public class TareaReparacionController {

	@Autowired
    private TareaReparacionService tareaReparacionService;
	
	@PostMapping("/crear")
    public TareaReparacion crearTareaReparacion(@RequestBody TareaReparacion tareaReparacion) {
        return tareaReparacionService.crearTareaReparacion(tareaReparacion);
    }
	
	@DeleteMapping("/eliminar/{id}")
    public void eliminarTareaReparacion(@PathVariable int id) {
		tareaReparacionService.eliminarTareaReparacion(id);
    }
	
	@GetMapping
    public List<TareaReparacion> listarTareaReparacion() {
        return tareaReparacionService.listarTareaReparacion();
    }
}
