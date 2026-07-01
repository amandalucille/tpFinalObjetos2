package eCommerce.errores;

public class NoTieneFacturaException extends IllegalStateException{
	private static final long serialVersionUID = 1L;

	public NoTieneFacturaException() {
		super("No existe factura para este pedido");
	}
}
