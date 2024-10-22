package ManyWorker.entity;

import java.sql.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;



@Entity
public class Mensaje extends DomainEntity {
    @Getter
    @Setter

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "remitente_id", nullable = false)
    private Actor remitente;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "destinatario_id", nullable = false)
    private Actor destinatario;

    @NotNull
    private Date fechaHora;

    @NotBlank
    private String asunto;

    @NotBlank
    private String cuerpo;

    // Propiedad para marcar el borrado del mensaje
    @ManyToOne
    @JoinColumn(name = "actor_borrado_id") // Actor que ha borrado el mensaje
    private Actor actorBorrado;

    public Mensaje() {
        super();
    }
}



