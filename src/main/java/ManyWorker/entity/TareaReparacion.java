package ManyWorker.entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class TareaReparacion extends DomainEntity {

    @NotNull
    private Date fechaHora;

    @NotBlank
    private String descripcion;

    @NotBlank
    private String direccion;

    @NotNull 
    private double precioMaximo;

    @NotBlank
    private String categoria;

    @NotNull
    private Date fechaFinalizacion;

    @NotNull
    private Long clienteId;

    // Constructor vacío
    public TareaReparacion() {
        super();
    }

    // Constructor con argumentos
    public TareaReparacion(Date fechaHora, String descripcion, String direccion, double precioMaximo, String categoria,
            Date fechaFinalizacion, Long clienteId) {
        super();
        this.fechaHora = fechaHora;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.precioMaximo = precioMaximo;
        this.categoria = categoria;
        this.fechaFinalizacion = fechaFinalizacion;
        this.clienteId = clienteId;
    }

    // Getters y setters
    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }
}
