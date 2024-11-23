package ManyWorker.seguridad;

import ManyWorker.entity.Actor;
import ManyWorker.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecurityBreachNotificationService {

    @Autowired
    private ActorRepository actorRepository;

    // Notificar a los usuarios de una brecha de seguridad
    public void notificarUsuariosDeBrecha() {
        // Obtiene todos los actores en el sistema
        List<Actor> actores = actorRepository.findAll();
        for (Actor actor : actores) {
            // Envia una notificación de brecha de seguridad a cada actor
            enviarNotificacion(actor);
        }
    }

    private void enviarNotificacion(Actor actor) {
        // Implementar el envío de notificación (correo electrónico, SMS, etc.)
    }
}
