package prueba.com.pruebaAgenda.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;
import prueba.com.pruebaAgenda.dto.ApiError;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CorreoDuplicadoException.class)
	public ResponseEntity<ApiError> handleCorreoDuplicado(CorreoDuplicadoException ex,
			HttpServletRequest request){
		ApiError error = new ApiError(HttpStatus.CONFLICT.value(), "", ex.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
	}
	
	@ExceptionHandler(NotFoundPersonaException.class)
	public ResponseEntity<ApiError> handleNotFoundPersona(NotFoundPersonaException ex,
			HttpServletRequest request){
		ApiError error = new ApiError(HttpStatus.NOT_FOUND.value(), "", ex.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ResponseEntity<ApiError>  handleBadCredentialsException(BadCredentialsException ex,
    		HttpServletRequest request) {
    	ApiError error = new ApiError(HttpStatus.UNAUTHORIZED.value(), "", "Usuario o contraseña incorrectos",
				request.getRequestURI());
    	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ResponseEntity<ApiError>  handleUsernameNotFoundException(UsernameNotFoundException ex,
    		HttpServletRequest request) {
      	ApiError error = new ApiError(HttpStatus.UNAUTHORIZED.value(), "", "Usuario o contraseña incorrectos",
				request.getRequestURI());
    	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }
    
    @ExceptionHandler(EstatusNoEncontradoException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ResponseEntity<ApiError>  EstatusNotFoundException(EstatusNoEncontradoException ex,
    		HttpServletRequest request) {
      	ApiError error = new ApiError(HttpStatus.CONFLICT.value(), "", ex.getMessage(),
				request.getRequestURI());
    	return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }
}
