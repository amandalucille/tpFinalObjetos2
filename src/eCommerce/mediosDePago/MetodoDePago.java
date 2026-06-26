package eCommerce.mediosDePago;


import java.util.List;

public abstract class MetodoDePago {
	
	private List<Comprobante> comprobantes;
	
	public final void procesarPago(DatosDePago datos, Double montoPedido) {
		validarDatos(datos);
		reservarFondos(datos, montoPedido);
		ejecutarTransaccion(datos, montoPedido);
		notificarResultado();
	}
	public abstract void validarDatos(DatosDePago datos);
	
	public abstract void reservarFondos(DatosDePago datos, Double monto);
	
	public abstract void ejecutarTransaccion(DatosDePago datos, Double monto);
	
	public String notificarResultado() {
		return "Tu transaccion ha sido procesada";
	}
	
}
