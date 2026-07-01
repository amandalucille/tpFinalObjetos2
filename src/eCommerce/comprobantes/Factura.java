package eCommerce.comprobantes;

import java.util.ArrayList;
import java.util.List;

import eCommerce.Pedido;

public class Factura {
	private Pedido pedido;
	private List<DetalleFacturableItem> preciosPorItem;
	private Float montoTotalEnvio;
	
	public Factura(Pedido pedido) {
		this.pedido = pedido;
		this.montoTotalEnvio = pedido.montoEnvio(); // costo de envio
		this.preciosPorItem = new ArrayList<>();
		generarCredencialesItems(pedido);
	}
	
	public Double totalFacturado() {
		return preciosPorItem.stream()
							 .mapToDouble(i -> i.getPrecioUnitarioFinal() * i.getCantidadVendida() )
							 .sum() + getMontoEnvio();
	}
	
	private void generarCredencialesItems(Pedido pedido) {
		pedido.getItems().entrySet()
		 				 .stream()
		 				 .forEach(itemCant -> preciosPorItem.add( new DetalleFacturableItem(itemCant.getKey(), itemCant.getValue())));
	}
	
	public Float getMontoEnvio() {
		return this.montoTotalEnvio;
	}
	
	public List<DetalleFacturableItem> getDetalles() {
	    return this.preciosPorItem;
	}
}
