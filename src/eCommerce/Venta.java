package eCommerce;
import java.util.Map;

import eCommerce.item.*;

public class Venta {
	//Clase que registra el valor final de que cada item vendido en el pedido 
	//al momento de realizar la compra (Historial)
	private Pedido pedido;
	private Map<ItemCatalogo, Double> precioFinalPorItem;
	
	public Venta(Pedido pedido, Map<ItemCatalogo, Double> precioFinalPorItem ) {
		this.pedido = pedido;
		this.precioFinalPorItem = precioFinalPorItem;
	}
}
