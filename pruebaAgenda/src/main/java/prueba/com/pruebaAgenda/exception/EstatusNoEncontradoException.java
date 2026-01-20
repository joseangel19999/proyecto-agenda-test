package prueba.com.pruebaAgenda.exception;

public class EstatusNoEncontradoException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public EstatusNoEncontradoException(String mensaje) {
		super(mensaje);
	}
}
