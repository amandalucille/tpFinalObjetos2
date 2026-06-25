package eCommerce.mediosDePago;


import java.util.List;

public abstract class MetodoDePago {
	
	private List<Comprobante> comprobantes;
	
	public final void procesarPago(Double montoPedido) {
		validarDatos();
		reservarFondos(montoPedido);
		ejecutarTransaccion(montoPedido);
		notificarResultado();
	}
	public abstract void validarDatos();
	
	public abstract void reservarFondos(Double monto);
	
	public abstract void ejecutarTransaccion(Double monto);
	
	public void notificarResultado() {
		System.out.println("Tu transaccion ha sido procesada");
	}
	
}
