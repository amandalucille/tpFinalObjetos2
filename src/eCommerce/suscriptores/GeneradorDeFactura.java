package eCommerce.suscriptores;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eCommerce.Pedido;
import eCommerce.comprobantes.Factura; 

public class GeneradorDeFactura implements PedidoObserver {
	private Map<Pedido, Factura> facturas;

	public GeneradorDeFactura() {
		this.facturas = new HashMap<>();
	}
	
	@Override
	public void notificarPedidoEntregado(Pedido pedido){
		this.facturas.put(pedido, new Factura(pedido));
	}
	
	public Factura getFactura(Pedido pedido) {
		return this.facturas.get(pedido);
	}
	
	public List<Factura> getFacturas(){
		return new ArrayList<>(facturas.values());
	}
	
}
