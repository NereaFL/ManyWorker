package ManyWorker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ManyWorker.entity.Tutorial;

public interface TutorialRepository extends JpaRepository<Tutorial, Integer> {

}
