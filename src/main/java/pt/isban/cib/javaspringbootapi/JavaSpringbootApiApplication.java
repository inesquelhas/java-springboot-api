package pt.isban.cib.javaspringbootapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication (scanBasePackages={"pt.isban.cib"})
@EnableJpaRepositories(basePackages={ "pt.isban.cib"})
@EntityScan(basePackages={ "pt.isban.cib"})
public class JavaSpringbootApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaSpringbootApiApplication.class, args);
	}

}
