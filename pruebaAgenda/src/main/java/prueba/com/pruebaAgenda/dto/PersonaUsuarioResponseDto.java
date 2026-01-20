package prueba.com.pruebaAgenda.dto;

public class PersonaUsuarioResponseDto {
	
	private Integer idPersona;
	private String nombre;
	private String apellido;
	private String corrreo;
	
	public PersonaUsuarioResponseDto(Integer idPersona, String nombre, String apellido, String corrreo) {
		this.idPersona = idPersona;
		this.nombre = nombre;
		this.apellido = apellido;
		this.corrreo = corrreo;
	}

	public PersonaUsuarioResponseDto() {
	}

	public Integer getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(Integer idPersona) {
		this.idPersona = idPersona;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCorrreo() {
		return corrreo;
	}

	public void setCorrreo(String corrreo) {
		this.corrreo = corrreo;
	}
	
}
