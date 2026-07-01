package eCommerce.errores;

public class AtributoInvalidoException extends IllegalArgumentException {

	private static final long serialVersionUID = 1L;

	public AtributoInvalidoException(String mensaje) {
		super(mensaje);
	}
}