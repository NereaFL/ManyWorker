package ManyWorker.entity;

import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Mensaje extends DomainEntity {

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "remitente_id", referencedColumnName = "id", nullable = false)
    private Actor remitente;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destinatario_id", referencedColumnName = "id", nullable = false)
    private Actor destinatario;

    @NotNull
    private LocalDateTime fechaHora;

    @NotBlank
    private String asunto;

    @NotBlank
    private String cuerpo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "actorborrado_id", referencedColumnName = "id", nullable = true)
    private Actor actorBorradoId;

    // Getters y setters
    public Actor getRemitente() {
        return remitente;
    }

    public void setRemitente(Actor remitente) {
        this.remitente = remitente;
    }

    public Actor getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Actor destinatario) {
        this.destinatario = destinatario;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
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

    public Actor getActorBorradoId() {
        return actorBorradoId;
    }

    public void setActorBorradoId(Actor actorBorradoId) {
        this.actorBorradoId = actorBorradoId;
    }
}
