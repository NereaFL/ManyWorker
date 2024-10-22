package ManyWorker.entity;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Fase extends DomainEntity {

    @NotBlank
    private String titulo;

    @NotBlank
    private String descripcion;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date fechaFin;

    // Relación muchos a uno (Fase está asociada a un PlanTrabajo)
    @ManyToOne(optional = false)
    @JoinColumn(name = "plan_trabajo_id", nullable = false)
    private PlanTrabajo planTrabajo;

    // Constructor vacío
    public Fase() {
    }

    // Constructor con parámetros
    public Fase(String titulo, String descripcion, Date fechaInicio, Date fechaFin, PlanTrabajo planTrabajo) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.planTrabajo = planTrabajo;
    }

    // Getters y Setters

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public PlanTrabajo getPlanTrabajo() {
        return planTrabajo;
    }

    public void setPlanTrabajo(PlanTrabajo planTrabajo) {
        this.planTrabajo = planTrabajo;
    }
}
