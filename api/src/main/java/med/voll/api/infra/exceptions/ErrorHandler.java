package med.voll.api.infra.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ErrorHandler {
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity ErrorHandler404(EntityNotFoundException e) {
		return ResponseEntity.ofNullable(e.getMessage());
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity ErrorHandler400(MethodArgumentNotValidException ex) {
		var erros = ex.getFieldErrors();
		return ResponseEntity.badRequest().body(erros.stream().map(ExceptionDates::new).toList());
	}
	@ExceptionHandler(AppointmentValidationException.class)
	public ResponseEntity ErrorHandler400(AppointmentValidationException ex) {
		return ResponseEntity.badRequest().body( new ExceptionDates("Validação" , ex.getMessage()));
	}
	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity ErrorCredenciaisLogin(AuthenticationException ex) {
		return ResponseEntity.badRequest().body( new ExceptionDates("Error" , "Login ou senha inválidos ou incorretos"));
	}
	private record ExceptionDates(String field, String mensage) {
		public ExceptionDates(FieldError erro) {
			this(erro.getField(), erro.getDefaultMessage());
		}
	}
}
