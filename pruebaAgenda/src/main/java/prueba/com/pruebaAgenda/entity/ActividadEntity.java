package prueba.com.pruebaAgenda.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="actividades")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ActividadEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_actividad")
	private Integer id;
	@Column(name="nombre")
	private String nombre;
	@Column(name="fecha")
	private LocalDate fecha;
	@Column(name="hora_inicio")
	private LocalTime horaInicio;
	@Column(name="hora_fin")
	private LocalTime horaFin;
	@Column(name="comentario")
	private String comentario;
	@OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
	@JoinColumn(name="cod_estatus")
	private EstatusEntity estatus;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_persona")
	private PersonaEntity persona;
}
