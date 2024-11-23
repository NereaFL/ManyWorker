package ManyWorker.entity;

import java.util.List;

import org.hibernate.validator.constraints.URL;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Actor extends DomainEntity {

    @NotBlank
    private String nombre;

    @NotBlank
    private String primerApellido;

    private String segundoApellido;
    
    @URL(message = "El enlace de la foto debe ser una URL válida")
    private String foto;

    @Email
    private String email;

    @Pattern(regexp = "[6-9][0-9]{8}", message = "El teléfono debe tener 9 dígitos y comenzar con 6, 7, 8 o 9")
    private String telefono;

    private String direccion;

    @NotNull
    @Min(0)
    private Integer numeroPerfilesSociales;

    private String password; // Nuevo campo para la autenticación

    // Relaciones unidireccionales a Mensaje y PerfilSocial
    @OneToMany
    private List<Mensaje> mensajesEnviados;

    @OneToMany
    private List<Mensaje> mensajesRecibidos;

    @OneToMany
    private List<Mensaje> mensajesBorrados;

    @OneToMany
    private List<PerfilSocial> perfilesSociales;

    public Actor() {
        super();
    }

    public Actor(String nombre, String primerApellido) {
        this.nombre = nombre;
        this.primerApellido = primerApellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getNumeroPerfilesSociales() {
        return numeroPerfilesSociales;
    }

    public void setNumeroPerfilesSociales(Integer numeroPerfilesSociales) {
        this.numeroPerfilesSociales = numeroPerfilesSociales;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Mensaje> getMensajesEnviados() {
        return mensajesEnviados;
    }

    public void setMensajesEnviados(List<Mensaje> mensajesEnviados) {
        this.mensajesEnviados = mensajesEnviados;
    }

    public List<Mensaje> getMensajesRecibidos() {
        return mensajesRecibidos;
    }

    public void setMensajesRecibidos(List<Mensaje> mensajesRecibidos) {
        this.mensajesRecibidos = mensajesRecibidos;
    }

    public List<Mensaje> getMensajesBorrados() {
        return mensajesBorrados;
    }

    public void setMensajesBorrados(List<Mensaje> mensajesBorrados) {
        this.mensajesBorrados = mensajesBorrados;
    }

    public List<PerfilSocial> getPerfilesSociales() {
        return perfilesSociales;
    }

    public void setPerfilesSociales(List<PerfilSocial> perfilesSociales) {
        this.perfilesSociales = perfilesSociales;
    }
}
