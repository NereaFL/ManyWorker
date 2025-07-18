package ManyWorker.entity;

import java.util.List;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
public class Solicitud extends DomainEntity {

    @NotBlank
    private String fechaHora;

    @NotNull
    private double precio;

    @NotBlank
    private String comentario;

    @Pattern(regexp = "PENDIENTE|ACEPTADO|RECHAZADO")
    private String estado;

    // Relación de muchos (0 o más) Solicitudes con un Trabajador
    @ManyToOne(optional = false)
    private Trabajador trabajador;

    // Relación unidireccional con PlanTrabajo
    @ManyToOne(optional = false)
    private PlanTrabajo planTrabajo;
    
    @ManyToOne
    private TareaReparacion tarea;

    public TareaReparacion getTarea() {
		return tarea;
	}

	public void setTarea(TareaReparacion tarea) {
		this.tarea = tarea;
	}

	// Constructor vacío
    public Solicitud() {
    }

    // Getters y Setters
    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Trabajador getTrabajador() {
        return trabajador;
    }

    public void setTrabajador(Trabajador trabajador) {
        this.trabajador = trabajador;
    }

    public PlanTrabajo getPlanTrabajo() {
        return planTrabajo;
    }

    public void setPlanTrabajo(PlanTrabajo planTrabajo) {
        this.planTrabajo = planTrabajo;
    }
}
