package prueba.com.pruebaAgenda.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import prueba.com.pruebaAgenda.dto.PersonaUsuarioResponseDto;

@Repository
public class PersonaUsuarioCustomRepository {


	private final JdbcTemplate jdbcTemplate;
	
	public PersonaUsuarioCustomRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	
	public Optional<PersonaUsuarioResponseDto> findAll() {
		String sql = """
				 SELECT p.NOMBRE ,p.APELLIDO ,u.EMAIL FROM PERSONA p
				INNER JOIN USUARIO u ON p.ID_PERSONA =u.ID_PERSONA;
				""";
		try {
			PersonaUsuarioResponseDto result = jdbcTemplate.queryForObject(sql, new Object[] {},new RowMapper<PersonaUsuarioResponseDto>() {
						@Override
						public PersonaUsuarioResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
							PersonaUsuarioResponseDto p = new PersonaUsuarioResponseDto();
							p.setIdPersona(null);
							p.setNombre(sql);
							p.setCorrreo(sql);
							p.setApellido(sql);
							return p;
						}
					});
			if (result != null) {
				return Optional.of(result);
			} else {
				return Optional.empty();
			}
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}
}
