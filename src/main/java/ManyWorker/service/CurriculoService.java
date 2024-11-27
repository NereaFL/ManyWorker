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

    // Registro de currículos
    public Curriculo registrarCurriculo(Curriculo curriculo) {
        return curriculoRepository.save(curriculo);
    }

    // Listado de currículos
    public List<Curriculo> listarCurriculos() {
        return curriculoRepository.findAll();
    }

    // Visualización de un currículo por ID
    public Optional<Curriculo> obtenerCurriculoPorId(Integer id) {
        return curriculoRepository.findById(id);
    }

    // Actualización de currículos
    public Curriculo actualizarCurriculo(Integer id, Curriculo curriculoActualizado) {
        return curriculoRepository.findById(id).map(curriculo -> {
            curriculo.setCodigo(curriculoActualizado.getCodigo());
            curriculo.setArchivoPdf(curriculoActualizado.getArchivoPdf());
            return curriculoRepository.save(curriculo);
        }).orElseThrow(() -> new RuntimeException("Currículo no encontrado con ID: " + id));
    }

    // Eliminación de currículos
    public void eliminarCurriculo(Integer id) {
        if (curriculoRepository.existsById(id)) {
            curriculoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Currículo no encontrado con ID: " + id);
        }
    }
}
