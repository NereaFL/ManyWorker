package ManyWorker.entity;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;

@Entity
public class Trabajador extends Actor {
	
	@NotNull
    private String nombreComercial;

    @OneToMany
    private List<Curriculo> curriculos = new ArrayList<>();

    @OneToMany
    private List<Tutorial> tutoriales = new ArrayList<>();

    public Trabajador(String nombre, String primerApellido, String nombreComercial) {
        super(nombre, primerApellido);
        this.nombreComercial = nombre + primerApellido;
    }

    public Trabajador() {
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public List<Curriculo> getCurriculos() {
        return curriculos;
    }

    public void setCurriculos(List<Curriculo> curriculos) {
        this.curriculos = curriculos;
    }

    public List<Tutorial> getTutoriales() {
        return tutoriales;
    }

    public void setTutoriales(List<Tutorial> tutoriales) {
        this.tutoriales = tutoriales;
    }
}
