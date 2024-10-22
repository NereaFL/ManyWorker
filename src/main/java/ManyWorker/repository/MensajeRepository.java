package ManyWorker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ManyWorker.entity.Mensaje;

public interface MensajeRepository extends JpaRepository<Mensaje, Integer> {

}
