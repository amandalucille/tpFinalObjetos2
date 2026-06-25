package eCommerce;

import java.util.HashSet;
import java.util.Set;

import eCommerce.item.ItemCatalogo;

public class Catalogo {

	private Set<ItemCatalogo> itemsDelCatalogo = new HashSet<>();
	
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


