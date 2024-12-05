package ManyWorker.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ManyWorker.entity.Administrador;
import ManyWorker.entity.Cliente;
import ManyWorker.entity.Mensaje;
import ManyWorker.entity.Patrocinador;
import ManyWorker.entity.Solicitud;
import ManyWorker.entity.Trabajador;
import ManyWorker.repository.MensajeRepository;
import ManyWorker.security.JWTUtils;
import jakarta.transaction.Transactional;

@Service
public class MensajeService {

    @Autowired
    private MensajeRepository mensajeRepository;
    
    @Autowired
    private ClienteService clienteService;
    
    @Autowired
    private AdministradorService administradorService;

    @Autowired
    private PatrocinadorService patrocinadorService;
    
    @Autowired
    private TrabajadorService trabajadorService;

    
    private JWTUtils JWTUtils;

    public Set<Mensaje> getAllMensajesByTrabajador() {
		Trabajador trabajador = JWTUtils.userLogin();
		return trabajador.getMensajes();
	}

	public Set<Mensaje> getAllMensajesByCliente() {
		Cliente cliente = JWTUtils.userLogin();
		return cliente.getMensajes();
	}
	
	public Set<Mensaje> getAllMensajesByAdministrador() {
		Administrador administrador = JWTUtils.userLogin();
		return administrador.getMensajes();
	}
	
	public Set<Mensaje> getAllMensajesByPatrocinador() {
		Patrocinador patrocinador = JWTUtils.userLogin();
		return patrocinador.getMensajes();
	}

	public Mensaje getMensajeById(int id) {
		Optional<Mensaje> mensaje0 = mensajeRepository.findById(id);
		if (mensaje0.isPresent()) {
			Object userLogin = JWTUtils.userLogin();
			if (userLogin instanceof Cliente) {
				Cliente cliente = (Cliente) userLogin;
				cliente.getMensajes().contains(mensaje0.get());
				return mensaje0.get();
			} else if (userLogin instanceof Trabajador) {
				Trabajador trabajador = (Trabajador) userLogin;
				trabajador.getMensajes().contains(mensaje0.get());
				return mensaje0.get();
			} else if (userLogin instanceof Administrador) {
				Administrador administrador = (Administrador) userLogin;
				administrador.getMensajes().contains(mensaje0.get());
				return mensaje0.get();
			} else if (userLogin instanceof Patrocinador) {
			Patrocinador patrocinador = (Patrocinador) userLogin;
			patrocinador.getMensajes().contains(mensaje0.get());
			return mensaje0.get();
		}
		}
		return null;
	}

	@Transactional
	public Mensaje saveMensaje(Mensaje mensaje) {
	    Mensaje resultado = null;

	    // Obtener el usuario autenticado
	    Object userLogin = JWTUtils.userLogin();

	    if (userLogin != null) {
	        // Guardar el mensaje en el repositorio
	        resultado = mensajeRepository.save(mensaje);

	        // Asociar el mensaje según el tipo de usuario
	        if (userLogin instanceof Cliente) {
	            Cliente cliente = (Cliente) userLogin;
	            cliente.getMensajes().add(resultado);
	            clienteService.saveCliente(cliente);
	        } else if (userLogin instanceof Trabajador) {
	            Trabajador trabajador = (Trabajador) userLogin;
	            trabajador.getMensajes().add(resultado);
	            trabajadorService.saveTrabajador(trabajador);
	        } else if (userLogin instanceof Administrador) {
	            Administrador administrador = (Administrador) userLogin;
	            administrador.getMensajes().add(resultado);
	            administradorService.saveAdmin(administrador);
	        } else if (userLogin instanceof Patrocinador) {
	            Patrocinador patrocinador = (Patrocinador) userLogin;
	            patrocinador.getMensajes().add(resultado);
	            patrocinadorService.savePatrocinador(patrocinador);
	        }
	    }

	    return resultado;
	}


	@Transactional
	public void deleteMensaje(Mensaje mensaje) {
	    // Obtener el usuario autenticado
	    Object userLogin = JWTUtils.userLogin();

	    if (userLogin != null) {
	        // Desasociar el mensaje según el tipo de usuario
	        if (userLogin instanceof Cliente) {
	            Cliente cliente = (Cliente) userLogin;
	            cliente.getMensajes().remove(mensaje);
	            clienteService.saveCliente(cliente);
	        } else if (userLogin instanceof Trabajador) {
	            Trabajador trabajador = (Trabajador) userLogin;
	            trabajador.getMensajes().remove(mensaje);
	            trabajadorService.saveTrabajador(trabajador);
	        } else if (userLogin instanceof Administrador) {
	            Administrador administrador = (Administrador) userLogin;
	            administrador.getMensajes().remove(mensaje);
	            administradorService.saveAdmin(administrador);
	        } else if (userLogin instanceof Patrocinador) {
	            Patrocinador patrocinador = (Patrocinador) userLogin;
	            patrocinador.getMensajes().remove(mensaje);
	            patrocinadorService.savePatrocinador(patrocinador);
	        }

	        // Eliminar el mensaje del repositorio
	        mensajeRepository.delete(mensaje);
	    }
	}
	
	@Transactional
    public Mensaje enviarMensajeBroadcast(Mensaje mensaje) {
        // Verificar si el usuario es un Administrador
        Object userLogin = JWTUtils.userLogin();
        if (!(userLogin instanceof Administrador)) {
            throw new SecurityException("Solo los administradores pueden enviar mensajes a todos los actores.");
        }

        // Guardar el mensaje en el repositorio
        Mensaje resultado = mensajeRepository.save(mensaje);

        // Asociar el mensaje a todos los actores
        clienteService.getAllCliente().forEach(cliente -> {
            cliente.getMensajes().add(resultado);
            clienteService.saveCliente(cliente);
        });
        trabajadorService.getAllTrabajadores().forEach(trabajador -> {
            trabajador.getMensajes().add(resultado);
            trabajadorService.saveTrabajador(trabajador);
        });
        patrocinadorService.getAllPatrocinadores().forEach(patrocinador -> {
            patrocinador.getMensajes().add(resultado);
            patrocinadorService.savePatrocinador(patrocinador);
        });
        administradorService.getAllAdmins().forEach(administrador -> {
            administrador.getMensajes().add(resultado);
            administradorService.saveAdmin(administrador);
        });

        return resultado;
    }

}
