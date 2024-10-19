package ManyWorker.entity;

import java.sql.Date;

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
public class Mensaje {
	@Getter
	@Setter
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotBlank
	@ManyToOne
    @JoinColumn(name = "remitente_id")
    private Actor remitente;

	@NotBlank
    @ManyToOne
    @JoinColumn(name = "destinatario_id")
    private Actor destinatario;
	
	@NotBlank
	private Date fechaHora;
	
	@NotBlank
	private String asunto;
	
	@NotBlank
	private String cuerpo;

	public Mensaje() {
		super();
	}
	
}
