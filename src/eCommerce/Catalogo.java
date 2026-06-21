package eCommerce;

import java.util.ArrayList;
import java.util.List;

public class Catalogo {

	private List<ItemCatalogo> itemsDelCatalogo = new ArrayList<>();
	
//	//esto no sabemos si lo vamos a usar en el mopdelo
//	public void decrementarStock(List<ItemCatalogo> itemsPedido) {
//		itemsPedido.stream().forEach(item -> item.decrementarStock());
//	}
//	
//	public void aumentarStock(List<ItemCatalogo> itemsPedido) {
//		itemsPedido.stream().forEach(item -> item.aumentarStock());
//	}
	
	public void agregarItem(ItemCatalogo item) {
		this.itemsDelCatalogo.add(item);
	}
	
}


