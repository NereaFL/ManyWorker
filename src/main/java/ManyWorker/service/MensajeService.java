package ManyWorker.service;

import ManyWorker.entity.Mensaje;
import ManyWorker.entity.PerfilSocial;
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
    
    public Mensaje editarMensaje(int mensajeId, Mensaje nuevosDatos) {
        Optional<Mensaje> mensajeOptional = mensajeRepository.findById(mensajeId);

        if (mensajeOptional.isPresent()) {
            Mensaje mensajeExistente = mensajeOptional.get();
            mensajeExistente.setRemitente(nuevosDatos.getRemitente());;
            mensajeExistente.setDestinatario(nuevosDatos.getDestinatario());
            mensajeExistente.setFechaHora(nuevosDatos.getFechaHora());
            mensajeExistente.setAsunto(nuevosDatos.getAsunto());
            mensajeExistente.setCuerpo(nuevosDatos.getCuerpo());
            mensajeExistente.setActorBorradoId(nuevosDatos.getActorBorradoId());
            return mensajeRepository.save(mensajeExistente);
        } else {
            throw new RuntimeException("Perfil social no encontrado");
        }
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
