package eCommerce.errores;

public class ItemNoEncontradoException extends IllegalStateException{
	private static final long serialVersionUID = 1L;

	public ItemNoEncontradoException(String mensaje) {
		super(mensaje);
	}
}
