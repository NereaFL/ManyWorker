package ManyWorker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ManyWorker.entity.PlanTrabajo;

@Repository
public interface PlanTrabajoRepository extends JpaRepository<PlanTrabajo, Integer> {

}
