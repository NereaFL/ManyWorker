package ManyWorker.service;

import ManyWorker.entity.Curriculo;
import ManyWorker.entity.Trabajador;
import ManyWorker.repository.CurriculoRepository;
import ManyWorker.security.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CurriculoService {

    @Autowired
    private CurriculoRepository curriculoRepository;

    @Autowired
    private TrabajadorService trabajadorService;

    private JWTUtils jwtUtils;

    // Crear un nuevo curriculo
    @Transactional
    public Curriculo crearCurriculo(Curriculo curriculo) {
        Trabajador trabajador = jwtUtils.userLogin();
        if (trabajador == null) {
            throw new IllegalStateException("Solo los trabajadores pueden gestionar currículos.");
        }

        curriculo.setTrabajador(trabajador); // Asociar el currículum al trabajador
        return curriculoRepository.save(curriculo);
    }

    // Editar un curriculo
    @Transactional
    public Curriculo editarCurriculo(int curriculoId, Curriculo nuevosDatos) {
        Trabajador trabajador = jwtUtils.userLogin();
        if (trabajador == null) {
            throw new IllegalStateException("Solo los trabajadores pueden gestionar currículos.");
        }

        Optional<Curriculo> curriculoOptional = curriculoRepository.findById(curriculoId);
        if (curriculoOptional.isPresent()) {
            Curriculo curriculoExistente = curriculoOptional.get();
            if (!curriculoExistente.getTrabajador().equals(trabajador)) {
                throw new IllegalArgumentException("No puedes editar un currículum que no te pertenece.");
            }

            curriculoExistente.setCodigo(nuevosDatos.getCodigo());
            curriculoExistente.setArchivoPdf(nuevosDatos.getArchivoPdf());
            return curriculoRepository.save(curriculoExistente);
        } else {
            throw new RuntimeException("Currículo no encontrado.");
        }
    }

    // Listar todos los currículos de un trabajador
    public List<Curriculo> listarCurriculos() {
        Trabajador trabajador = jwtUtils.userLogin();
        if (trabajador == null) {
            throw new IllegalStateException("Solo los trabajadores pueden gestionar currículos.");
        }

        return curriculoRepository.findByTrabajador(trabajador); 
    }

    // Eliminar un currículum
    @Transactional
    public void eliminarCurriculo(int curriculoId) {
        Trabajador trabajador = jwtUtils.userLogin();
        if (trabajador == null) {
            throw new IllegalStateException("Solo los trabajadores pueden gestionar currículos.");
        }

        Optional<Curriculo> curriculoOptional = curriculoRepository.findById(curriculoId);
        if (curriculoOptional.isPresent()) {
            Curriculo curriculo = curriculoOptional.get();
            if (!curriculo.getTrabajador().equals(trabajador)) {
                throw new IllegalArgumentException("No puedes eliminar un currículum que no te pertenece.");
            }

            curriculoRepository.delete(curriculo);
        } else {
            throw new RuntimeException("Currículo no encontrado.");
        }
    }

    // Obtener un currículum por ID
    public Optional<Curriculo> obtenerCurriculoPorId(int curriculoId) {
        Trabajador trabajador = jwtUtils.userLogin();
        if (trabajador == null) {
            throw new IllegalStateException("Solo los trabajadores pueden gestionar currículos.");
        }

        Optional<Curriculo> curriculoOptional = curriculoRepository.findById(curriculoId);
        if (curriculoOptional.isPresent() && curriculoOptional.get().getTrabajador().equals(trabajador)) {
            return curriculoOptional;
        } else {
            return Optional.empty();
        }
    }
}
