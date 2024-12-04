package ManyWorker.controller;

import ManyWorker.entity.Fase;
import ManyWorker.service.FaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/fase")
public class FaseController {

    @Autowired
    private FaseService FaseService;

    @PostMapping
    public Fase crearFase(@RequestBody Fase Fase) {
        return FaseService.crearFase(Fase);
    }

    @PutMapping("/{id}")
    public Fase editarFase(@PathVariable int id, @RequestBody Fase nuevosDatos) {
        return FaseService.editarFase(id, nuevosDatos);
    }

    @GetMapping
    public List<Fase> listarFases() {
        return FaseService.listarFases();
    }

    @DeleteMapping("/{id}")
    public void eliminarFase(@PathVariable int id) {
        FaseService.eliminarFase(id);
    }

    @GetMapping("/{id}")
    public Optional<Fase> obtenerFasePorId(@PathVariable int id) {
        return FaseService.obtenerFasePorId(id);
    }
}