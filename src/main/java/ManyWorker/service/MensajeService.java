package ManyWorker.service;

import ManyWorker.entity.Mensaje;
import ManyWorker.repository.MensajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MensajeService {

    @Autowired
    private MensajeRepository mensajeRepository;

    // Enviar un mensaje
    public Mensaje enviarMensaje(Mensaje mensaje) {
        mensaje.setActorBorradoId(null);
        return mensajeRepository.save(mensaje);
    }
    
    public List<Mensaje> listarMensajes() {
        return mensajeRepository.findAll();
    }

    // Listar mensajes por actor
    public List<Mensaje> listarMensajesPorActor(int actorId) {
        return mensajeRepository.findByRemitente_IdOrDestinatario_Id(actorId, actorId);
    }

    // Eliminar un mensaje
    public void eliminarMensaje(int id) {
        mensajeRepository.deleteById(id);
    }

    // Obtener un mensaje por ID
    public Optional<Mensaje> obtenerMensajePorId(int id) {
        return mensajeRepository.findById(id);
    }
}
