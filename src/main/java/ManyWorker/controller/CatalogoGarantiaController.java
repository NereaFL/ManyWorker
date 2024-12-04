package ManyWorker.controller;

import ManyWorker.entity.CatalogoGarantia;
import ManyWorker.service.CatalogoGarantiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/catalogoGarantia")
public class CatalogoGarantiaController {

	    @Autowired
	    private CatalogoGarantiaService catalogoGarantiaService;

	    //Crear un Catalogo Garantia
	    @PostMapping
	    public CatalogoGarantia crearCatalogoGarantia(@RequestBody CatalogoGarantia catalogoGarantia) {
	        return catalogoGarantiaService.crearCatalogoGarantia(catalogoGarantia);
	    }

	    //Editar un Catalogo Garantia
	    @PutMapping("/{id}")
	    public CatalogoGarantia editarCatalogoGarantia(@PathVariable int id, @RequestBody CatalogoGarantia nuevosDatos) {
	        return catalogoGarantiaService.editarCatalogoGarantia(id, nuevosDatos);
	    }

	    //Listar un Catalogo Garantia
	    @GetMapping
	    public List<CatalogoGarantia> listarCatalogoGarantia() {
	        return catalogoGarantiaService.listarCatalogoGarantia();
	    }

	    //Eliminar un Catalogo Garantia
	    @DeleteMapping("/{id}")
	    public void eliminarCatalogoGarantia(@PathVariable int id) {
	    	catalogoGarantiaService.eliminarCatalogoGarantia(id);
	    }

	    //Obtener un Catalogo Garantia
	    @GetMapping("/{id}")
	    public Optional<CatalogoGarantia> obtenerCatalogoGarantia(@PathVariable int id) {
	        return catalogoGarantiaService.obtenerCatalogoGarantiaPorId(id);
	    }
}
