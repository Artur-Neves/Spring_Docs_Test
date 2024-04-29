package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import med.voll.api.domain.User.User;
import med.voll.api.domain.User.UserLoginDTO;
import med.voll.api.infra.security.TokenDTO;
import med.voll.api.infra.security.TokenService;

@RestController
@RequestMapping("/login")
public class UserController {
	//dispara o processo de autentificação
	@Autowired
	private AuthenticationManager manager;
	@Autowired
	private TokenService tokenService;
	@PostMapping
	public ResponseEntity login(@RequestBody @Valid UserLoginDTO userDTO) {
		var token = new UsernamePasswordAuthenticationToken(userDTO.login(), userDTO.password());
		// por baixo dos planos essa linha chama o método de autentificação que
		// esta na classe AutentificacaoUser
		Authentication authentication =	manager.authenticate(token);
		// fazendo a geração do token a partir da decodificação do tipo hmac 256
		var tokenJWT =tokenService.getToken((User)authentication.getPrincipal());
		return ResponseEntity.ok(new TokenDTO(tokenJWT));
	}
}
