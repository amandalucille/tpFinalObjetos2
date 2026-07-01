package eCommerce.filtro;

import eCommerce.item.ItemCatalogo;

public class FiltroPorCategoria extends CriterioDeBusqueda {
	private String categoria;
	
	public FiltroPorCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	@Override
	public Boolean cumple(ItemCatalogo item) {
		return item.getCategoria().equalsIgnoreCase(categoria);
	}

}
