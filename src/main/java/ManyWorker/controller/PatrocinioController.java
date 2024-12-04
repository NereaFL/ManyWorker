package ManyWorker.controller;

import ManyWorker.entity.Patrocinio;
import ManyWorker.service.PatrocinioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patrocinio")
public class PatrocinioController {

    @Autowired
    private PatrocinioService PatrocinioService;

    @PostMapping
    public Patrocinio crearPatrocinio(@RequestBody Patrocinio Patrocinio) {
        return PatrocinioService.crearPatrocinio(Patrocinio);
    }

    @PutMapping("/{id}")
    public Patrocinio editarPatrocinio(@PathVariable int id, @RequestBody Patrocinio nuevosDatos) {
        return PatrocinioService.editarPatrocinio(id, nuevosDatos);
    }

    @GetMapping
    public List<Patrocinio> listarPatrocinios() {
        return PatrocinioService.listarPatrocinios();
    }

    @DeleteMapping("/{id}")
    public void eliminarPatrocinio(@PathVariable int id) {
        PatrocinioService.eliminarPatrocinio(id);
    }

    @GetMapping("/{id}")
    public Optional<Patrocinio> obtenerPatrocinioPorId(@PathVariable int id) {
        return PatrocinioService.obtenerPatrocinioPorId(id);
    }
}