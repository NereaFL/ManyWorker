package ManyWorker.entity;

import java.util.List;

import org.hibernate.validator.constraints.URL;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Patrocinio extends DomainEntity {

	@URL(message = "El enlace de la foto debe ser una URL válida")
	private String banner;

	@URL(message = "El enlace debe ser una URL válida")
	private String enlacePaginaObjetivo;
	
	@OneToMany
	private List<Patrocinador> patrocinador;


	public List<Patrocinador> getPatrocinador() {
		return patrocinador;
	}

	public void setPatrocinador(List<Patrocinador> patrocinador) {
		this.patrocinador = patrocinador;
	}

	// Constructor vacío
	public Patrocinio() {
		super();
	}

	// Getters y setters
	public String getBanner() {
		return banner;
	}

	public void setBanner(String banner) {
		this.banner = banner;
	}

	public String getEnlacePaginaObjetivo() {
		return enlacePaginaObjetivo;
	}

	public void setEnlacePaginaObjetivo(String enlacePaginaObjetivo) {
		this.enlacePaginaObjetivo = enlacePaginaObjetivo;
	}

}
