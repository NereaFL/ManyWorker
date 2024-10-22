package ManyWorker.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
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
    @JoinColumn(name = "trabajador_id", nullable = false)
    private Trabajador trabajador;

    // Relación de muchos (0 o más) Solicitudes con muchas (1 o más) Tareas de Reparación
    @OneToMany(mappedBy = "solicitud", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<TareaReparacion> tareasReparacion;

    // Constructor vacío
    public Solicitud() {
    }

    // Constructor con parámetros
    public Solicitud(String fechaHora, double precio, String comentario, String estado, Trabajador trabajador, Set<TareaReparacion> tareasReparacion) {
        this.fechaHora = fechaHora;
        this.precio = precio;
        this.comentario = comentario;
        this.estado = estado;
        this.trabajador = trabajador;
        this.tareasReparacion = tareasReparacion;
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

    public Set<TareaReparacion> getTareasReparacion() {
        return tareasReparacion;
    }

    public void setTareasReparacion(Set<TareaReparacion> tareasReparacion) {
        this.tareasReparacion = tareasReparacion;
    }
}
