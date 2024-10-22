package ManyWorker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ManyWorker.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
