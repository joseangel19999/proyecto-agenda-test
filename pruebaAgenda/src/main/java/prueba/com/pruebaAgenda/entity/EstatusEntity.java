package prueba.com.pruebaAgenda.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "ESTATUS_ACTIVIDAD")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EstatusEntity {

	@Id
	@Column(name="codigo_estatus")
	private String code_estatus;
	@Column(name="descripcion")
	private String descripcion;
	
}
