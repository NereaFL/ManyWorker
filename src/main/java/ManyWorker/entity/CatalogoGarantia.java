package ManyWorker.entity;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;

@Entity
public class CatalogoGarantia extends DomainEntity {
	 
	@NotBlank
	private String titulo;
	
	@NotBlank
	private String terminosLeyes;

	//Contructor vacio
	public CatalogoGarantia() {
		super();
	}

	//Getters and Setters
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTerminosLeyes() {
		return terminosLeyes;
	}

	public void setTerminosLeyes(String terminosLeyes) {
		this.terminosLeyes = terminosLeyes;
	}
}