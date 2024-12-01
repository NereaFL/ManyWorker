package ManyWorker.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ManyWorker.entity.Trabajador;

public interface TrabajadorRepository extends JpaRepository<Trabajador, Integer> {
	public Optional<Trabajador> findByUsername(String username);
}
