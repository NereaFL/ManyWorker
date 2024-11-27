package ManyWorker.service;

import ManyWorker.entity.Solicitud;
import ManyWorker.repository.SolicitudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SolicitudService {

    @Autowired
    private SolicitudRepository solicitudRepository;

    // Registrar una nueva Solicitud
    public Solicitud registrarSolicitud(Solicitud solicitud) {
        return solicitudRepository.save(solicitud);
    }

    // Editar una Solicitud existente
    public Solicitud editarSolicitud(int id, Solicitud nuevosDatos) {
        Optional<Solicitud> solicitudOptional = solicitudRepository.findById(id);

        if (solicitudOptional.isPresent()) {
            Solicitud solicitudExistente = solicitudOptional.get();
            solicitudExistente.setFechaHora(nuevosDatos.getFechaHora());
            solicitudExistente.setPrecio(nuevosDatos.getPrecio());
            solicitudExistente.setComentario(nuevosDatos.getComentario());
            solicitudExistente.setEstado(nuevosDatos.getEstado());
            solicitudExistente.setTrabajador(nuevosDatos.getTrabajador());
            solicitudExistente.setPlanTrabajo(nuevosDatos.getPlanTrabajo());
            return solicitudRepository.save(solicitudExistente);
        } else {
            throw new RuntimeException("Solicitud no encontrada");
        }
    }

    // Listar todas las Solicitudes
    public List<Solicitud> listarSolicitudes() {
        return solicitudRepository.findAll();
    }

    // Obtener una Solicitud por ID
    public Optional<Solicitud> obtenerSolicitudPorId(int id) {
        return solicitudRepository.findById(id);
    }

    // Eliminar una Solicitud
    public void eliminarSolicitud(int id) {
        if (solicitudRepository.existsById(id)) {
            solicitudRepository.deleteById(id);
        } else {
            throw new RuntimeException("Solicitud no encontrada");
        }
    }
}
