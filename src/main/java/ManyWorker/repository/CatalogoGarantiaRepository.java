package ManyWorker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ManyWorker.entity.CatalogoGarantia;

@Repository
public interface CatalogoGarantiaRepository extends JpaRepository<CatalogoGarantia, Integer> {

}
