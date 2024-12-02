package ManyWorker.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
public class Cliente extends Actor {

    // Relación unidireccional hacia TareaReparacion
    @OneToMany
    private List<TareaReparacion> tareasReparacion;

    public Cliente() {
        super();
    }

    // Getters y setters
    public List<TareaReparacion> getTareasReparacion() {
        return tareasReparacion;
    }

    public void setTareasReparacion(List<TareaReparacion> tareasReparacion) {
        this.tareasReparacion = tareasReparacion;
    }
    
}

