package ManyWorker.controller;

import ManyWorker.entity.Actor;
import ManyWorker.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/actores")
public class ActorController {

    @Autowired
    private ActorService actorService;

    @PostMapping("/registro")
    public Actor registrarActor(@RequestBody Actor actor) {
        return actorService.registrarActor(actor);
    }

    @PostMapping("/autenticacion")
    public Optional<Actor> autenticarActor(@RequestBody LoginRequest loginRequest) {
        return actorService.autenticarActor(loginRequest.getEmail(), loginRequest.getPassword());
    }

    @PutMapping("/editar/{id}")
    public Actor editarPerfil(@PathVariable int id, @RequestBody Actor nuevosDatos) {
        return actorService.editarPerfil(id, nuevosDatos);
    }

    @GetMapping
    public List<Actor> listarActores() {
        return actorService.listarActores();
    }

    @GetMapping("/{id}")
    public Optional<Actor> obtenerActorPorId(@PathVariable int id) {
        return actorService.obtenerActorPorId(id);
    }
}

class LoginRequest {
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
