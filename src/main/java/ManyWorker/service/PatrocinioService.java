package ManyWorker.service;

import ManyWorker.entity.Patrocinio;
import ManyWorker.entity.Patrocinador;
import ManyWorker.repository.PatrocinioRepository;
import ManyWorker.security.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatrocinioService {

    @Autowired
    private PatrocinioRepository patrocinioRepository;

    @Autowired
    private JWTUtils jwtUtils; 

    public Patrocinio crearPatrocinio(Patrocinio patrocinio) {

        Patrocinador patrocinador = (Patrocinador) jwtUtils.userLogin();
        if (patrocinador == null) {
            throw new IllegalStateException("Solo los patrocinadores pueden crear un patrocinio.");
        }
        
        if (patrocinio.getPatrocinador() == null) {
            throw new IllegalArgumentException("El patrocinio debe tener un patrocinador asignado.");
        }

        return patrocinioRepository.save(patrocinio);
    }

    public Patrocinio editarPatrocinio(int patrocinioId, Patrocinio nuevosDatos) {
        Patrocinador patrocinador = (Patrocinador) jwtUtils.userLogin();
        if (patrocinador == null) {
            throw new IllegalStateException("Solo los patrocinadores pueden editar un patrocinio.");
        }

        Optional<Patrocinio> patrocinioOptional = patrocinioRepository.findById(patrocinioId);

        if (patrocinioOptional.isPresent()) {
            Patrocinio patrocinioExistente = patrocinioOptional.get();
            
            if (!patrocinioExistente.getPatrocinador().equals(patrocinador)) {
                throw new IllegalArgumentException("No puedes editar un patrocinio que no te pertenece.");
            }

            patrocinioExistente.setBanner(nuevosDatos.getBanner());
            patrocinioExistente.setEnlacePaginaObjetivo(nuevosDatos.getEnlacePaginaObjetivo());
            patrocinioExistente.setPatrocinador(nuevosDatos.getPatrocinador());
            return patrocinioRepository.save(patrocinioExistente);
        } else {
            throw new RuntimeException("Patrocinio no encontrado");
        }
    }

    public List<Patrocinio> listarPatrocinios() {
        return patrocinioRepository.findAll();
    }

    public void eliminarPatrocinio(int patrocinioId) {
        Patrocinador patrocinador = (Patrocinador) jwtUtils.userLogin();
        if (patrocinador == null) {
            throw new IllegalStateException("Solo los patrocinadores pueden eliminar un patrocinio.");
        }

        Optional<Patrocinio> patrocinioOptional = patrocinioRepository.findById(patrocinioId);

        if (patrocinioOptional.isPresent()) {
            Patrocinio patrocinio = patrocinioOptional.get();
            if (!patrocinio.getPatrocinador().equals(patrocinador)) {
                throw new IllegalArgumentException("No puedes eliminar un patrocinio que no te pertenece.");
            }
            patrocinioRepository.deleteById(patrocinioId);
        } else {
            throw new RuntimeException("Patrocinio no encontrado");
        }
    }

    public Optional<Patrocinio> obtenerPatrocinioPorId(int patrocinioId) {
        return patrocinioRepository.findById(patrocinioId);
    }
}
