package ManyWorker.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ManyWorker.entity.Administrador;

public interface AdministradorRepository extends JpaRepository<Administrador, Integer> {
	public Optional<Administrador> findByUsername(String username);

}
