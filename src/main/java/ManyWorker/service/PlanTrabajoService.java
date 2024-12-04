package ManyWorker.service;

import ManyWorker.entity.PlanTrabajo;
import ManyWorker.repository.PlanTrabajoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanTrabajoService {

    @Autowired
    private PlanTrabajoRepository planTrabajoRepository;

    // Crear un nuevo Plan Trabajo
    public PlanTrabajo crearPlanTrabajo(PlanTrabajo planTrabajo) {
        return planTrabajoRepository.save(planTrabajo);
    }

    // Editar un Plan Trabajo
    public PlanTrabajo editarPlanTrabajo(int planTrabajoId, PlanTrabajo nuevosDatos) {
        Optional<PlanTrabajo> planTrabajoOptional = planTrabajoRepository.findById(planTrabajoId);

        if (planTrabajoOptional.isPresent()) {
        	PlanTrabajo planTrabajoExistente = planTrabajoOptional.get();
        	planTrabajoExistente.setNumeroFases(nuevosDatos.getNumeroFases());
        	planTrabajoExistente.setFases(nuevosDatos.getFases());
            return planTrabajoRepository.save(planTrabajoExistente);
        } else {
            throw new RuntimeException("Plan Trabajo no encontrado");
        }
    }

    // Listar todos los Plan Trabajo
    public List<PlanTrabajo> listarPlanTrabajo() {
        return planTrabajoRepository.findAll();
    }

    // Eliminar un Plan Trabajo
    public void eliminarPlanTrabajo(int planTrabajoId) {
    	planTrabajoRepository.deleteById(planTrabajoId);
    }

    // Obtener un Plan Trabajo por ID
    public Optional<PlanTrabajo> obtenerPlanTrabajoPorId(int planTrabajoId) {
        return planTrabajoRepository.findById(planTrabajoId);
    }
}
