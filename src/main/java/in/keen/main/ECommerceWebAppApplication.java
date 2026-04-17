package in.keen.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "in.keen.Entity")
@EnableJpaRepositories(basePackages = "in.keen.Repository")
@ComponentScan(basePackages = "in.keen")
public class ECommerceWebAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECommerceWebAppApplication.class, args);
	}

}
