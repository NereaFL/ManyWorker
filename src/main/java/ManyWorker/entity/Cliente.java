package ManyWorker.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.List;
import java.util.Set;

@Entity
public class Cliente extends Actor {

    // Relación unidireccional hacia TareaReparacion
    @OneToMany
    private Set<TareaReparacion> tareasReparacion;
    
    @OneToMany
	private Set<Solicitud> solicitudes;

	public Set<Solicitud> getSolicitudes() {
		return solicitudes;
	}

	public void setSolicitudes(Set<Solicitud> solicitudes) {
		this.solicitudes = solicitudes;
	}

    public Cliente() {
        super();
    }

    // Getters y setters
    public Set<TareaReparacion> getTareasReparacion() {
        return tareasReparacion;
    }

    public void setTareasReparacion(Set<TareaReparacion> tareasReparacion) {
        this.tareasReparacion = tareasReparacion;
    }
    
}

