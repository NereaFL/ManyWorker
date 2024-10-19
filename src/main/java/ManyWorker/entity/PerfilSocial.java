package ManyWorker.entity;

import javax.validation.constraints.NotBlank;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
public class PerfilSocial {

	@Getter
	@Setter
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotBlank
	private String apodo;
	
	@NotBlank
	private String nombreRedSocial;
	
	private String enlace;
	
	@ManyToOne
    @JoinColumn(name = "actor_id")
    private Actor actor;

	public PerfilSocial() {
		super();
	}
	
}
