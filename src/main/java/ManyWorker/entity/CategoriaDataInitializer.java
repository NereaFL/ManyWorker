package ManyWorker.entity;

import ManyWorker.entity.Categoria;
import ManyWorker.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class CategoriaDataInitializer implements CommandLineRunner {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public void run(String... args) throws Exception {
        // Verificar si las categorías ya están presentes
        List<String> categoriasExistentes = categoriaRepository.findAll().stream()
            .map(Categoria::getNombreCategoria) // Asumimos que tienes un método getNombreCategoria en la entidad
            .toList();

        // Lista de categorías predeterminadas
        List<String> categoriasPredeterminadas = Arrays.asList(
            "Carpintería", "Reparación de techos", "Limpieza", "Puertas", "Cableado eléctrico",
            "Instalación de ventiladores", "Reparación de cercas", "Sistemas de seguridad para el hogar",
            "Instalación de aislamiento", "Reparaciones de lámparas", "Mudanzas", "Pintura",
            "Control de plagas", "Reparaciones de fontanería", "Techado", "Instalación de estanterías",
            "Paneles solares", "Insonorización", "Reparación de sistemas de riego", "Reparación de ventanas"
        );

        // Añadir las categorías por defecto solo si no existen
        for (String nombreCategoria : categoriasPredeterminadas) {
            if (!categoriasExistentes.contains(nombreCategoria)) {
                Categoria categoria = new Categoria();
                categoria.setNombreCategoria(nombreCategoria);
                categoriaRepository.save(categoria);
            }
        }
    }
}

