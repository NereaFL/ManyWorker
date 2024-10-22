package ManyWorker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ManyWorker.entity.Trabajador;

public interface TrabajadorRepository extends JpaRepository<Trabajador, Integer> {

}
