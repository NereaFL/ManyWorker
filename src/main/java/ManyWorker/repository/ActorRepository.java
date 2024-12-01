package ManyWorker.repository;

import ManyWorker.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer> {
	public Optional<Actor> findByUsername(String username);
	
    // Verificar si el actor existe por su email (método personalizado)
    public boolean existsByEmail(String email);

    // Buscar actor por email (opcional)
    public Optional<Actor> findByEmail(String email);
}
