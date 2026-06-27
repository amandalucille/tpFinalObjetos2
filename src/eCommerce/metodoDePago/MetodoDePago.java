package eCommerce.metodoDePago;
import eCommerce.Pedido;
import eCommerce.comprobantes.Comprobante;
public abstract class MetodoDePago {
	 
	private Comprobante comprobante;
	
	public final void procesarPago(Pedido pedido) {
		
		validarDatos(pedido);
		reservarFondos(pedido);
		ejecutarTransaccion(pedido);
		notificarResultado(pedido); //return
	}
	public abstract void validarDatos(Pedido pedido);
	
	public abstract void reservarFondos(Pedido pedido);
	
	public abstract void ejecutarTransaccion(Pedido pedido);
	
	public String notificarResultado(Pedido pedido) {
		return "Tu transaccion ha sido procesada";
	}
	
	public void setComprobante(Comprobante comprobante) {
		this.comprobante = comprobante;
	}
	public Comprobante getComprobante() {
		return comprobante;
	}
}
