package prueba.com.pruebaAgenda.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import prueba.com.pruebaAgenda.dto.EstatusResponseDto;
import prueba.com.pruebaAgenda.entity.EstatusEntity;
import prueba.com.pruebaAgenda.service.IEstatusService;

@RestController
@RequestMapping("/estatus")
public class EstatusController {


	private final IEstatusService estatusService;
	
	public EstatusController(IEstatusService estatusService) {
		this.estatusService=estatusService;
	}
	
	@GetMapping
	public ResponseEntity<List<EstatusResponseDto>> findAll(){
		List<EstatusResponseDto> response=this.estatusService.findAll();
		return new ResponseEntity<List<EstatusResponseDto>>(response,HttpStatus.OK);
	}
	
}
