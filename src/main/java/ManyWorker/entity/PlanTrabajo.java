package ManyWorker.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinTable;
import jakarta.validation.constraints.NotNull;

@Entity
public class PlanTrabajo extends DomainEntity {

    @NotNull
    private int numeroFases;

    // Relación uno a muchos (PlanTrabajo tiene 0 o más Fases)
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Fase> fases;

 
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
