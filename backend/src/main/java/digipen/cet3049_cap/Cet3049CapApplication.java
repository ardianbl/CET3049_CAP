package digipen.cet3049_cap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

/**
 * Main Spring Boot application class for the CET3049 CAP project.
 * Boots the application and enables Spring Data web support for DTO pagination.
 */
@SpringBootApplication
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
public class Cet3049CapApplication {

    public static void main(String[] args) {
        SpringApplication.run(Cet3049CapApplication.class, args);
    }

}
