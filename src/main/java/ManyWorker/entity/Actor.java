package ManyWorker.entity;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
public abstract class Actor {
	
	@Getter
	@Setter
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
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
	
	@NotBlank
	private int numeroPerfilesSociales;
	
	@OneToMany(mappedBy = "remitente")
    private List<Mensaje> mensajesEnviados;

    @OneToMany(mappedBy = "destinatario")
    private List<Mensaje> mensajesRecibidos;

    @OneToMany(mappedBy = "actor")
    private List<Mensaje> mensajesBorrados;

    @OneToMany(mappedBy = "actor")
    private List<PerfilSocial> perfilesSociales;

	public Actor() {
		super();
	}
	
}
