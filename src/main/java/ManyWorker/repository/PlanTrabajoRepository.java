package ManyWorker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ManyWorker.entity.PlanTrabajo;
import ManyWorker.entity.Trabajador;

@Repository
public interface PlanTrabajoRepository extends JpaRepository<PlanTrabajo, Integer> {

	List<PlanTrabajo> findByTrabajador(Trabajador trabajador);

}
