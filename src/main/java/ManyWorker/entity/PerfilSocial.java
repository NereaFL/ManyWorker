package ManyWorker.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
public class PerfilSocial {

    @Getter
    @Setter

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String apodo;

    @NotBlank
    private String nombreRedSocial;

    private String enlace;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "actor_id", nullable = false) // Asegura que actor no sea nulo
    private Actor actor;

    public PerfilSocial() {
        super();
    }
}
