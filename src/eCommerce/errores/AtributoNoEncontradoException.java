package eCommerce.errores;

public class AtributoNoEncontradoException extends IllegalStateException{
	private static final long serialVersionUID = 1L;

	public AtributoNoEncontradoException(String mensaje) {
		super(mensaje);
	}

}
