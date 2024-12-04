package ManyWorker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ManyWorker.entity.Tutorial;

@Repository
public interface TutorialRepository extends JpaRepository<Tutorial, Integer> {

}
