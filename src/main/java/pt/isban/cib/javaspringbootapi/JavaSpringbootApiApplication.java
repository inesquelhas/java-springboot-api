package pt.isban.cib.javaspringbootapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import pt.isban.cib.enums.PrivilegioEnum;
import pt.isban.cib.security.JWTUtil;
import pt.isban.cib.security.MyUserDetails;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


@SpringBootApplication (scanBasePackages={"pt.isban.cib"})
@EnableJpaRepositories(basePackages={ "pt.isban.cib"})
@EntityScan(basePackages={ "pt.isban.cib"})
public class JavaSpringbootApiApplication {

	@Value("${pt.isban.cib.timeout:20}") //:20 20 por default, para a propriedade ser reconhecida em PROD
	public Integer timeout;

	@Autowired //anotaçao do spring (procura obj no sistema e instancia o objeto)
	private JWTUtil jwtUtil;

	public static void main(String[] args) {
		SpringApplication.run(JavaSpringbootApiApplication.class, args);
	}

	@PostConstruct
	public void init(){
		System.out.println("timeout:" + timeout);

		List<PrivilegioEnum> roles = new ArrayList<>();
		roles.add(PrivilegioEnum.CLIENT);
		MyUserDetails user = new MyUserDetails(1,"ines@gmail.com","1234",roles);
		final String token = jwtUtil.generateToken(user);

		System.out.println("token:" + token);

	}

}
