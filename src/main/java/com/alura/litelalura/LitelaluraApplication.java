package com.alura.litelalura;

import com.alura.litelalura.principal.Principal;
import com.alura.litelalura.repository.LibroRepository;
import com.alura.litelalura.service.AutorService;
import com.alura.litelalura.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LitelaluraApplication implements CommandLineRunner {

	@Autowired
	private LibroService libroService;

	@Autowired
	private AutorService autorService;

	public static void main(String[] args) {
		SpringApplication.run(LitelaluraApplication.class, args);
	}

	public void run(String... args) throws Exception {

		Principal principal = new Principal(libroService,autorService );
		principal.muestraMenu();

	}
}
