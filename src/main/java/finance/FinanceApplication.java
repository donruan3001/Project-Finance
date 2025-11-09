package finance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;


@OpenAPIDefinition(
    servers = {
        @Server(url = "https://nonsatirical-overkind-bernarda.ngrok-free.dev", description = "Ngrok Public HTTPS"),
        @Server(url = "http://localhost:8080", description = "Localhost Dev")
    }
)
@SpringBootApplication
public class FinanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinanceApplication.class, args);
	}

}
