package ManyWorker.service;

import ManyWorker.entity.CatalogoGarantia;
import ManyWorker.repository.CatalogoGarantiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CatalogoGarantiaService {

    @Autowired
    private CatalogoGarantiaRepository catalogoGarantiaRepository;

    // Crear un nuevo Catalogo Garantia
    public CatalogoGarantia crearCatalogoGarantia(CatalogoGarantia catalogoGarantia) {
        return catalogoGarantiaRepository.save(catalogoGarantia);
    }


    // Editar un Catalogo Garantia
    public CatalogoGarantia editarCatalogoGarantia(int catalogoGarantiaId, CatalogoGarantia nuevosDatos) {
        Optional<CatalogoGarantia> catalogoGarantiaOptional = catalogoGarantiaRepository.findById(catalogoGarantiaId);

        if (catalogoGarantiaOptional.isPresent()) {
        	CatalogoGarantia catalogoGarantiaExistente = catalogoGarantiaOptional.get();
        	catalogoGarantiaExistente.setTitulo(nuevosDatos.getTitulo());
        	catalogoGarantiaExistente.setTerminosLeyes(nuevosDatos.getTerminosLeyes());
            return catalogoGarantiaRepository.save(catalogoGarantiaExistente);
        } else {
            throw new RuntimeException("Catalogo Garantia no encontrado");
        }
    }

    // Listar todos los catalogo garantia
    public List<CatalogoGarantia> listarCatalogoGarantia() {
        return catalogoGarantiaRepository.findAll();
    }

    // Eliminar un catalogo garantia
    public void eliminarCatalogoGarantia(int catalogoGarantiaId) {
    	catalogoGarantiaRepository.deleteById(catalogoGarantiaId);
    }

    // Obtener un catalogo garantia por ID
    public Optional<CatalogoGarantia> obtenerCatalogoGarantiaPorId(int catalogoGarantiaId) {
        return catalogoGarantiaRepository.findById(catalogoGarantiaId);
    }
}
