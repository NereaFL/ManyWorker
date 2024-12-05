package ManyWorker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ManyWorker.entity.PlanTrabajo;
import ManyWorker.entity.Trabajador;
import ManyWorker.repository.PlanTrabajoRepository;
import ManyWorker.security.JWTUtils;

@Service
public class PlanTrabajoService {

    @Autowired
    private PlanTrabajoRepository planTrabajoRepository;

    @Autowired
    private JWTUtils jwtUtils;

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
