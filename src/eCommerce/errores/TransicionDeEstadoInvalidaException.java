package eCommerce.errores;

public class TransicionDeEstadoInvalidaException extends IllegalStateException{
	private static final long serialVersionUID = 1L;

	public TransicionDeEstadoInvalidaException(String mensaje) {
		super(mensaje);
	}
}
