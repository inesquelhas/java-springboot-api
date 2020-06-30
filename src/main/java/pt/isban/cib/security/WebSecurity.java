package pt.isban.cib.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import sun.security.util.SecurityConstants;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private HTTPUtil httpUtil;

    @Autowired
    private ClienteDetailsService clienteDetailsService;

    public void configure(HttpSecurity http) throws Exception {

        JWTAuthenticationFilter jwtAuth = new JWTAuthenticationFilter(authenticationManager(),jwtUtil);
        jwtAuth.setFilterProcessesUrl("/api/v1/login");

        http.headers().frameOptions().disable().and() // Disable header X-Frame-Options, Sites can use this to avoid clickjacking attacks, by ensuring that their content is not embedded into other sites in an iframe content.
                .cors().and().csrf().disable()
                // .cors().and().csrf().disable() // Interesting, the attacker makes the client trigger the request he wants to
                // .cors().and().csrf().and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/v1/clientes").permitAll()
                .antMatchers("*","/h2-console/").permitAll() //For H2
                .antMatchers("*", "/favicon.ico").permitAll() //For H2
                .anyRequest().authenticated().and()
                .addFilter(jwtAuth)
                .addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtUtil, httpUtil, clienteDetailsService))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // this disables session creation on Spring Security
    }

    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(clienteDetailsService)
                .passwordEncoder(getPasswordEncoder());
    }

    @Bean //cria uma unica instancia e partilha para os componentes que precisarem
    public BCryptPasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
