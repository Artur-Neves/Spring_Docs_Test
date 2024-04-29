package med.voll.api.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import med.voll.api.domain.User.User;

@Service
public class TokenService {
	@Value("${api.security.passwordSecret}")
	private  String passwordSecret;
	
	
	
	public String getToken(User user) {
		 Algorithm ALGORITHM =  Algorithm.HMAC256(passwordSecret);
		System.out.println(ALGORITHM);
		try {
		    Algorithm algorithm =ALGORITHM;
		    return JWT.create()
		        .withIssuer("Api do Artur")
		        .withSubject(user.getUsername())
		        .withExpiresAt(expiresData())
		        .sign(algorithm);
		} catch (JWTCreationException exception){
		    throw new RuntimeException("Erro ao gerar o token");
		}
	}

	private Instant expiresData() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}
	
	public String getSubject(String tokenJWT) {
		 Algorithm ALGORITHM =  Algorithm.HMAC256(passwordSecret);
		try {
		    Algorithm algorithm = ALGORITHM;
		   return  JWT.require(algorithm)
		        .withIssuer("Api do Artur")
		        .build()
		        .verify(tokenJWT)
		        .getSubject();
		        
		    
		} catch (JWTVerificationException exception){
		    throw new RuntimeException("Token inválido ou expirado");
		}
	}
}
