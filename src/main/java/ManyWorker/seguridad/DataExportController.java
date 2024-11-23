package ManyWorker.seguridad;

import ManyWorker.entity.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/exportar-datos")
public class DataExportController {

    @Autowired
    private DataExportService dataExportService;

    // Endpoint para exportar los datos del usuario
    @GetMapping("/{actorId}")
    public Optional<Actor> exportarDatosUsuario(@PathVariable int actorId) {
        return dataExportService.exportarDatosUsuario(actorId);
    }
}
