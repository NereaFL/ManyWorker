package ManyWorker.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notificaciones-seguridad")
public class SecurityBreachNotificationController {

    @Autowired
    private SecurityBreachNotificationService securityBreachNotificationService;

    // Endpoint para notificar a los usuarios de una brecha de seguridad
    @PostMapping("/notificar")
    public void notificarUsuariosDeBrecha() {
        securityBreachNotificationService.notificarUsuariosDeBrecha();
    }
}
