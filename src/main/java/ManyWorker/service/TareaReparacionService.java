package ManyWorker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ManyWorker.entity.TareaReparacion;
import ManyWorker.repository.TareaReparacionRepository;

@Service
public class TareaReparacionService {

	@Autowired
	private TareaReparacionRepository tareaRepository;
	
	 // Crear una nueva tareaReparacion
    public TareaReparacion crearTareaReparacion(TareaReparacion tareaReparacion) {
        return tareaRepository.save(tareaReparacion);
    }
    
	//Eliminar una TareaReparacion
    public void eliminarTareaReparacion(int mensajeId) {
    	tareaRepository.deleteById(mensajeId);
    }
    
    //Listar todas las tareaReparacion
    public List<TareaReparacion> listarTareaReparacion() {
        return tareaRepository.findAll();
    }
}
