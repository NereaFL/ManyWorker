package ManyWorker.service;

import ManyWorker.entity.Categoria;
import ManyWorker.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    // Crear una nueva Categoria
    public Categoria crearCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    // Editar una Categoria
    public Categoria editarCategoria(int categoriaId, Categoria nuevosDatos) {
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(categoriaId);

        if (categoriaOptional.isPresent()) {
        	Categoria categoriaExistente = categoriaOptional.get();
        	categoriaExistente.setNombreCategoria(nuevosDatos.getNombreCategoria());
            return categoriaRepository.save(categoriaExistente);
        } else {
            throw new RuntimeException("Categoria no encontrada");
        }
    }

    // Listar todas las Categorias
    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

    // Eliminar una categoria
    public void eliminarCategoria(int categoriaId) {
    	categoriaRepository.deleteById(categoriaId);
    }

    // Obtener una Categoria por ID
    public Optional<Categoria> obtenerCategoriaPorId(int categoriaId) {
        return categoriaRepository.findById(categoriaId);
    }
}
