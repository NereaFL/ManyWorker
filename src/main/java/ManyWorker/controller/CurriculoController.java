package ManyWorker.controller;

import ManyWorker.entity.Curriculo;
import ManyWorker.service.CurriculoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/curriculos") 
public class CurriculoController {

    @Autowired
    private CurriculoService curriculoService;

    // Registrar un currículo
    @PostMapping
    public ResponseEntity<Curriculo> registrarCurriculo(@Validated @RequestBody Curriculo curriculo) {
        Curriculo nuevoCurriculo = curriculoService.registrarCurriculo(curriculo);
        return new ResponseEntity<>(nuevoCurriculo, HttpStatus.CREATED);
    }

    // Listar todos los currículos
    @GetMapping
    public ResponseEntity<List<Curriculo>> listarCurriculos() {
        List<Curriculo> curriculos = curriculoService.listarCurriculos();
        return new ResponseEntity<>(curriculos, HttpStatus.OK);
    }

    // Visualizar un currículo por ID
    @GetMapping("/{id}")
    public ResponseEntity<Curriculo> obtenerCurriculoPorId(@PathVariable Integer id) {
        Optional<Curriculo> curriculo = curriculoService.obtenerCurriculoPorId(id);
        return curriculo.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Actualizar un currículo
    @PutMapping("/{id}")
    public ResponseEntity<Curriculo> actualizarCurriculo(@PathVariable Integer id, @Validated @RequestBody Curriculo curriculoActualizado) {
        try {
            Curriculo curriculo = curriculoService.actualizarCurriculo(id, curriculoActualizado);
            return new ResponseEntity<>(curriculo, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar un currículo
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCurriculo(@PathVariable Integer id) {
        try {
            curriculoService.eliminarCurriculo(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
