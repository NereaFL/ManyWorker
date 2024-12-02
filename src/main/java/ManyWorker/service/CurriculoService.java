package ManyWorker.service;

import ManyWorker.entity.Curriculo;
import ManyWorker.repository.CurriculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CurriculoService {

    @Autowired
    private CurriculoRepository curriculoRepository;

    // Crear un nuevo curriculo
    public Curriculo crearCurriculo(Curriculo curriculo) {
        if (curriculo.getCodigo() == null || curriculo.getCodigo().isEmpty()) {
            throw new IllegalArgumentException("El curriculo debe tener un código válido.");
        }
        return curriculoRepository.save(curriculo);
    }

    // Editar un curriculo
    public Curriculo editarCurriculo(int curriculoId, Curriculo nuevosDatos) {
        Optional<Curriculo> curriculoOptional = curriculoRepository.findById(curriculoId);

        if (curriculoOptional.isPresent()) {
            Curriculo curriculoExistente = curriculoOptional.get();
            curriculoExistente.setCodigo(nuevosDatos.getCodigo());
            curriculoExistente.setArchivoPdf(nuevosDatos.getArchivoPdf());
            return curriculoRepository.save(curriculoExistente);
        } else {
            throw new RuntimeException("Curriculo no encontrado");
        }
    }

    // Listar todos los curriculos
    public List<Curriculo> listarCurriculos() {
        return curriculoRepository.findAll();
    }

    // Eliminar un curriculo
    public void eliminarCurriculo(int curriculoId) {
        curriculoRepository.deleteById(curriculoId);
    }

    // Obtener un curriculo por ID
    public Optional<Curriculo> obtenerCurriculoPorId(int curriculoId) {
        return curriculoRepository.findById(curriculoId);
    }
}
