package ManyWorker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ManyWorker.entity.Patrocinio;

@Repository
public interface PatrocinioRepository extends JpaRepository<Patrocinio, Integer> {

}
