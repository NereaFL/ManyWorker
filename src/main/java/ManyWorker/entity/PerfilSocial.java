package ManyWorker.entity;

import org.hibernate.validator.constraints.URL;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;

@Entity
public class PerfilSocial extends DomainEntity {

    @NotBlank
    private String apodo;

    @NotBlank
    private String nombreRedSocial;

    @URL(message = "El enlace debe ser una URL válida")
    private String enlace;

    public PerfilSocial() {
        super();
    }

    // Getters y setters
    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public String getNombreRedSocial() {
        return nombreRedSocial;
    }

    public void setNombreRedSocial(String nombreRedSocial) {
        this.nombreRedSocial = nombreRedSocial;
    }

    public String getEnlace() {
        return enlace;
    }

    public void setEnlace(String enlace) {
        this.enlace = enlace;
    }
}

