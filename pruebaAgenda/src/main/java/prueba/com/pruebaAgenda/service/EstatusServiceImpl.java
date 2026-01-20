package prueba.com.pruebaAgenda.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import prueba.com.pruebaAgenda.dto.EstatusResponseDto;
import prueba.com.pruebaAgenda.entity.EstatusEntity;
import prueba.com.pruebaAgenda.repository.EstatusRepository;

@Service
public class EstatusServiceImpl implements IEstatusService {

	private final EstatusRepository estatusRepository;
	
	public EstatusServiceImpl(EstatusRepository estatusRepository){
		this.estatusRepository=estatusRepository;
	}
	
	@Override
	public List<EstatusResponseDto> findAll() {
		List<EstatusEntity> estatus=this.estatusRepository.findAll();
		return estatus.stream().map(e->toDto(e)).collect(Collectors.toList());
	}

	private EstatusResponseDto toDto(EstatusEntity entity) {
		EstatusResponseDto dto = new EstatusResponseDto();
		dto.setCode_estatus(entity.getCode_estatus());
		dto.setDescripcion(entity.getDescripcion());
		return dto;
	}
	@Override
	public EstatusEntity save(EstatusEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EstatusEntity update(EstatusEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
