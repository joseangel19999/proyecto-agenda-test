package prueba.com.pruebaAgenda.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import prueba.com.pruebaAgenda.entity.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity,Integer>{

	List<UsuarioEntity> findAll();
	Optional<UsuarioEntity> findByCorreo(String correo);
}
