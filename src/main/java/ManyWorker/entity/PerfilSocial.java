package ManyWorker.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class PerfilSocial extends DomainEntity {
 
    @NotBlank
    private String apodo;

    @NotBlank
    private String nombreRedSocial;

    @URL
    private String enlace;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "actor_id", nullable = false) // Asegura que actor no sea nulo
    private Actor actor;

    public PerfilSocial() {
        super();
    }

	public String getApodo() {
		return apodo;
	}

	public void setApodo(String apodo) {
		this.apodo = apodo;
	}

	public String getNombreRedSocial() {
		return nombreRedSocial;
	}

	public void setNombreRedSocial(String nombreRedSocial) {
		this.nombreRedSocial = nombreRedSocial;
	}

	public String getEnlace() {
		return enlace;
	}

	public void setEnlace(String enlace) {
		this.enlace = enlace;
	}

	public Actor getActor() {
		return actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}
    
    
}
