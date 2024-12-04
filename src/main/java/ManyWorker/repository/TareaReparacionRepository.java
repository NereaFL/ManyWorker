package ManyWorker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ManyWorker.entity.TareaReparacion;

@Repository
public interface TareaReparacionRepository extends JpaRepository<TareaReparacion, Integer> {

}
