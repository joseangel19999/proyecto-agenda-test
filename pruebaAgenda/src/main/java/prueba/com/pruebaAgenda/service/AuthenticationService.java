package prueba.com.pruebaAgenda.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import prueba.com.pruebaAgenda.dto.AuthenticationResponse;
import prueba.com.pruebaAgenda.dto.LoginRequest;
import prueba.com.pruebaAgenda.dto.PersonaUsuarioResponseDto;
import prueba.com.pruebaAgenda.security.JwtSecurityService;

@Component
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtSecurityService jwtSecurityService;
    private final IPersonaService personaService;
    
	public AuthenticationService(AuthenticationManager authenticationManager, JwtSecurityService jwtSecurityService,
			IPersonaService personaService) {
		this.authenticationManager = authenticationManager;
		this.jwtSecurityService = jwtSecurityService;
		this.personaService = personaService;
	}

	public AuthenticationResponse authenticate(LoginRequest req) {
	      UsernamePasswordAuthenticationToken authToken = 
	              new UsernamePasswordAuthenticationToken(
	                  req.getUsername(),
	                  req.getPassword()
	              );
	        Authentication authentication = authenticationManager.authenticate(authToken);
	        SecurityContextHolder.getContext().setAuthentication(authentication);
	        String accessToken = jwtSecurityService.generateToken(authentication);
	        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
	        PersonaUsuarioResponseDto persona=this.personaService.findByCorreo(req.getUsername());
	        return new AuthenticationResponse(accessToken,userDetails.getUsername(),persona.getNombre().concat(" ").concat(persona.getApellido()));
	}
    
    
}
