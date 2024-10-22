package ManyWorker.entity;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;



@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Actor extends DomainEntity {
    @Getter
    @Setter

    @NotBlank
    private String nombre;

    @NotBlank
    private String primerApellido;

    private String segundoApellido;

    private String foto;

    @Email
    private String email;

    @Pattern(regexp = "[6-9][0-9]{8}", message = "El teléfono debe tener 9 dígitos y comenzar con 6, 7, 8 o 9")
    private String telefono;

    private String direccion;

    @NotNull
    @Min(0)
    private Integer numeroPerfilesSociales;

    @OneToMany(mappedBy = "remitente")
    private List<Mensaje> mensajesEnviados;

    @OneToMany(mappedBy = "destinatario")
    private List<Mensaje> mensajesRecibidos;

    // Mensajes que este Actor ha borrado
    @OneToMany(mappedBy = "actorBorrado")
    private List<Mensaje> mensajesBorrados;

    @OneToMany(mappedBy = "actor")
    private List<PerfilSocial> perfilesSociales;

    public Actor() {
        super();
    }
}



