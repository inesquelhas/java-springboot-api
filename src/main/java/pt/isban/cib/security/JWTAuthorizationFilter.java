package pt.isban.cib.security;

import com.auth0.jwt.exceptions.TokenExpiredException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private static final Logger LOG = LoggerFactory.getLogger(JWTAuthorizationFilter.class);

    private JWTUtil jwtUtil;

    private HTTPUtil httpUtil;

    private ClienteDetailsService clienteDetailsService;

    public JWTAuthorizationFilter(AuthenticationManager authManager, JWTUtil jwtUtil, HTTPUtil httpUtil, ClienteDetailsService clienteDetailsService){
        super(authManager);
        this.jwtUtil = jwtUtil;
        this.httpUtil = httpUtil;
        this.clienteDetailsService = clienteDetailsService;
    }

    public void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
        throws IOException, ServletException {

        if(httpUtil.hasRequestToken()){
            UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
            if (authentication != null){
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        chain.doFilter(req,res);
    }


    private UsernamePasswordAuthenticationToken getAuthentication (HttpServletRequest request)
        throws TokenExpiredException {

        String token = httpUtil.getRequestToken();

        if (token!= null){
            try {
                String login = jwtUtil.getTokenLogin(token);
                MyUserDetails user = (MyUserDetails) clienteDetailsService.loadUserByUsername(login);
                if (user != null){
                    return new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
                }
            } catch (TokenExpiredException e) {
                LOG.error("Token Expired",e);
                request.setAttribute("expired",e.getMessage());
            } catch (UsernameNotFoundException e){
                LOG.error("Cliente not found",e);
            }
            return null;
        }
        return null;
    }

}
