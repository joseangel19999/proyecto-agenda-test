package prueba.com.pruebaAgenda.mapper;

import prueba.com.pruebaAgenda.dto.PersonaUsuarioResponseDto;
import prueba.com.pruebaAgenda.dto.PersonaUsuarioRequest;
import prueba.com.pruebaAgenda.entity.PersonaEntity;
import prueba.com.pruebaAgenda.entity.UsuarioEntity;

public class PersonaUsuarioMapper {

	public static PersonaEntity toEntityPersona(PersonaUsuarioRequest req){
		PersonaEntity entity=new PersonaEntity();
		entity.setNombre(req.getNombre());
		entity.setApelldo(req.getApellido());
		return entity;
	}
	
	public static UsuarioEntity toEntityUsuario(PersonaUsuarioRequest req) {
		UsuarioEntity entity= new UsuarioEntity();
		entity.setCorreo(req.getEmail());
		entity.setPassword(req.getPassword());
		return entity;
	}
	
	public static PersonaUsuarioResponseDto toDto(UsuarioEntity usuario) {
		PersonaUsuarioResponseDto dto = new PersonaUsuarioResponseDto();
		dto.setIdPersona(usuario.getPersona().getId());
		dto.setNombre(usuario.getPersona().getNombre());
		dto.setCorrreo(usuario.getCorreo());
		dto.setApellido(usuario.getPersona().getApelldo());
		return dto;
	}
	
	public static PersonaUsuarioResponseDto toDto(PersonaEntity persona) {
		PersonaUsuarioResponseDto dto = new PersonaUsuarioResponseDto();
		dto.setIdPersona(persona.getId());
		dto.setNombre(persona.getNombre());
		dto.setCorrreo(persona.getUsuario().getCorreo());
		dto.setApellido(persona.getApelldo());
		return dto;
	}
	
}
