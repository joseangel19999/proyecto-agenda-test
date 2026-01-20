package prueba.com.pruebaAgenda.security;

import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import prueba.com.pruebaAgenda.entity.UsuarioEntity;
import prueba.com.pruebaAgenda.repository.UsuarioRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService{

	private final UsuarioRepository usuarioRepository;
	private final String RoleDefault="USUARIO";

	public CustomUserDetailsService(UsuarioRepository usuarioRepository) {
		super();
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UsuarioEntity usuario = usuarioRepository.findByCorreo(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
			return org.springframework.security.core.userdetails.User.builder()
            .username(usuario.getCorreo())
            .password(usuario.getPassword())
            .roles(RoleDefault)
            .build();
	}
}
