package prueba.com.pruebaAgenda.service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import prueba.com.pruebaAgenda.dto.ActividadRequestDto;
import prueba.com.pruebaAgenda.dto.ActividadResponseDto;
import prueba.com.pruebaAgenda.entity.ActividadEntity;
import prueba.com.pruebaAgenda.entity.EstatusEntity;
import prueba.com.pruebaAgenda.entity.PersonaEntity;
import prueba.com.pruebaAgenda.exception.EntidadNoEncontradaException;
import prueba.com.pruebaAgenda.exception.EstatusNoEncontradoException;
import prueba.com.pruebaAgenda.exception.NotFoundPersonaException;
import prueba.com.pruebaAgenda.mapper.ActividadMapper;
import prueba.com.pruebaAgenda.repository.ActividadRepository;
import prueba.com.pruebaAgenda.repository.EstatusRepository;
import prueba.com.pruebaAgenda.repository.PersonaRepository;
import prueba.com.pruebaAgenda.security.CustomUserDetailsService;
import prueba.com.pruebaAgenda.utils.DateTimeParser;

@Service
@Slf4j
public class ActividadServiceImpl implements IActividadService {

    private final CustomUserDetailsService customUserDetailsService;

	private final ActividadRepository actividadRepository;
	private final EstatusRepository estatusRepository;
	private final PersonaRepository personaRepository;

	public ActividadServiceImpl(ActividadRepository actividadRepository, EstatusRepository estatusRepository,
			PersonaRepository personaRepository, CustomUserDetailsService customUserDetailsService) {
		super();
		this.actividadRepository = actividadRepository;
		this.estatusRepository = estatusRepository;
		this.personaRepository = personaRepository;
		this.customUserDetailsService = customUserDetailsService;
	}

	@Transactional
	@Override
	public ActividadResponseDto save(ActividadRequestDto req) {
		if (!this.estatusRepository.existsById(req.getCodigoEstatus())) {
			throw new EstatusNoEncontradoException("No existe el codigo de estatus");
		}
		Optional<PersonaEntity> persona = this.personaRepository.findPersonaPorCorreo(req.getUsername());
		if (!persona.isPresent()) {
			throw new NotFoundPersonaException("No existe la persona");
		}
		EstatusEntity estatus = estatusRepository.getReferenceById(req.getCodigoEstatus());
		ActividadEntity actividad = ActividadMapper.toEntity(req);
		actividad.setPersona(persona.get());
		actividad.setEstatus(estatus);
		ActividadEntity actividadCreado = this.actividadRepository.save(actividad);
		return ActividadMapper.toDto(actividadCreado);
	}

	@Override
	public ActividadResponseDto update(ActividadRequestDto req, Integer id) {
		if (!this.estatusRepository.existsById(req.getCodigoEstatus())) {
			throw new EstatusNoEncontradoException("No existe el codigo de estatus");
		}
		Optional<PersonaEntity> persona = this.personaRepository.findPersonaPorCorreo(req.getUsername());
		if (!persona.isPresent()) {
			throw new NotFoundPersonaException("No existe la persona");
		}
		Optional<ActividadEntity> actividadOpt=this.actividadRepository.findById(id);
		if(!actividadOpt.isPresent()) {
			throw new EntidadNoEncontradaException("No existe la actividad por el id: "+id);
		}
		EstatusEntity estatus = estatusRepository.getReferenceById(req.getCodigoEstatus());
		ActividadEntity actividad =actividadOpt.get();
		actividad.setFecha(DateTimeParser.stringToLocalDate(req.getFecha()));
		actividad.setHoraInicio(LocalTime.parse(req.getHoraInicio()));
		actividad.setHoraFin(LocalTime.parse(req.getHoraFin()));
		actividad.setPersona(persona.get());
		actividad.setEstatus(estatus);
		ActividadEntity actividadCreado = this.actividadRepository.save(actividad);
		return ActividadMapper.toDto(actividadCreado);
	}

	@Override
	public void delete(Integer id) {
		Optional<ActividadEntity> actividad= this.actividadRepository.findById(id);
		if(!actividad.isPresent()) {
			throw new EntidadNoEncontradaException("No existe la actividad por el id: "+id);
		}
		this.actividadRepository.delete(actividad.get());
	}

	@Override
	public List<ActividadResponseDto> findAll() {
		List<ActividadEntity> actividades=this.actividadRepository.findAll();
		return actividades.stream().map(act->ActividadMapper.toDto(act)).collect(Collectors.toList());
	}

}
