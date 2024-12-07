package ManyWorker.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ManyWorker.entity.PlanTrabajo;
import ManyWorker.service.PlanTrabajoService;

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
    
    @DeleteMapping("/fases/{id}")
    public ResponseEntity<?> eliminarFase(@PathVariable int id) {
        planTrabajoService.eliminarFaseDePlanTrabajo(id);
        return ResponseEntity.ok("Fase eliminada con éxito.");
    }

}
