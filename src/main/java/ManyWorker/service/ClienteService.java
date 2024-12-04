package ManyWorker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ManyWorker.entity.Cliente;
import ManyWorker.entity.Patrocinador;
import ManyWorker.entity.Roles;
import ManyWorker.repository.ClienteRepository;
import ManyWorker.security.JWTUtils;
import jakarta.transaction.Transactional;

@Service
public class ClienteService {

	@Autowired
    private ClienteRepository clienteRepository;
    
    @Autowired
	private PasswordEncoder passwordEncoder;
    
    private JWTUtils JWTUtils;
    
    @Transactional
    public Cliente saveCliente(Cliente cliente) {
    	cliente.setRol(Roles.CLIENTE);
    	cliente.setPassword(passwordEncoder.encode(cliente.getPassword()));
        return clienteRepository.save(cliente);
    }

    @Transactional
    public Cliente updateCliente(Cliente cliente) {
    	Cliente cliente0 = JWTUtils.userLogin();
		if (cliente != null) {
        	cliente0.setNombre(cliente.getNombre());
        	cliente0.setPrimerApellido(cliente.getPrimerApellido());
        	cliente0.setSegundoApellido(cliente.getSegundoApellido());
        	cliente0.setFoto(cliente.getFoto());
        	cliente0.setEmail(cliente.getEmail());
        	cliente0.setTelefono(cliente.getTelefono());
        	cliente0.setDireccion(cliente.getDireccion());
            return clienteRepository.save(cliente0);
        }
        return null;
    }

    public List<Cliente> getAllCliente() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> getClienteById(int id) {
        return clienteRepository.findById(id);
    }

	public Optional<Cliente> findByUsername(String username) {
		return clienteRepository.findByUsername(username);
	}

    @Transactional
    public boolean deleteCliente() {
    	Cliente cliente = JWTUtils.userLogin();
		if (cliente != null) {
			clienteRepository.deleteById(cliente.getId());
            return true;
        }
        return false;
    }
}
