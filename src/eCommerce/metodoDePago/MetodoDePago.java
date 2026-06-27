package eCommerce.metodoDePago;
import eCommerce.Pedido;
import eCommerce.comprobantes.Comprobante;

public abstract class MetodoDePago {
	 
	private Comprobante comprobante;
	private String nroTransaccion;
	
	public final void procesarPago(Pedido pedido) {
		
		validarDatos(pedido);
		reservarFondos(pedido);
		ejecutarTransaccion(pedido);
		notificarResultado(pedido); //return
	}
	
	public abstract void validarDatos(Pedido pedido);
	public abstract void reservarFondos(Pedido pedido);
	public abstract void ejecutarTransaccion(Pedido pedido);
	
	public void notificarResultado(Pedido pedido) {
		this.setComprobante(new Comprobante(this.getNroTransaccion()));
	}
	
	public void setComprobante(Comprobante comprobante) {
		this.comprobante = comprobante;
	}
	public Comprobante getComprobante() {
		return this.comprobante;
	}
	public String getNroTransaccion() {
		return nroTransaccion;
	}
	public void setNroTransaccion(String nroTransaccion) {
		this.nroTransaccion = nroTransaccion;
	}
	
	
}
