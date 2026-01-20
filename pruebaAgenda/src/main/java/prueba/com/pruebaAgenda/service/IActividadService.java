package prueba.com.pruebaAgenda.service;

import java.util.List;

import prueba.com.pruebaAgenda.dto.ActividadRequestDto;
import prueba.com.pruebaAgenda.dto.ActividadResponseDto;

public interface IActividadService {

	ActividadResponseDto save(ActividadRequestDto req);
	ActividadResponseDto update(ActividadRequestDto req,Integer id);
	void delete(Integer id);
	List<ActividadResponseDto> findAll();
}
