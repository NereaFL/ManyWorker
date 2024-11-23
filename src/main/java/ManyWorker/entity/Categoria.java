package ManyWorker.entity;

import jakarta.persistence.Entity;

@Entity
public class Categoria extends DomainEntity{
	private String nombreCategoria;

	public Categoria() {
		super();
	}

	public String getNombreCategoria() {
		return nombreCategoria;
	}

	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}
	
	
	
	

}
