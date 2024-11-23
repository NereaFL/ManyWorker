package ManyWorker.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eliminar-cuenta")
public class AccountDeletionController {

    @Autowired
    private AccountDeletionService accountDeletionService;

    // Endpoint para eliminar la cuenta del usuario
    @DeleteMapping("/{actorId}")
    public void eliminarCuenta(@PathVariable int actorId) {
        accountDeletionService.eliminarCuenta(actorId);
    }
}
