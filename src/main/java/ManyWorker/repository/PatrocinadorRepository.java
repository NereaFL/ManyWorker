package ManyWorker.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ManyWorker.entity.Patrocinador;

@Repository
public interface PatrocinadorRepository extends JpaRepository<Patrocinador, Integer> {
	public Optional<Patrocinador> findByUsername(String username);

}
