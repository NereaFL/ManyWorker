package ManyWorker.entity;

import javax.validation.constraints.NotBlank;

import jakarta.persistence.Entity;

@Entity
public class CatalogoGarantia {
	 
	@NotBlank
	private String titulo;
	
	@NotBlank
	private String terminosLeyes;

	//Contructor vacio
	public CatalogoGarantia() {
		super();
	}

	//Constructor con argumentos 
	public CatalogoGarantia(@NotBlank String titulo, @NotBlank String terminosLeyes) {
		super();
		this.titulo = titulo;
		this.terminosLeyes = terminosLeyes;
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