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

    // Crear una nueva solicitud
    public Solicitud crearSolicitud(Solicitud solicitud) {
        if (solicitud.getFechaHora() == null || solicitud.getFechaHora().isEmpty()) {
            throw new IllegalArgumentException("La fecha y hora de la solicitud no pueden estar vacías.");
        }
        if (solicitud.getPrecio() <= 0) {
            throw new IllegalArgumentException("El precio de la solicitud debe ser mayor que cero.");
        }
        if (solicitud.getComentario() == null || solicitud.getComentario().isEmpty()) {
            throw new IllegalArgumentException("El comentario de la solicitud no puede estar vacío.");
        }
        if (solicitud.getEstado() == null || !(solicitud.getEstado().equals("PENDIENTE") || solicitud.getEstado().equals("ACEPTADO") || solicitud.getEstado().equals("RECHAZADO"))) {
            throw new IllegalArgumentException("El estado de la solicitud debe ser uno de los valores válidos: PENDIENTE, ACEPTADO, RECHAZADO.");
        }
        return solicitudRepository.save(solicitud);
    }

    // Editar una solicitud
    public Solicitud editarSolicitud(int solicitudId, Solicitud nuevosDatos) {
        Optional<Solicitud> solicitudOptional = solicitudRepository.findById(solicitudId);

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

    // Listar todas las solicitudes
    public List<Solicitud> listarSolicitudes() {
        return solicitudRepository.findAll();
    }

    // Eliminar una solicitud
    public void eliminarSolicitud(int solicitudId) {
        solicitudRepository.deleteById(solicitudId);
    }

    // Obtener una solicitud por ID
    public Optional<Solicitud> obtenerSolicitudPorId(int solicitudId) {
        return solicitudRepository.findById(solicitudId);
    }
}
