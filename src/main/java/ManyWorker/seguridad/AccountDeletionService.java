package ManyWorker.seguridad;

import ManyWorker.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountDeletionService {

    @Autowired
    private ActorRepository actorRepository;

    // Eliminar cuenta del usuario
    public void eliminarCuenta(int actorId) {
        // Elimina la cuenta del actor por su ID
        actorRepository.deleteById(actorId);
    }
}
