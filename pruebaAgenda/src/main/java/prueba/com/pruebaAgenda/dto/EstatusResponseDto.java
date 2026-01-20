package prueba.com.pruebaAgenda.dto;

public class EstatusResponseDto {

	private String code_estatus;
	private String descripcion;
	public EstatusResponseDto(String code_estatus, String descripcion) {
		this.code_estatus = code_estatus;
		this.descripcion = descripcion;
	}
	public EstatusResponseDto() {
	}
	public String getCode_estatus() {
		return code_estatus;
	}
	public void setCode_estatus(String code_estatus) {
		this.code_estatus = code_estatus;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	
}
