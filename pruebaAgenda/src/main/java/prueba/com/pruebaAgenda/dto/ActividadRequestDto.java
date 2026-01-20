package prueba.com.pruebaAgenda.dto;

public class ActividadRequestDto {

	private String nombre;
	private String fecha;
	private String horaInicio;
	private String horaFin;
	private String comentario;
	private String codigoEstatus;
	private String username;
	
	public ActividadRequestDto() {
	}

	public ActividadRequestDto(String nombre, String fecha, String horaInicio, String horaFin, String comentario,
			String codigoEstatus, String username) {
		this.nombre = nombre;
		this.fecha = fecha;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.comentario = comentario;
		this.codigoEstatus = codigoEstatus;
		this.username = username;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getCodigoEstatus() {
		return codigoEstatus;
	}

	public void setCodigoEstatus(String codigoEstatus) {
		this.codigoEstatus = codigoEstatus;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
