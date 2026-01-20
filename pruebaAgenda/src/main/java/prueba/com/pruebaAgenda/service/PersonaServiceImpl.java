package prueba.com.pruebaAgenda.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import prueba.com.pruebaAgenda.dto.PersonaUsuarioResponseDto;
import prueba.com.pruebaAgenda.dto.NotificationRequestDto;
import prueba.com.pruebaAgenda.dto.PersonaUsuarioRequest;
import prueba.com.pruebaAgenda.entity.PersonaEntity;
import prueba.com.pruebaAgenda.entity.UsuarioEntity;
import prueba.com.pruebaAgenda.exception.CorreoDuplicadoException;
import prueba.com.pruebaAgenda.exception.NotFoundPersonaException;
import prueba.com.pruebaAgenda.mapper.PersonaUsuarioMapper;
import prueba.com.pruebaAgenda.repository.PersonaRepository;
import prueba.com.pruebaAgenda.repository.UsuarioRepository;

@Service
@Slf4j
public class PersonaServiceImpl implements IPersonaService{

	private final PersonaRepository personaRepository;
	private final UsuarioRepository usuarioRepository;
	private final INotificacion notificacion;
	private final PasswordEncoder passwordEncoder;

	public PersonaServiceImpl(PersonaRepository personaRepository, UsuarioRepository usuarioRepository,
			INotificacion notificacion, PasswordEncoder passwordEncoder) {
		this.personaRepository = personaRepository;
		this.usuarioRepository = usuarioRepository;
		this.notificacion = notificacion;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public List<PersonaUsuarioResponseDto> findAllPersonasWithUsuarios() {
		List<PersonaEntity> persona=this.personaRepository.findAllPersonasWithUsuarios();
		return persona.stream().map(p->PersonaUsuarioMapper.toDto(p)).collect(Collectors.toList());
	}

	@Override
	public PersonaUsuarioResponseDto findByCorreo(String correo) {
		Optional<PersonaEntity> persona=this.personaRepository.findPersonaPorCorreo(correo);
		return PersonaUsuarioMapper.toDto(persona.get());
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Transactional
	@Override
	public PersonaUsuarioResponseDto update(PersonaUsuarioRequest req,Integer id) {
		
		Optional<PersonaEntity> persona=this.personaRepository.findPersonaPorIdPersona(id);
		if(!persona.isPresent()) {
			throw new NotFoundPersonaException("No existe la persona que se decea actualizar");
		}
		PersonaEntity personaUpdate=persona.get();
		UsuarioEntity usuario=persona.get().getUsuario();
		personaUpdate.setNombre(req.getNombre());
		personaUpdate.setApelldo(req.getApellido());
		usuario.setCorreo(req.getEmail());
		usuario.setPersona(personaUpdate);
		this.usuarioRepository.save(usuario);
		return PersonaUsuarioMapper.toDto(usuario);
	}
	
	private void sendNotificacion(String correo) {
		NotificationRequestDto notificacion= new NotificationRequestDto(correo,"Creacion de persona","Se creo correctamente la persona");
		this.notificacion.enviar(notificacion);
	}

	@Transactional
	@Override
	public PersonaUsuarioResponseDto save(PersonaUsuarioRequest req) {
			Optional<PersonaEntity> existeCorreo=this.personaRepository.findPersonaPorCorreo(req.getEmail());
			if(existeCorreo.isPresent()) {
				log.info("YA EXISTE EL CORREO");
				throw new CorreoDuplicadoException("El correo ya existe",null);
			}
			PersonaEntity persona=PersonaUsuarioMapper.toEntityPersona(req);
			PersonaEntity personaCreado=this.personaRepository.save(persona);
			UsuarioEntity usuario=PersonaUsuarioMapper.toEntityUsuario(req);
			usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
			usuario.setPersona(personaCreado);
			this.usuarioRepository.save(usuario);
			this.sendNotificacion(req.getEmail());
			return PersonaUsuarioMapper.toDto(usuario);
	}

}
