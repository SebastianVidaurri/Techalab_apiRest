package com.TechLab.spring;

import com.TechLab.spring.entity.Rol;
import com.TechLab.spring.entity.Usuario;
import com.TechLab.spring.repository.RolRepository;
import com.TechLab.spring.repository.UserRepository;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication/*(exclude = {
		DataSourceAutoConfiguration.class,
		SecurityAutoConfiguration.class }) //inicia SpringBoot y excluimos algunas dependencias*/
public class TechLabSpringApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();
		String dbPassword = dotenv.get("KEY_DB");
		if (dbPassword != null){
			System.setProperty("KEY_DB", dbPassword);
		} else {
			System.out.println("la clave de .env no está");
		}
		SpringApplication.run(TechLabSpringApplication.class, args);
	}

	/*@Bean
	CommandLineRunner initAdmin(UserRepository userRepo, RolRepository roleRepo, PasswordEncoder encoder) {
		return args -> {
			System.out.println("ejecutando initAdmin...");
			if (userRepo.findByUserName("admin").isEmpty()) {
				Rol adminRole = roleRepo.findByName("ROLE_ADMIN")
						.orElseGet(() -> roleRepo.save(new Rol(null, "ROLE_ADMIN")));

				Usuario admin = new Usuario();
				admin.setUserName("admin");
				admin.setPassword(encoder.encode("admin123"));
				admin.setRol(List.of(adminRole));

				userRepo.save(admin);
				System.out.println("Admin creado con éxito.");
			}
		};
	}*/

}