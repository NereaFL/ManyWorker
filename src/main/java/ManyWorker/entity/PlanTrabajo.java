package ManyWorker.entity;

import java.util.Set;

import javax.validation.constraints.NotNull;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class PlanTrabajo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private int numeroFases;

    // Relación uno a muchos (PlanTrabajo tiene 0 o más Fases)
    @OneToMany(mappedBy = "planTrabajo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Fase> fases;

    // Relación muchos a muchos (PlanTrabajo puede tener 0 o más Trabajadores y viceversa)
    @ManyToMany
    @JoinTable(
        name = "plan_trabajo_trabajador",
        joinColumns = @JoinColumn(name = "plan_trabajo_id"),
        inverseJoinColumns = @JoinColumn(name = "trabajador_id")
    )
    private Set<Trabajador> trabajadores;

    // Constructor vacío
    public PlanTrabajo() {
    }

    // Constructor con parámetros
    public PlanTrabajo(int numeroFases, Set<Fase> fases, Set<Trabajador> trabajadores) {
        this.numeroFases = numeroFases;
        this.fases = fases;
        this.trabajadores = trabajadores;
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Set<Trabajador> getTrabajadores() {
        return trabajadores;
    }

    public void setTrabajadores(Set<Trabajador> trabajadores) {
        this.trabajadores = trabajadores;
    }
}
