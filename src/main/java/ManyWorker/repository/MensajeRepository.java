package ManyWorker.repository;

import ManyWorker.entity.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MensajeRepository extends JpaRepository<Mensaje, Integer> {
    List<Mensaje> findByRemitenteIdOrDestinatarioId(int remitenteId, int destinatarioId);
}
