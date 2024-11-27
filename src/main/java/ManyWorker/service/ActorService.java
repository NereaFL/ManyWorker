package ManyWorker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ManyWorker.entity.Actor;
import ManyWorker.entity.Trabajador;
import ManyWorker.repository.ActorRepository;

@Service
public class ActorService {

    @Autowired
    private ActorRepository actorRepository;

    // Registrar un nuevo actor (cliente o trabajador)
    public Actor registrarActor(Actor actor) {
        // Verificar si ya existe un actor con el mismo email
        Optional<Actor> existente = actorRepository.findByEmail(actor.getEmail());
        if (existente.isPresent()) {
            throw new RuntimeException("Ya existe un actor registrado con el email: " + actor.getEmail());
        }
        
        if(actor instanceof Trabajador) {
        	Trabajador trabajador = (Trabajador) actor;
        	if(trabajador.getNombreComercial() == null || trabajador.getNombreComercial().isEmpty()) {
        		trabajador.setNombreComercial(trabajador.getNombre() + " " + trabajador.getPrimerApellido());
        	}
        }
        
        return actorRepository.save(actor);
    }

    // Autenticar un actor (implementar lógica de autenticación según tus necesidades)
    public Optional<Actor> autenticarActor(String email, String password) {
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
