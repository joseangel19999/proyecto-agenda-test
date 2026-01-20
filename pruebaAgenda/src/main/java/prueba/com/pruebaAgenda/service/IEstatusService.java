package prueba.com.pruebaAgenda.service;

import java.util.List;

import prueba.com.pruebaAgenda.dto.EstatusResponseDto;
import prueba.com.pruebaAgenda.entity.EstatusEntity;

public interface IEstatusService {

	List<EstatusResponseDto> findAll();
	EstatusEntity save(EstatusEntity entity);
	EstatusEntity update(EstatusEntity entity);
}
