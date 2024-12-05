package ManyWorker.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ManyWorker.entity.Administrador;
import ManyWorker.entity.Cliente;
import ManyWorker.entity.Solicitud;
import ManyWorker.entity.Trabajador;
import ManyWorker.repository.SolicitudRepository;
import ManyWorker.security.JWTUtils;
import jakarta.transaction.Transactional;

@Service
public class SolicitudService {

    @Autowired
    private SolicitudRepository solicitudRepository;
    
    @Autowired 
    private ClienteService clienteService;
    
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
	public Solicitud save(Solicitud s, int idCliente) {
		Solicitud res = null;
		Optional<Cliente> cliente0 = clienteService.getClienteById(idCliente);
		if (!cliente0.isEmpty()) {
			s.setEstado("PENDIENTE");
			Trabajador trabajador = JWTUtils.userLogin();

			res = solicitudRepository.save(s);
			trabajador.getSolicitudes().add(res);

			Cliente cliente = cliente0.get();
			cliente.getSolicitudes().add(s);
			clienteService.saveCliente(cliente);
		}
		return res;
	}

	public boolean acceptSolicitud(int id) {
		boolean res = false;
		Optional<Solicitud> solicitudO = solicitudRepository.findById(id);
		if (solicitudO.isPresent()) {
			Cliente cliente = JWTUtils.userLogin();
			if (cliente.getSolicitudes().contains(solicitudO.get())) {
				solicitudO.get().setEstado("ACEPTADO");
				res = true;
			}
		}
		return res;
	}

	public boolean refuseSolicitud(int id) {
		boolean res = false;
		Optional<Solicitud> solicitudO = solicitudRepository.findById(id);
		if (solicitudO.isPresent()) {
			Cliente cliente = JWTUtils.userLogin();
			if (cliente.getSolicitudes().contains(solicitudO.get())) {
				solicitudO.get().setEstado("RECHAZADO");
				res = true;
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
