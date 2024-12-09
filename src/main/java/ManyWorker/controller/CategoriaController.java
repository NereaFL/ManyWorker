package ManyWorker.controller;

import ManyWorker.entity.Categoria;
import ManyWorker.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping
    public Categoria crearCategoria(@RequestBody Categoria categoria) {
        return categoriaService.crearCategoria(categoria);
    }

    @PutMapping("/{id}")
    public Categoria editarCategoria(@PathVariable int id, @RequestBody Categoria nuevosDatos) {
        return categoriaService.editarCategoria(id, nuevosDatos);
    }

    @GetMapping
    public List<Categoria> listarCategoria() {
        return categoriaService.listarCategorias();
    }

    @DeleteMapping("/{id}")
    public void eliminarCategoria(@PathVariable int id) {
    	categoriaService.eliminarCategoria(id);
    }

    @GetMapping("/{id}")
    public Optional<Categoria> obtenerCategoriaPorId(@PathVariable int id) {
        return categoriaService.obtenerCategoriaPorId(id);
    }
    
    
}

