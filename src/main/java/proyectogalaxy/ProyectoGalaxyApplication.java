package proyectogalaxy;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProyectoGalaxyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProyectoGalaxyApplication.class, args);
    }


    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Reservas de barberia API V1")
                        .version("2.0.0")
                        .summary("API para agendar reservas de una barberia.")
                        .description("Esta api se implemento para gestionar las reservas de la barberia hombres del saber")
                        .termsOfService("https://barbershop.com/terminosycondiciones"));
    }

}
