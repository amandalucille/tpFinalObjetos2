package eCommerce.comprobantes;

import eCommerce.Pedido;

public class Factura {
	private Double montoTotalItems;
	private Float montoTotalEnvio;
	
	public Factura(Pedido pedido) {
		this.montoTotalItems = pedido.montoTotalItems();
		this.montoTotalEnvio = pedido.montoEnvio();
		
	}
}
