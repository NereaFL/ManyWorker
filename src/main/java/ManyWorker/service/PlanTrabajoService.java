package ManyWorker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ManyWorker.entity.Fase;
import ManyWorker.entity.PlanTrabajo;
import ManyWorker.entity.Trabajador;
import ManyWorker.repository.FaseRepository;
import ManyWorker.repository.PlanTrabajoRepository;
import ManyWorker.security.JWTUtils;

@Service
public class PlanTrabajoService {

    @Autowired
    private PlanTrabajoRepository planTrabajoRepository;

    @Autowired
    private JWTUtils jwtUtils;
    
    @Autowired
    private FaseRepository faseRepository;

    // Crear un nuevo Plan Trabajo
    public PlanTrabajo crearPlanTrabajo(PlanTrabajo planTrabajo) {
        Trabajador trabajador = jwtUtils.userLogin();
        if (trabajador == null) {
            throw new IllegalStateException("Solo los trabajadores pueden gestionar planes de trabajo.");
        }

        planTrabajo.setTrabajador(trabajador); // Asociar el plan de trabajo al trabajador autenticado
        return planTrabajoRepository.save(planTrabajo);
    }

    // Editar un Plan Trabajo
    public PlanTrabajo editarPlanTrabajo(int planTrabajoId, PlanTrabajo nuevosDatos) {
        Trabajador trabajador = jwtUtils.userLogin();
        if (trabajador == null) {
            throw new IllegalStateException("Solo los trabajadores pueden gestionar planes de trabajo.");
        }

        Optional<PlanTrabajo> planTrabajoOptional = planTrabajoRepository.findById(planTrabajoId);
        if (planTrabajoOptional.isPresent()) {
            PlanTrabajo planTrabajoExistente = planTrabajoOptional.get();
            if (!planTrabajoExistente.getTrabajador().equals(trabajador)) {
                throw new IllegalArgumentException("No puedes editar un plan de trabajo que no te pertenece.");
            }

            planTrabajoExistente.setNumeroFases(nuevosDatos.getNumeroFases());
            planTrabajoExistente.setFases(nuevosDatos.getFases());
            return planTrabajoRepository.save(planTrabajoExistente);
        } else {
            throw new RuntimeException("Plan de trabajo no encontrado.");
        }
    }

    // Listar todos los Plan Trabajo del Trabajador
    public List<PlanTrabajo> listarPlanTrabajo() {
        Trabajador trabajador = jwtUtils.userLogin();
        if (trabajador == null) {
            throw new IllegalStateException("Solo los trabajadores pueden gestionar planes de trabajo.");
        }

        return planTrabajoRepository.findByTrabajador(trabajador); // Método personalizado
    }

    // Eliminar un Plan Trabajo
    public void eliminarPlanTrabajo(int planTrabajoId) {
        Trabajador trabajador = jwtUtils.userLogin();
        if (trabajador == null) {
            throw new IllegalStateException("Solo los trabajadores pueden gestionar planes de trabajo.");
        }

        Optional<PlanTrabajo> planTrabajoOptional = planTrabajoRepository.findById(planTrabajoId);
        if (planTrabajoOptional.isPresent()) {
            PlanTrabajo planTrabajo = planTrabajoOptional.get();
            if (!planTrabajo.getTrabajador().equals(trabajador)) {
                throw new IllegalArgumentException("No puedes eliminar un plan de trabajo que no te pertenece.");
            }

            planTrabajoRepository.delete(planTrabajo);
        } else {
            throw new RuntimeException("Plan de trabajo no encontrado.");
        }
    }
    
 // Eliminar una Fase de un Plan de Trabajo
    public void eliminarFaseDePlanTrabajo(int faseId) {
        Trabajador trabajador = jwtUtils.userLogin();
        if (trabajador == null) {
            throw new IllegalStateException("Solo los trabajadores pueden gestionar fases.");
        }

        Optional<Fase> faseOptional = faseRepository.findById(faseId);
        if (faseOptional.isPresent()) {
            Fase fase = faseOptional.get();
            PlanTrabajo planTrabajo = fase.getPlanTrabajo(); // Suponiendo que Fase tiene una relación con PlanTrabajo

            if (planTrabajo == null) {
                throw new IllegalStateException("La fase no está asociada a ningún plan de trabajo.");
            }

            if (!planTrabajo.getTrabajador().equals(trabajador)) {
                throw new IllegalArgumentException("No puedes eliminar una fase de un plan de trabajo que no te pertenece.");
            }

            faseRepository.delete(fase);
        } else {
            throw new RuntimeException("Fase no encontrada.");
        }
    }


    // Obtener un Plan Trabajo por ID
    public Optional<PlanTrabajo> obtenerPlanTrabajoPorId(int planTrabajoId) {
        Trabajador trabajador = jwtUtils.userLogin();
        if (trabajador == null) {
            throw new IllegalStateException("Solo los trabajadores pueden gestionar planes de trabajo.");
        }

        Optional<PlanTrabajo> planTrabajoOptional = planTrabajoRepository.findById(planTrabajoId);
        if (planTrabajoOptional.isPresent() && planTrabajoOptional.get().getTrabajador().equals(trabajador)) {
            return planTrabajoOptional;
        } else {
            return Optional.empty();
        }
    }
}
