package ManyWorker.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ManyWorker.entity.Patrocinador;

public interface PatrocinadorRepository extends JpaRepository<Patrocinador, Integer> {
	public Optional<Patrocinador> findByUsername(String username);

}
