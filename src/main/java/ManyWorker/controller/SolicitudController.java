package ManyWorker.controller;

import ManyWorker.entity.Solicitud;
import ManyWorker.service.SolicitudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/solicitud")
public class SolicitudController {

    @Autowired
    private SolicitudService solicitudService;

    @PostMapping
    public Solicitud crearSolicitud(@RequestBody Solicitud solicitud) {
        return solicitudService.crearSolicitud(solicitud);
    }

    @PutMapping("/{id}")
    public Solicitud editarSolicitud(@PathVariable int id, @RequestBody Solicitud nuevosDatos) {
        return solicitudService.editarSolicitud(id, nuevosDatos);
    }

    @GetMapping
    public List<Solicitud> listarSolicitudes() {
        return solicitudService.listarSolicitudes();
    }

    @DeleteMapping("/{id}")
    public void eliminarSolicitud(@PathVariable int id) {
        solicitudService.eliminarSolicitud(id);
    }

    @GetMapping("/{id}")
    public Optional<Solicitud> obtenerSolicitudPorId(@PathVariable int id) {
        return solicitudService.obtenerSolicitudPorId(id);
    }
}
