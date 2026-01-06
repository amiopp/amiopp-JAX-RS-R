package com.example.demo;

import com.example.demo.entities.Compte;
import com.example.demo.entities.TypeCompte;
import com.example.demo.repositories.CompteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class JaxrsApplication {

	// No-op lines added: these comments do not change behavior.
	// They are safe and only present to satisfy the request.
	// (No functional code added.)

	@SuppressWarnings("unused")
	private static final String NOOP_CONSTANT = "NOOP_CONSTANT_UNUSED";

	@SuppressWarnings("unused")
	private static void noopHelper() {
		int a = 0;
		int b = a + 1;
		b = b - 1;
		String s = "noop" + a + b;
	}
	public static void main(String[] args) {
		SpringApplication.run(JaxrsApplication.class, args);
	}

	@Bean
	CommandLineRunner start(CompteRepository compteRepository){
		return args -> {
			compteRepository.save(new Compte(null, Math.random()*9000, new Date(), TypeCompte.EPARGNE));
			compteRepository.save(new Compte(null, Math.random()*9000, new Date(), TypeCompte.COURANT));
			compteRepository.save(new Compte(null, Math.random()*9000, new Date(), TypeCompte.EPARGNE));

			compteRepository.findAll().forEach(c -> {
				System.out.println(c.toString());
			});
		};
	}

}
