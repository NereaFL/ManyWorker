package ManyWorker.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ManyWorker.entity.Fase;
import ManyWorker.entity.PlanTrabajo;
import ManyWorker.entity.Trabajador;
import ManyWorker.repository.FaseRepository;
import ManyWorker.repository.PlanTrabajoRepository;
import ManyWorker.security.JWTUtils;

@Service
public class FaseService {

    @Autowired
    private FaseRepository faseRepository;

    @Autowired
    private PlanTrabajoRepository planTrabajoRepository;

    @Autowired
    private JWTUtils jwtUtils;

    // Crear una nueva Fase
    public Fase crearFase(Fase fase, int planTrabajoId) {
        Trabajador trabajador = jwtUtils.userLogin();
        if (trabajador == null) {
            throw new IllegalStateException("Solo los trabajadores pueden gestionar fases.");
        }

        Optional<PlanTrabajo> planTrabajoOptional = planTrabajoRepository.findById(planTrabajoId);
        if (planTrabajoOptional.isPresent()) {
            PlanTrabajo planTrabajo = planTrabajoOptional.get();
            if (!planTrabajo.getTrabajador().equals(trabajador)) {
                throw new IllegalArgumentException("No puedes añadir fases a un plan de trabajo que no te pertenece.");
            }

            // Validar fechas con la fecha de finalización de la tarea asociada
            if (planTrabajo.getTarea() == null) {
                throw new IllegalArgumentException("El plan de trabajo debe estar asociado a una tarea.");
            }

            if (fase.getFechaFin().after(planTrabajo.getTarea().getFechaFinalizacion())) {
                throw new IllegalArgumentException("La fecha de finalización de la fase no puede ser posterior a la fecha de finalización de la tarea asociada.");
            }

            // Asociar la fase al plan de trabajo
            fase.setPlanTrabajo(planTrabajo);
            return faseRepository.save(fase);
        } else {
            throw new RuntimeException("Plan de trabajo no encontrado.");
        }
    }


    // Editar una Fase
    public Fase editarFase(int faseId, Fase nuevosDatos) {
        Trabajador trabajador = jwtUtils.userLogin();
        if (trabajador == null) {
            throw new IllegalStateException("Solo los trabajadores pueden gestionar fases.");
        }

        Optional<Fase> faseOptional = faseRepository.findById(faseId);
        if (faseOptional.isPresent()) {
            Fase faseExistente = faseOptional.get();
            if (!faseExistente.getPlanTrabajo().getTrabajador().equals(trabajador)) {
                throw new IllegalArgumentException("No puedes editar una fase que no pertenece a tus planes de trabajo.");
            }

            PlanTrabajo planTrabajo = faseExistente.getPlanTrabajo();
            if (planTrabajo.getTarea() == null) {
                throw new IllegalArgumentException("El plan de trabajo debe estar asociado a una tarea.");
            }

            // Validar fechas con la fecha de finalización de la tarea asociada
            if (nuevosDatos.getFechaFin().after(planTrabajo.getTarea().getFechaFinalizacion())) {
                throw new IllegalArgumentException("La fecha de finalización de la fase no puede ser posterior a la fecha de finalización de la tarea asociada.");
            }

            faseExistente.setTitulo(nuevosDatos.getTitulo());
            faseExistente.setDescripcion(nuevosDatos.getDescripcion());
            faseExistente.setFechaInicio(nuevosDatos.getFechaInicio());
            faseExistente.setFechaFin(nuevosDatos.getFechaFin());
            return faseRepository.save(faseExistente);
        } else {
            throw new RuntimeException("Fase no encontrada.");
        }
    }


    // Listar todas las fases de un plan de trabajo
    public Set<Fase> listarFases(int planTrabajoId) {
        Trabajador trabajador = jwtUtils.userLogin();
        if (trabajador == null) {
            throw new IllegalStateException("Solo los trabajadores pueden gestionar fases.");
        }

        Optional<PlanTrabajo> planTrabajoOptional = planTrabajoRepository.findById(planTrabajoId);
        if (planTrabajoOptional.isPresent()) {
            PlanTrabajo planTrabajo = planTrabajoOptional.get();
            if (!planTrabajo.getTrabajador().equals(trabajador)) {
                throw new IllegalArgumentException("No puedes ver las fases de un plan de trabajo que no te pertenece.");
            }

            return planTrabajo.getFases();
        } else {
            throw new RuntimeException("Plan de trabajo no encontrado.");
        }
    }

    // Eliminar una Fase
    public void eliminarFase(int faseId) {
        Trabajador trabajador = jwtUtils.userLogin();
        if (trabajador == null) {
            throw new IllegalStateException("Solo los trabajadores pueden gestionar fases.");
        }

        Optional<Fase> faseOptional = faseRepository.findById(faseId);
        if (faseOptional.isPresent()) {
            Fase fase = faseOptional.get();
            if (!fase.getPlanTrabajo().getTrabajador().equals(trabajador)) {
                throw new IllegalArgumentException("No puedes eliminar una fase que no pertenece a tus planes de trabajo.");
            }

            faseRepository.delete(fase);
        } else {
            throw new RuntimeException("Fase no encontrada.");
        }
    }
}
