package eCommerce;

import java.util.ArrayList;
import java.util.List;

public class Catalogo {

	private List<ItemCatalogo> itemsDelCatalogo = new ArrayList<>();
	
	public void decrementarStock(List<ItemCatalogo> itemsPedido) {
		itemsPedido.stream().forEach(item -> item.decrementarStock());
	}
}


