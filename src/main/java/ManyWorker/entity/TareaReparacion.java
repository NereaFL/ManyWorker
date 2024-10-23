package ManyWorker.entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;

@Entity
public class TareaReparacion extends DomainEntity {

	@NotBlank
	private Date fechaHora;

	@NotBlank
	private String descipcion;

	@NotBlank
	private String direccion;

	@NotBlank
	private double precioMaximo;

	@NotBlank
	private String categoria;

	@NotBlank
	private Date fechaFinalizacion;

	// Constructor Vacio
	public TareaReparacion() {
		super();
	}

	// Constructor con argumentos
	public TareaReparacion(Date fechaHora, String descipcion, String direccion, double precioMaximo, String categoria,
			Date fechaFinalizacion) {
		super();
		this.fechaHora = fechaHora;
		this.descipcion = descipcion;
		this.direccion = direccion;
		this.precioMaximo = precioMaximo;
		this.categoria = categoria;
		this.fechaFinalizacion = fechaFinalizacion;
	}

	public Date getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}

	public String getDescipcion() {
		return descipcion;
	}

	public void setDescipcion(String descipcion) {
		this.descipcion = descipcion;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public double getPrecioMaximo() {
		return precioMaximo;
	}

	public void setPrecioMaximo(double precioMaximo) {
		this.precioMaximo = precioMaximo;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Date getFechaFinalizacion() {
		return fechaFinalizacion;
	}

	public void setFechaFinalizacion(Date fechaFinalizacion) {
		this.fechaFinalizacion = fechaFinalizacion;
	}

}