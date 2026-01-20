package prueba.com.pruebaAgenda.service;

import java.util.List;

import prueba.com.pruebaAgenda.dto.PersonaUsuarioResponseDto;
import prueba.com.pruebaAgenda.dto.PersonaUsuarioRequest;

public interface IPersonaService {

	List<PersonaUsuarioResponseDto> findAllPersonasWithUsuarios();
	PersonaUsuarioResponseDto findByCorreo(String correo);
	void delete(Integer id);
	PersonaUsuarioResponseDto update(PersonaUsuarioRequest req,Integer id);
	PersonaUsuarioResponseDto save(PersonaUsuarioRequest req);
}
