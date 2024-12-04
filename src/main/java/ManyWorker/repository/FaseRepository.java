package ManyWorker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ManyWorker.entity.Fase;

@Repository
public interface FaseRepository extends JpaRepository<Fase, Integer> {

}
