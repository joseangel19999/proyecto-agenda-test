package prueba.com.pruebaAgenda.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import prueba.com.pruebaAgenda.entity.PersonaEntity;

public interface PersonaRepository extends JpaRepository<PersonaEntity,Integer>{
	
	@Query("SELECT p FROM persona p LEFT JOIN FETCH p.usuario")
    List<PersonaEntity> findAllPersonasWithUsuarios();
	
	@Query("SELECT p FROM persona p LEFT JOIN FETCH p.usuario u WHERE u.correo=:correo")
    Optional<PersonaEntity> findPersonaPorCorreo(@Param("correo") String correo);
	
	@Query("SELECT p FROM persona p LEFT JOIN FETCH p.usuario u WHERE p.id=:id")
    Optional<PersonaEntity> findPersonaPorIdPersona(@Param("id") Integer id);
}
