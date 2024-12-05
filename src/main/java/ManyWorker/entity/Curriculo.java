package ManyWorker.entity;

import org.hibernate.validator.constraints.URL;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Curriculo extends DomainEntity {
	
	@NotBlank
    private String codigo;   
	
	@URL(message = "El enlace del archivo debe ser una URL válida")
    private String archivoPdf;
	
	@ManyToOne
	private Trabajador trabajador;

	public Trabajador getTrabajador() {
		return trabajador;
	}

	public void setTrabajador(Trabajador trabajador) {
		this.trabajador = trabajador;
	}

	public Curriculo() {
		super();
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getArchivoPdf() {
		return archivoPdf;
	}

	public void setArchivoPdf(String archivoPdf) {
		this.archivoPdf = archivoPdf;
	}  
}
