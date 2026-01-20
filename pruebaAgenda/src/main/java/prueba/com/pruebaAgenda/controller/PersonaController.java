package prueba.com.pruebaAgenda.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import prueba.com.pruebaAgenda.dto.PersonaUsuarioRequest;
import prueba.com.pruebaAgenda.dto.PersonaUsuarioResponseDto;
import prueba.com.pruebaAgenda.service.IPersonaService;

@RestController
@RequestMapping("/persona")
public class PersonaController {

	private final IPersonaService personaService;

	public PersonaController(IPersonaService personaService) {
		this.personaService = personaService;
	}
	
	@PostMapping("/create")
	public ResponseEntity<PersonaUsuarioResponseDto> save(@RequestBody PersonaUsuarioRequest  req){
		PersonaUsuarioResponseDto response=this.personaService.save(req);
		return new ResponseEntity<PersonaUsuarioResponseDto>(response,HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<PersonaUsuarioResponseDto>> findAllPersonWithUsuario(){
		return new ResponseEntity<List<PersonaUsuarioResponseDto>>(this.personaService.findAllPersonasWithUsuarios(),HttpStatus.OK);
	}
	
	@GetMapping("/{correo}")
	public ResponseEntity<PersonaUsuarioResponseDto> findPersona(@PathVariable String correo){
		return new ResponseEntity<PersonaUsuarioResponseDto>(this.personaService.findByCorreo(correo),HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PersonaUsuarioResponseDto> updatePersonaWithUsuario(@PathVariable Long id,@RequestBody PersonaUsuarioRequest req){
		return new ResponseEntity<PersonaUsuarioResponseDto>(this.personaService.update(req,1),HttpStatus.OK);
	}
	
}
