package ManyWorker.entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
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

    @NotNull
    private Date fechaFinalizacion;

    @NotNull
    private Long clienteId;
    
    @OneToOne
    private CatalogoGarantia catalogoGarantia;
    
    @OneToOne
    private Categoria categoria;
    

    // Constructor vacío
    public TareaReparacion() {
        super();
    }

    // Constructor con argumentos
	public TareaReparacion(@NotNull Date fechaHora, @NotBlank String descripcion, @NotBlank String direccion,
			@NotNull double precioMaximo, @NotNull Date fechaFinalizacion, @NotNull Long clienteId,
			CatalogoGarantia catalogoGarantia, Categoria categoria) {
		super();
		this.fechaHora = fechaHora;
		this.descripcion = descripcion;
		this.direccion = direccion;
		this.precioMaximo = precioMaximo;
		this.fechaFinalizacion = fechaFinalizacion;
		this.clienteId = clienteId;
		this.catalogoGarantia = catalogoGarantia;
		this.categoria = categoria;
	}

    // Getters y setters
    public Date getFechaHora() {
        return fechaHora;
    }

    public CatalogoGarantia getCatalogoGarantia() {
		return catalogoGarantia;
	}

	public void setCatalogoGarantia(CatalogoGarantia catalogoGarantia) {
		this.catalogoGarantia = catalogoGarantia;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
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
