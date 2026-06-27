package eCommerce.filtro;

import eCommerce.item.ItemCatalogo;

public abstract class CriterioDeBusqueda {
	
	public abstract Boolean cumple(ItemCatalogo item);

}
