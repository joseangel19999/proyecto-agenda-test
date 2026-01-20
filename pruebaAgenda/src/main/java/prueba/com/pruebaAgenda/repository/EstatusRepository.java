package prueba.com.pruebaAgenda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import prueba.com.pruebaAgenda.entity.EstatusEntity;

public interface EstatusRepository  extends JpaRepository<EstatusEntity,String>{

	List<EstatusEntity> findAll();
}
