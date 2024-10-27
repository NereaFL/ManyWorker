package ManyWorker.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
public class Patrocinador extends Actor {

    // Relación uno a muchos hacia Patrocinio
    @OneToMany
    private List<Patrocinio> patrocinios;

    public Patrocinador() {
        super();
    }

    // Getters y setters
    public List<Patrocinio> getPatrocinios() {
        return patrocinios;
    }

    public void setPatrocinios(List<Patrocinio> patrocinios) {
        this.patrocinios = patrocinios;
    }
}
