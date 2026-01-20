package prueba.com.pruebaAgenda.exception;

public class NotFoundPersonaException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public NotFoundPersonaException(String mensaje){
		super(mensaje);
	}
}
