package prueba.com.pruebaAgenda.exception;

public class CorreoDuplicadoException extends RuntimeException {

private static final long serialVersionUID = 1L;
	
    public CorreoDuplicadoException(String message, Throwable cause) {
        super(message, cause);
    }
}
