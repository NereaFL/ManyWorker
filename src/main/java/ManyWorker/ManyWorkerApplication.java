package ManyWorker;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ManyWorker.service.AdministradorService;

@SpringBootApplication
public class ManyWorkerApplication implements CommandLineRunner{
	
	private AdministradorService adminService;

	public static void main(String[] args) {
		SpringApplication.run(ManyWorkerApplication.class, args);
	}

	 @Override
	    public void run(String... args) throws Exception {
	        // Invocar el método para crear el administrador por defecto si no existe
	        adminService.adminPorDefecto();
	    }

}

