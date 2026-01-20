package prueba.com.pruebaAgenda.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="persona")
@Table(name="persona")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonaEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_persona")
	private Integer id;
	@Column(name="nombre")
	private String nombre;
	@Column(name="apellido")
	private String apelldo;
	@OneToMany(mappedBy = "persona",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
	List<ActividadEntity> actividades;
	@OneToOne(mappedBy = "persona", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private UsuarioEntity usuario;
}
