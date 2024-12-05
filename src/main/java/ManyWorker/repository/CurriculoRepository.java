package ManyWorker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ManyWorker.entity.Curriculo;
import ManyWorker.entity.Trabajador;

@Repository
public interface CurriculoRepository extends JpaRepository<Curriculo, Integer> {

	List<Curriculo> findByTrabajador(Trabajador trabajador);

}
