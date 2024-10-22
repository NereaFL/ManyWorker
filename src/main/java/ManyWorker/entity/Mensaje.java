package ManyWorker.entity;

import java.sql.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;



@Entity
public class Mensaje extends DomainEntity {

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "remitente_id", nullable = false)
    private Actor remitente;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "destinatario_id", nullable = false)
    private Actor destinatario;

    @NotNull
    private Date fechaHora;

    @NotBlank
    private String asunto;

    @NotBlank
    private String cuerpo;

    // Propiedad para marcar el borrado del mensaje
    @ManyToOne
    @JoinColumn(name = "actor_borrado_id") // Actor que ha borrado el mensaje
    private Actor actorBorrado;

    public Mensaje() {
        super();
    }

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

	public Actor getActorBorrado() {
		return actorBorrado;
	}

	public void setActorBorrado(Actor actorBorrado) {
		this.actorBorrado = actorBorrado;
	}
    
    
}



