package eCommerce.errores;

public class PagoRechazadoException extends IllegalStateException{
	private static final long serialVersionUID = 1L;

	public PagoRechazadoException(String mensaje) {
		super(mensaje);
	}
}
