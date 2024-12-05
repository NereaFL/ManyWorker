package ManyWorker.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;

@Entity
public class PlanTrabajo extends DomainEntity {

    @NotNull
    private int numeroFases;

    // Relación uno a muchos (PlanTrabajo tiene 0 o más Fases)
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Fase> fases;
    
    @ManyToOne
    private Trabajador trabajador;
    
    @ManyToOne
    private TareaReparacion tarea;
    

 
    public TareaReparacion getTarea() {
		return tarea;
	}

	public void setTarea(TareaReparacion tarea) {
		this.tarea = tarea;
	}

	public Trabajador getTrabajador() {
		return trabajador;
	}

	public void setTrabajador(Trabajador trabajador) {
		this.trabajador = trabajador;
	}

	// Constructor vacío
    public PlanTrabajo() {
    }

    // Getters y Setters
    public int getNumeroFases() {
        return numeroFases;
    }

    public void setNumeroFases(int numeroFases) {
        this.numeroFases = numeroFases;
    }

    public Set<Fase> getFases() {
        return fases;
    }

    public void setFases(Set<Fase> fases) {
        this.fases = fases;
    }

}
