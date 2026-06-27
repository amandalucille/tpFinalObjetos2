package eCommerce.filtro;

import eCommerce.item.ItemCatalogo;

public class BusquedaPorCategoria extends CriterioDeBusqueda {
	private String categoria;
	
	public BusquedaPorCategoria(String categoria) {
		this.categoria = categoria;
	}
	@Override
	public Boolean cumple(ItemCatalogo item) {
		return item.getCategoria() == categoria;
	}

}
