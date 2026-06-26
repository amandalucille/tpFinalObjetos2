package eCommerce;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import eCommerce.item.ItemCatalogo;

public class ECommerce {

	private Set<ItemCatalogo> itemsDelCatalogo;
	private List<Pedido> pedidos;
	private List<NotaDeCredito> notasDeCredito;
	
	public ECommerce() {
		this.itemsDelCatalogo = new HashSet<>();
		this.notasDeCredito = new ArrayList<>();
		this.pedidos = new ArrayList<>();
	}
	
	public void agregarItem(ItemCatalogo item) {
		this.itemsDelCatalogo.add(item);
	}
	
}


