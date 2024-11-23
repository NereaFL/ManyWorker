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
        return mensajeRepository.save(mensaje);
    }

    // Listar mensajes por usuario (enviados y recibidos)
    public List<Mensaje> listarMensajesPorUsuario(int usuarioId) {
        return mensajeRepository.findByRemitenteIdOrDestinatarioId(usuarioId, usuarioId);
    }

    // Eliminar un mensaje
    public void eliminarMensaje(int mensajeId) {
        mensajeRepository.deleteById(mensajeId);
    }

    // Obtener un mensaje por ID
    public Optional<Mensaje> obtenerMensajePorId(int mensajeId) {
        return mensajeRepository.findById(mensajeId);
    }
}
