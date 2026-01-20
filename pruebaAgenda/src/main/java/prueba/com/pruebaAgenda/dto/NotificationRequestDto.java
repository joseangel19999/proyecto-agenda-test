package prueba.com.pruebaAgenda.dto;

public class NotificationRequestDto {

	private String destinatario;
	private String asunto;
	private String cuerpo;
	
	public NotificationRequestDto(String destinatario, String asunto, String cuerpo) {
		this.destinatario = destinatario;
		this.asunto = asunto;
		this.cuerpo = cuerpo;
	}

	public String getDestinatario() {
		return destinatario;
	}

	public String getAsunto() {
		return asunto;
	}

	public String getCuerpo() {
		return cuerpo;
	}
	
    
}
