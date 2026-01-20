package prueba.com.pruebaAgenda.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import prueba.com.pruebaAgenda.dto.ActividadRequestDto;
import prueba.com.pruebaAgenda.dto.ActividadResponseDto;
import prueba.com.pruebaAgenda.service.IActividadService;

@RestController
@RequestMapping("/actividad")
public class ActividadController {

	private final IActividadService actividadService;

	public ActividadController(IActividadService actividadService) {
		this.actividadService = actividadService;
	}
	
	@PostMapping
	public ResponseEntity<ActividadResponseDto> save(@RequestBody ActividadRequestDto req){
		return new ResponseEntity<ActividadResponseDto>(this.actividadService.save(req),HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<ActividadResponseDto>> listarActividades(){
		return new ResponseEntity<List<ActividadResponseDto>>(this.actividadService.findAll(),HttpStatus.OK);
	}

}
