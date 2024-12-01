package ManyWorker.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ManyWorker.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	public Optional<Cliente> findByUsername(String username);

}
