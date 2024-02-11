package luis122448.platformtraining;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@AutoConfiguration
@EnableJpaAuditing
@EnableJpaRepositories
@OpenAPIDefinition(info = @Info(title = "PLATFORM-TRAINING API",
		version = "1.0.0",
		description = "Documentation for API"),
		tags = {
				@Tag(name = "Test", description = "For Testing Deploying Server"),
				@Tag(name = "Auth", description = "Authentication"),
				@Tag(name = "App", description = "Application")
		})
public class PlatformTrainingApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlatformTrainingApplication.class, args);
	}

}
