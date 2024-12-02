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
	
	// Crear una nueva TareaReparacion
    public TareaReparacion crearTareaReparacion(TareaReparacion tareaReparacion) {
        return tareaRepository.save(tareaReparacion);
    }
    
	//Eliminar una TareaReparacion
    public void eliminarTareaReparacion(int mensajeId) {
    	tareaRepository.deleteById(mensajeId);
    }
    
    //Listar todas las TareaReparacion
    public List<TareaReparacion> listarTareaReparacion() {
        return tareaRepository.findAll();
    }
    
    // Editar una TareaReparacion
    public TareaReparacion editarTareaReparacion(int id, TareaReparacion nuevosDatos) {
        Optional<TareaReparacion> tareaRepositorio = tareaRepository.findById(id);

        if (tareaRepositorio.isPresent()) {
            TareaReparacion tareaExistente = tareaRepositorio.get();
            tareaExistente.setCatalogoGarantia(nuevosDatos.getCatalogoGarantia());
            tareaExistente.setClienteId(nuevosDatos.getClienteId());
            tareaExistente.setCategoria(nuevosDatos.getCategoria());
            tareaExistente.setDescripcion(nuevosDatos.getDescripcion());
            tareaExistente.setDireccion(nuevosDatos.getDireccion());
            tareaExistente.setFechaFinalizacion(nuevosDatos.getFechaFinalizacion());
            tareaExistente.setFechaHora(nuevosDatos.getFechaHora());
            tareaExistente.setPrecioMaximo(nuevosDatos.getPrecioMaximo());
            return tareaRepository.save(tareaExistente);
        } else {
            throw new RuntimeException("Tarea Reparacion no encontrado");
        }
    }
}
