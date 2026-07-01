package eCommerce.errores;

public class PedidoVacioException extends IllegalStateException {
	private static final long serialVersionUID = 1L;

	public PedidoVacioException(String mensaje) {
		super(mensaje);
	}
}
