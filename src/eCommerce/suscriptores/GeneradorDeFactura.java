package eCommerce.suscriptores;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eCommerce.Pedido;
import eCommerce.comprobantes.Factura;
import eCommerce.errores.NoTieneFacturaException; 

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
		if (this.facturas.get(pedido) == null) {
			throw new NoTieneFacturaException();
		}
		return this.facturas.get(pedido); 
	}
	
	public List<Factura> getFacturas(){
		return new ArrayList<>(facturas.values());
	}
	
}
