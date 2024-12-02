package ManyWorker.controller;

import ManyWorker.entity.PerfilSocial;
import ManyWorker.service.PerfilSocialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/perfil")
public class PerfilSocialController {

    @Autowired
    private PerfilSocialService perfilSocialService;

    @PostMapping("/crear")
    public PerfilSocial crearPerfilSocial(@RequestBody PerfilSocial perfilSocial) {
        return perfilSocialService.crearPerfilSocial(perfilSocial);
    }

    @PutMapping("/editar/{id}")
    public PerfilSocial editarPerfilSocial(@PathVariable int id, @RequestBody PerfilSocial nuevosDatos) {
        return perfilSocialService.editarPerfilSocial(id, nuevosDatos);
    }

    @GetMapping
    public List<PerfilSocial> listarPerfilesSociales() {
        return perfilSocialService.listarPerfilesSociales();
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarPerfilSocial(@PathVariable int id) {
        perfilSocialService.eliminarPerfilSocial(id);
    }

    @GetMapping("/{id}")
    public Optional<PerfilSocial> obtenerPerfilSocialPorId(@PathVariable int id) {
        return perfilSocialService.obtenerPerfilSocialPorId(id);
    }
}
