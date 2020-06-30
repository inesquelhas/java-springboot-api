package pt.isban.cib.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JWTUtil {

    @Value("${pt.isban.cib.secretKey}")
    private String secret;

    @Value("${pt.isban.cib.expirationKey}")
    private Long expiration;

    public JWTUtil(){

    }

    public String generateToken(MyUserDetails userDetails){

        return JWT.create()
                .withSubject(userDetails.getUsername())
                .withClaim("id", userDetails.getId())
                .withArrayClaim("roles",userDetails.getAuthorities()
                        .stream()
                        .map(role -> role.getAuthority())
                        .collect(Collectors.toList())
                        .stream()
                        .toArray(String[]::new))
                .withExpiresAt(Date.from(
                                        LocalDateTime.now()
                                        .plusSeconds(expiration)
                                        .atZone(ZoneId.systemDefault())
                                        .toInstant()))
                .sign(Algorithm.HMAC512(secret));
    }

    public String getTokenLogin(String token) {
        return JWT.require(Algorithm.HMAC512(secret))
                .build()
                .verify(token)
                .getSubject();
    }

    public Integer getTokenId(String token) {
        return JWT.require(Algorithm.HMAC512(secret))
                .build()
                .verify(token)
                .getClaim("id")
                .asInt();
    }
}
