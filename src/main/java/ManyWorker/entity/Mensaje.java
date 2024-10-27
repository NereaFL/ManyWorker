package ManyWorker.entity;

import java.sql.Date;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Mensaje extends DomainEntity {

    @NotNull
    private Long remitenteId; // ID del remitente en lugar de un objeto Actor

    @NotNull
    private Long destinatarioId; // ID del destinatario en lugar de un objeto Actor

    @NotNull
    private Date fechaHora;

    @NotBlank
    private String asunto;

    @NotBlank
    private String cuerpo;

    private Long actorBorradoId; // ID del actor que borró el mensaje, si aplica

    public Mensaje() {
        super();
    }

    // Getters y setters
    public Long getRemitenteId() {
        return remitenteId;
    }

    public void setRemitenteId(Long remitenteId) {
        this.remitenteId = remitenteId;
    }

    public Long getDestinatarioId() {
        return destinatarioId;
    }

    public void setDestinatarioId(Long destinatarioId) {
        this.destinatarioId = destinatarioId;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public Long getActorBorradoId() {
        return actorBorradoId;
    }

    public void setActorBorradoId(Long actorBorradoId) {
        this.actorBorradoId = actorBorradoId;
    }
}



