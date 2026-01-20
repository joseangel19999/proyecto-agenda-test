package prueba.com.pruebaAgenda.mapper;

import java.time.LocalTime;

import prueba.com.pruebaAgenda.dto.ActividadRequestDto;
import prueba.com.pruebaAgenda.dto.ActividadResponseDto;
import prueba.com.pruebaAgenda.entity.ActividadEntity;
import prueba.com.pruebaAgenda.utils.DateTimeParser;

public class ActividadMapper {

	public static ActividadEntity toEntity(ActividadRequestDto req) {
		ActividadEntity entity = new ActividadEntity();
		entity.setNombre(req.getNombre());
		entity.setComentario(req.getComentario());
		entity.setFecha(DateTimeParser.stringToLocalDate(req.getFecha()));
		entity.setHoraInicio(LocalTime.parse(req.getHoraInicio()));
		entity.setHoraFin(LocalTime.parse(req.getHoraFin()));
		return entity;
	}

	public static ActividadResponseDto toDto(ActividadEntity entity) {
		ActividadResponseDto dto = new ActividadResponseDto();
		dto.setComentario(entity.getComentario());
		dto.setEstatus(entity.getEstatus().getDescripcion());
		dto.setFecha(DateTimeParser.localDateTimeToString(entity.getFecha()));
		dto.setHoraInicio(entity.getHoraInicio().toString());
		dto.setHoraFin(entity.getHoraFin().toString());
		dto.setNombre(entity.getNombre());
		return dto;
	}

}
