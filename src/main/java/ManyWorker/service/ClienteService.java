package ManyWorker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ManyWorker.entity.Cliente;
import ManyWorker.entity.Roles;
import ManyWorker.repository.ClienteRepository;
import jakarta.transaction.Transactional;

@Service
public class ClienteService {

	@Autowired
    private ClienteRepository clienteRepository;
    
    @Autowired
	private PasswordEncoder passwordEncoder;
    
    @Transactional
    public Cliente saveCliente(Cliente cliente) {
    	cliente.setRol(Roles.CLIENTE);
    	cliente.setPassword(passwordEncoder.encode(cliente.getPassword()));
        return clienteRepository.save(cliente);
    }

    @Transactional
    public Cliente updateCliente(int id, Cliente cliente) {
        Optional<Cliente> cliente0 = clienteRepository.findById(id);
        if (cliente0.isPresent()) {
        	cliente0.get().setNombre(cliente.getNombre());
        	cliente0.get().setPrimerApellido(cliente.getPrimerApellido());
        	cliente0.get().setSegundoApellido(cliente.getSegundoApellido());
        	cliente0.get().setFoto(cliente.getFoto());
        	cliente0.get().setEmail(cliente.getEmail());
        	cliente0.get().setTelefono(cliente.getTelefono());
        	cliente0.get().setDireccion(cliente.getDireccion());
            return clienteRepository.save(cliente0.get());
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
    public boolean deleteCliente(int id) {
        if (clienteRepository.existsById(id)) {
        	clienteRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
