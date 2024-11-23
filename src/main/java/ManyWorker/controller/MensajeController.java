package ManyWorker.controller;

import ManyWorker.entity.Mensaje;
import ManyWorker.service.MensajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mensajes")
public class MensajeController {

    @Autowired
    private MensajeService mensajeService;

    @PostMapping("/enviar")
    public Mensaje enviarMensaje(@RequestBody Mensaje mensaje) {
        return mensajeService.enviarMensaje(mensaje);
    }

    @GetMapping("/usuario/{id}")
    public List<Mensaje> listarMensajesPorUsuario(@PathVariable int id) {
        return mensajeService.listarMensajesPorUsuario(id);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarMensaje(@PathVariable int id) {
        mensajeService.eliminarMensaje(id);
    }

    @GetMapping("/{id}")
    public Optional<Mensaje> obtenerMensajePorId(@PathVariable int id) {
        return mensajeService.obtenerMensajePorId(id);
    }
}
