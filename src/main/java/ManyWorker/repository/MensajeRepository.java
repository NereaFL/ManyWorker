package ManyWorker.repository;

import ManyWorker.entity.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MensajeRepository extends JpaRepository<Mensaje, Integer> {
    List<Mensaje> findByRemitente_IdOrDestinatario_Id(int remitenteId, int destinatarioId);
}
