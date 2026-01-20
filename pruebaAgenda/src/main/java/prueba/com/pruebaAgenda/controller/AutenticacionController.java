package prueba.com.pruebaAgenda.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import prueba.com.pruebaAgenda.dto.AuthenticationResponse;
import prueba.com.pruebaAgenda.dto.LoginRequest;
import prueba.com.pruebaAgenda.service.AuthenticationService;

@RestController
@RequestMapping("/auth")
public class AutenticacionController {

	private final AuthenticationService auteAuthenticationService;
	
	public AutenticacionController(AuthenticationService auteAuthenticationService) {
		this.auteAuthenticationService = auteAuthenticationService;
	}

	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponse> autenticar(@RequestBody LoginRequest request){
		return new ResponseEntity<AuthenticationResponse>(this.auteAuthenticationService.authenticate(request),HttpStatus.OK);
	}
}
