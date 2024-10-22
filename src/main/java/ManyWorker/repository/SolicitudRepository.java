package ManyWorker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ManyWorker.entity.Solicitud;

public interface SolicitudRepository extends JpaRepository<Solicitud, Integer> {

}
