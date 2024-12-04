package ManyWorker.controller;

import ManyWorker.entity.PlanTrabajo;
import ManyWorker.service.PlanTrabajoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/planTrabajo")
public class PlanTrabajoController {

    @Autowired
    private PlanTrabajoService planTrabajoService;

    @PostMapping
    public PlanTrabajo crearPlanTrabajo(@RequestBody PlanTrabajo planTrabajo) {
        return planTrabajoService.crearPlanTrabajo(planTrabajo);
    }

    @PutMapping("/{id}")
    public PlanTrabajo editarPlanTrabajo(@PathVariable int id, @RequestBody PlanTrabajo nuevosDatos) {
        return planTrabajoService.editarPlanTrabajo(id, nuevosDatos);
    }

    @GetMapping
    public List<PlanTrabajo> listarPlanTrabajo() {
        return planTrabajoService.listarPlanTrabajo();
    }

    @DeleteMapping("/{id}")
    public void eliminarPlanTrabajo(@PathVariable int id) {
    	planTrabajoService.eliminarPlanTrabajo(id);
    }

    @GetMapping("/{id}")
    public Optional<PlanTrabajo> obtenerPlanTrabajoPorId(@PathVariable int id) {
        return planTrabajoService.obtenerPlanTrabajoPorId(id);
    }
}
