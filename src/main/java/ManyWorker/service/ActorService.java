package ManyWorker.service;

import ManyWorker.entity.Actor;
import ManyWorker.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActorService {

    @Autowired
    private ActorRepository actorRepository;

    // Registrar un nuevo actor (cliente o trabajador)
    public Actor registrarActor(Actor actor) {
        return actorRepository.save(actor);
    }

    // Autenticar un actor (implementar lógica de autenticación según tus necesidades)
    public Optional<Actor> autenticarActor(String email, String password) {
        // Ejemplo simple de autenticación, busca por email y un campo ficticio de password
        Optional<Actor> actor = actorRepository.findByEmail(email);
        if (actor.isPresent() && actor.get().getPassword().equals(password)) {
            return actor;
        }
        return Optional.empty();
    }

    // Editar datos del perfil de un actor
    public Actor editarPerfil(int actorId, Actor nuevosDatos) {
        Optional<Actor> actorOptional = actorRepository.findById(actorId);

        if (actorOptional.isPresent()) {
            Actor actorExistente = actorOptional.get();
            actorExistente.setNombre(nuevosDatos.getNombre());
            actorExistente.setPrimerApellido(nuevosDatos.getPrimerApellido());
            actorExistente.setSegundoApellido(nuevosDatos.getSegundoApellido());
            actorExistente.setFoto(nuevosDatos.getFoto());
            actorExistente.setEmail(nuevosDatos.getEmail());
            actorExistente.setTelefono(nuevosDatos.getTelefono());
            actorExistente.setDireccion(nuevosDatos.getDireccion());
            actorExistente.setNumeroPerfilesSociales(nuevosDatos.getNumeroPerfilesSociales());
            actorExistente.setPerfilesSociales(nuevosDatos.getPerfilesSociales());
            return actorRepository.save(actorExistente);
        } else {
            throw new RuntimeException("Actor no encontrado");
        }
    }

    // Listar todos los actores
    public List<Actor> listarActores() {
        return actorRepository.findAll();
    }

    // Obtener un actor por ID
    public Optional<Actor> obtenerActorPorId(int actorId) {
        return actorRepository.findById(actorId);
    }
}
