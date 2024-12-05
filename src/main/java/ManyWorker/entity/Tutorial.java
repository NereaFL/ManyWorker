package ManyWorker.entity;

import java.sql.Date;
import java.time.LocalDateTime;

import org.hibernate.validator.constraints.URL;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Tutorial extends DomainEntity {
	
	@NotBlank
    private String titulo;
	
	@NotBlank
    private LocalDateTime fechaHoraActualizacion;
	
	@NotBlank
    private String resumen;
	
	@URL(message = "El enlace de la foto debe ser una URL válida")
    private String imagen;  
    
    @NotBlank
    private String textoTutorial;
    
    @ManyToOne
    private Trabajador trabajador;

	public Trabajador getTrabajador() {
		return trabajador;
	}

	public void setTrabajador(Trabajador trabajador) {
		this.trabajador = trabajador;
	}

	public Tutorial() {
		super();
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public LocalDateTime getFechaHoraActualizacion() {
		return fechaHoraActualizacion;
	}

	public void setFechaHoraActualizacion(LocalDateTime localDateTime) {
		this.fechaHoraActualizacion = localDateTime;
	}

	public String getResumen() {
		return resumen;
	}

	public void setResumen(String resumen) {
		this.resumen = resumen;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getTextoTutorial() {
		return textoTutorial;
	}

	public void setTextoTutorial(String textoTutorial) {
		this.textoTutorial = textoTutorial;
	}

}