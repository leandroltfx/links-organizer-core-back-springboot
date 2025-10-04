package br.com.links_organizer_core_back_springboot;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Links Organizer", description = "API core do Links Organizer", version = "1"))
public class LinksOrganizerCoreBackSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(LinksOrganizerCoreBackSpringbootApplication.class, args);
	}

}
