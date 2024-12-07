package ManyWorker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ManyWorker.entity.Trabajador;
import ManyWorker.entity.Tutorial;

@Repository
public interface TutorialRepository extends JpaRepository<Tutorial, Integer> {

	List<Tutorial> findByTrabajadorId(int trabajadorId);

}
