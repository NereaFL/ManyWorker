package ManyWorker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ManyWorker.repository.ClienteRepository;

@Service
public class ClienteService {

	 @Autowired
	 private ClienteRepository clienteRepository;
}
