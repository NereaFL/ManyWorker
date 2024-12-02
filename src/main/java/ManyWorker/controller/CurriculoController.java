package ManyWorker.controller;

import ManyWorker.entity.Curriculo;
import ManyWorker.service.CurriculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/curriculo")
public class CurriculoController {

    @Autowired
    private CurriculoService curriculoService;

    @PostMapping
    public Curriculo crearCurriculo(@RequestBody Curriculo curriculo) {
        return curriculoService.crearCurriculo(curriculo);
    }

    @PutMapping("/{id}")
    public Curriculo editarCurriculo(@PathVariable int id, @RequestBody Curriculo nuevosDatos) {
        return curriculoService.editarCurriculo(id, nuevosDatos);
    }

    @GetMapping
    public List<Curriculo> listarCurriculos() {
        return curriculoService.listarCurriculos();
    }

    @DeleteMapping("/{id}")
    public void eliminarCurriculo(@PathVariable int id) {
        curriculoService.eliminarCurriculo(id);
    }

    @GetMapping("/{id}")
    public Optional<Curriculo> obtenerCurriculoPorId(@PathVariable int id) {
        return curriculoService.obtenerCurriculoPorId(id);
    }
}
