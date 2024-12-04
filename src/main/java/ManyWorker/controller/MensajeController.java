package ManyWorker.controller;

import ManyWorker.entity.Mensaje;
import ManyWorker.entity.PerfilSocial;
import ManyWorker.service.MensajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mensaje")
public class MensajeController {

    @Autowired
    private MensajeService mensajeService;

    @PostMapping
    public Mensaje enviarMensaje(@RequestBody Mensaje mensaje) {
        return mensajeService.enviarMensaje(mensaje);
    }
    
    @GetMapping
    public List<Mensaje> listarMensajes() {
        return mensajeService.listarMensajes();
    }

    @GetMapping("/actor/{actorId}")
    public List<Mensaje> listarMensajesPorActor(@PathVariable int actorId) {
        return mensajeService.listarMensajesPorActor(actorId);
    }
    
    @PutMapping("/{id}")
    public Mensaje editarMensaje(@PathVariable int id, @RequestBody Mensaje nuevosDatos) {
        return mensajeService.editarMensaje(id, nuevosDatos);
    }

    @DeleteMapping("/{id}")
    public void eliminarMensaje(@PathVariable int id) {
        mensajeService.eliminarMensaje(id);
    }

    @GetMapping("/{id}")
    public Optional<Mensaje> obtenerMensajePorId(@PathVariable int id) {
        return mensajeService.obtenerMensajePorId(id);
    }
}
