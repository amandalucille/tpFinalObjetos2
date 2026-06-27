package eCommerce.filtro;

import eCommerce.item.ItemCatalogo;

public class FiltroPorDisponibilidad extends CriterioDeBusqueda {
	@Override
	public Boolean cumple(ItemCatalogo item) {
		return item.hayStock(1);
	}

}
