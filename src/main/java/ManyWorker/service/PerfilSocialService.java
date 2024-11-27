package ManyWorker.service;

import ManyWorker.entity.PerfilSocial;
import ManyWorker.repository.PerfilSocialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PerfilSocialService {

    @Autowired
    private PerfilSocialRepository perfilSocialRepository;

    // Crear un nuevo perfil social
    public PerfilSocial crearPerfilSocial(PerfilSocial perfilSocial) {
        // Asegúrate de que el actor esté asignado
        if (perfilSocial.getActor() == null) {
            throw new IllegalArgumentException("El perfil debe tener un actor asignado.");
        }
        return perfilSocialRepository.save(perfilSocial);
    }


    // Editar un perfil social
    public PerfilSocial editarPerfilSocial(int perfilId, PerfilSocial nuevosDatos) {
        Optional<PerfilSocial> perfilOptional = perfilSocialRepository.findById(perfilId);

        if (perfilOptional.isPresent()) {
            PerfilSocial perfilExistente = perfilOptional.get();
            perfilExistente.setApodo(nuevosDatos.getApodo());
            perfilExistente.setNombreRedSocial(nuevosDatos.getNombreRedSocial());
            perfilExistente.setEnlace(nuevosDatos.getEnlace());
            return perfilSocialRepository.save(perfilExistente);
        } else {
            throw new RuntimeException("Perfil social no encontrado");
        }
    }

    // Listar todos los perfiles sociales
    public List<PerfilSocial> listarPerfilesSociales() {
        return perfilSocialRepository.findAll();
    }

    // Eliminar un perfil social
    public void eliminarPerfilSocial(int perfilId) {
        perfilSocialRepository.deleteById(perfilId);
    }

    // Obtener un perfil social por ID
    public Optional<PerfilSocial> obtenerPerfilSocialPorId(int perfilId) {
        return perfilSocialRepository.findById(perfilId);
    }
}
