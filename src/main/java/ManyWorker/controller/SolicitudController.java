package ManyWorker.controller;

import ManyWorker.entity.Solicitud;
import ManyWorker.service.SolicitudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/solicitudes")
public class SolicitudController {

    @Autowired
    private SolicitudService solicitudService;

    @PostMapping("/registro")
    public Solicitud registrarSolicitud(@RequestBody Solicitud solicitud) {
        return solicitudService.registrarSolicitud(solicitud);
    }

    @PutMapping("/editar/{id}")
    public Solicitud editarSolicitud(@PathVariable int id, @RequestBody Solicitud nuevosDatos) {
        return solicitudService.editarSolicitud(id, nuevosDatos);
    }

    @GetMapping
    public List<Solicitud> listarSolicitudes() {
        return solicitudService.listarSolicitudes();
    }

    @GetMapping("/{id}")
    public Optional<Solicitud> obtenerSolicitudPorId(@PathVariable int id) {
        return solicitudService.obtenerSolicitudPorId(id);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarSolicitud(@PathVariable int id) {
        solicitudService.eliminarSolicitud(id);
    }
}
