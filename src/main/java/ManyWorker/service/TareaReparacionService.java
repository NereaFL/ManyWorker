package ManyWorker.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ManyWorker.entity.Cliente;
import ManyWorker.entity.Solicitud;
import ManyWorker.entity.TareaReparacion;
import ManyWorker.entity.Trabajador;
import ManyWorker.repository.TareaReparacionRepository;
import ManyWorker.security.JWTUtils;

@Service
public class TareaReparacionService {

	@Autowired
	private TareaReparacionRepository tareaRepository;
	
	private JWTUtils JWTUtils;

	public Set<TareaReparacion> getAllTareasReparacionesByCliente() {
		Cliente cliente = JWTUtils.userLogin();
		return cliente.getTareasReparacion();
	}
	
	public TareaReparacion getTareaReparacionById(int id) {
		Optional<TareaReparacion> tareaReparacionO = tareaRepository.findById(id);
		if (tareaReparacionO.isPresent()) {
			Object userLogin = JWTUtils.userLogin();
			if (userLogin instanceof Cliente) {
				Cliente cliente = (Cliente) userLogin;
				cliente.getSolicitudes().contains(tareaReparacionO.get());
				return tareaReparacionO.get();
			}
		}
		return null;
	}
	
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
