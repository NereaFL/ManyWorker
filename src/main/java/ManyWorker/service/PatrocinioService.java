package ManyWorker.service;

import ManyWorker.entity.Patrocinio;
import ManyWorker.repository.PatrocinioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatrocinioService {

    @Autowired
    private PatrocinioRepository patrocinioRepository;

    // Crear un nuevo perfil social
    public Patrocinio crearPatrocinio(Patrocinio patrocinio) {
        // Asegúrate de que el actor esté asignado
        if (patrocinio.getPatrocinador() == null) {
            throw new IllegalArgumentException("El patrocinio debe tener un patrocinador asignado.");
        }
        return patrocinioRepository.save(patrocinio);
    }


    // Editar un perfil social
    public Patrocinio editarPatrocinio(int patrocinioId, Patrocinio nuevosDatos) {
        Optional<Patrocinio> patrocinioOptional = patrocinioRepository.findById(patrocinioId);

        if (patrocinioOptional.isPresent()) {
            Patrocinio patrocinioExistente = patrocinioOptional.get();
            patrocinioExistente.setBanner(nuevosDatos.getBanner());
            patrocinioExistente.setEnlacePaginaObjetivo(nuevosDatos.getEnlacePaginaObjetivo());
            patrocinioExistente.setPatrocinador(nuevosDatos.getPatrocinador());
            return patrocinioRepository.save(patrocinioExistente);
        } else {
            throw new RuntimeException("Patrocinio no encontrado");
        }
    }

    // Listar todos los perfiles sociales
    public List<Patrocinio> listarPatrocinios() {
        return patrocinioRepository.findAll();
    }

    // Eliminar un perfil social
    public void eliminarPatrocinio(int patrocinioId) {
        patrocinioRepository.deleteById(patrocinioId);
    }

    // Obtener un perfil social por ID
    public Optional<Patrocinio> obtenerPatrocinioPorId(int patrocinioId) {
        return patrocinioRepository.findById(patrocinioId);
    }
}