package prueba.com.pruebaAgenda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import prueba.com.pruebaAgenda.entity.ActividadEntity;

public interface ActividadRepository  extends JpaRepository<ActividadEntity,Integer>{

	List<ActividadEntity> findAll();

}
