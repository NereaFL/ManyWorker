package ManyWorker.controller;

import ManyWorker.entity.Trabajador;
import ManyWorker.service.TrabajadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/trabajadores")
public class TrabajadorController {

    @Autowired
    private TrabajadorService trabajadorService;

    @PostMapping("/registro")
    public Trabajador registrarTrabajador(@RequestBody Trabajador trabajador) {
        return trabajadorService.registrarTrabajador(trabajador);
    }

    @PutMapping("/editar/{id}")
    public Trabajador editarTrabajador(@PathVariable int id, @RequestBody Trabajador nuevosDatos) {
        return trabajadorService.editarTrabajador(id, nuevosDatos);
    }

    @GetMapping
    public List<Trabajador> listarTrabajadores() {
        return trabajadorService.listarTrabajadores();
    }

    @GetMapping("/{id}")
    public Optional<Trabajador> obtenerTrabajadorPorId(@PathVariable int id) {
        return trabajadorService.obtenerTrabajadorPorId(id);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarTrabajador(@PathVariable int id) {
        trabajadorService.eliminarTrabajador(id);
    }
}
