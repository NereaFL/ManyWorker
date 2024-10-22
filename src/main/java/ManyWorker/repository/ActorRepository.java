package ManyWorker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ManyWorker.entity.Actor;

public interface ActorRepository extends JpaRepository<Actor, Integer>  {

}
