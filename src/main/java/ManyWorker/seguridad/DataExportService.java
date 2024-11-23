package ManyWorker.seguridad;

import ManyWorker.entity.Actor;
import ManyWorker.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DataExportService {

    @Autowired
    private ActorRepository actorRepository;

    // Exportar datos del usuario
    public Optional<Actor> exportarDatosUsuario(int actorId) {
        // Busca y devuelve los datos del actor por su ID
        return actorRepository.findById(actorId);
    }
}
