package ManyWorker.service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ManyWorker.entity.Cliente;
import ManyWorker.entity.Mensaje;
import ManyWorker.entity.Solicitud;
import ManyWorker.entity.Trabajador;
import ManyWorker.repository.MensajeRepository;
import ManyWorker.repository.SolicitudRepository;
import ManyWorker.security.JWTUtils;
import jakarta.transaction.Transactional;

@Service
public class SolicitudService {

    @Autowired
    private SolicitudRepository solicitudRepository;
    
    @Autowired 
    private ClienteService clienteService;
    
    @Autowired
    private TrabajadorService trabajadorService;
    
    @Autowired
    private MensajeRepository mensajeRepository;
    
    private JWTUtils JWTUtils;

    public Set<Solicitud> getAllSolicitudesByTrabajador() {
		Trabajador trabajador = JWTUtils.userLogin();
		return trabajador.getSolicitudes();
	}

	public Set<Solicitud> getAllSolicitudesByCliente() {
		Cliente cliente = JWTUtils.userLogin();
		return cliente.getSolicitudes();
	}

	public Solicitud getSolicitudById(int id) {
		Optional<Solicitud> solicitudO = solicitudRepository.findById(id);
		if (solicitudO.isPresent()) {
			Object userLogin = JWTUtils.userLogin();
			if (userLogin instanceof Cliente) {
				Cliente cliente = (Cliente) userLogin;
				cliente.getSolicitudes().contains(solicitudO.get());
				return solicitudO.get();
			} else if (userLogin instanceof Trabajador) {
				Trabajador caseta = (Trabajador) userLogin;
				caseta.getSolicitudes().contains(solicitudO.get());
				return solicitudO.get();
			}
		}
		return null;
	}

	@Transactional
	public Solicitud save(Solicitud s, int idCliente, double precio, String comentario) {
	    Solicitud res = null;

	    Optional<Cliente> clienteOptional = clienteService.getClienteById(idCliente);
	    if (clienteOptional.isPresent()) {

	        // Verificar si la tarea tiene un precio máximo definido
	        if (s.getTarea() != null) {
	            double precioMaximo = s.getTarea().getPrecioMaximo();  // Suponiendo que la tarea tiene un precio máximo
	            if (precio > precioMaximo) {
	                throw new IllegalArgumentException("El precio ofrecido excede el precio máximo de la tarea.");
	            }
	        }

	        s.setEstado("PENDIENTE");
	        s.setPrecio(precio);
	        s.setComentario(comentario);

	        Trabajador trabajador = JWTUtils.userLogin();
	        trabajador.getSolicitudes().add(s);

	        Cliente cliente = clienteOptional.get();
	        cliente.getSolicitudes().add(s);
	        clienteService.saveCliente(cliente);

	        res = solicitudRepository.save(s);

	        // Asociar la solicitud al trabajador y guardarlo
	        trabajador.getSolicitudes().add(res);
	        trabajadorService.saveTrabajador(trabajador);
	    }
	    return res;
	}


	public boolean acceptSolicitud(int id) {
	    boolean res = false;
	    Optional<Solicitud> solicitudO = solicitudRepository.findById(id);
	    if (solicitudO.isPresent()) {
	        Solicitud solicitud = solicitudO.get();
	        Cliente cliente = JWTUtils.userLogin();  


	        if (cliente.getSolicitudes().contains(solicitud)) {
	            solicitud.setEstado("ACEPTADO");
	            solicitudRepository.save(solicitud); 

	            res = true;

	            // Crear el mensaje para el cliente
	            Mensaje mensajeCliente = new Mensaje();
	            mensajeCliente.setRemitente(cliente);  
	            mensajeCliente.setDestinatario(cliente);  
	            mensajeCliente.setFechaHora(LocalDateTime.now());
	            mensajeCliente.setAsunto("Estado de la solicitud: Aceptada");
	            mensajeCliente.setCuerpo("Tu solicitud ha sido aceptada por el cliente.");
	            mensajeRepository.save(mensajeCliente);  

	            // Crear el mensaje para el trabajador
	            Trabajador trabajador = solicitud.getTrabajador();  
	            Mensaje mensajeTrabajador = new Mensaje();
	            mensajeTrabajador.setRemitente(cliente);  
	            mensajeTrabajador.setDestinatario(trabajador); 
	            mensajeTrabajador.setFechaHora(LocalDateTime.now());
	            mensajeTrabajador.setAsunto("Estado de la solicitud: Aceptada");
	            mensajeTrabajador.setCuerpo("La solicitud que realizaste ha sido aceptada por el cliente.");
	            mensajeRepository.save(mensajeTrabajador);  
	        }
	    }
	    return res;
	}
	
	public boolean refuseSolicitud(int id) {
	    boolean res = false;
	    Optional<Solicitud> solicitudO = solicitudRepository.findById(id);
	    if (solicitudO.isPresent()) {
	        Solicitud solicitud = solicitudO.get();
	        Cliente cliente = JWTUtils.userLogin();  

	       
	        if (cliente.getSolicitudes().contains(solicitud)) {
	            solicitud.setEstado("RECHAZADO");
	            solicitudRepository.save(solicitud);  

	            res = true;

	            // Crear el mensaje para el cliente
	            Mensaje mensajeCliente = new Mensaje();
	            mensajeCliente.setRemitente(cliente);  
	            mensajeCliente.setDestinatario(cliente);  
	            mensajeCliente.setFechaHora(LocalDateTime.now());
	            mensajeCliente.setAsunto("Estado de la solicitud: Rechazada");
	            mensajeCliente.setCuerpo("Tu solicitud ha sido rechazada por el cliente.");
	            mensajeRepository.save(mensajeCliente);  

	            // Crear el mensaje para el trabajador
	            Trabajador trabajador = solicitud.getTrabajador();  
	            Mensaje mensajeTrabajador = new Mensaje();
	            mensajeTrabajador.setRemitente(cliente); 
	            mensajeTrabajador.setDestinatario(trabajador);  
	            mensajeTrabajador.setFechaHora(LocalDateTime.now());
	            mensajeTrabajador.setAsunto("Estado de la solicitud: Rechazada");
	            mensajeTrabajador.setCuerpo("La solicitud que realizaste ha sido rechazada por el cliente.");
	            mensajeRepository.save(mensajeTrabajador);  
	        }
	    }
	    return res;
	}



	public boolean deleteSolicitud(int id) {
		boolean res = false;
		Optional<Solicitud> solicitudO = solicitudRepository.findById(id);
		if (solicitudO.isPresent() && solicitudO.get().getEstado().equals("PENDIENTE")) {
			Cliente cliente = JWTUtils.userLogin();
			if (cliente.getSolicitudes().contains(solicitudO.get())) {
				solicitudRepository.deleteById(id);
				res = true;
			}
		}
		return res;
	}

}
