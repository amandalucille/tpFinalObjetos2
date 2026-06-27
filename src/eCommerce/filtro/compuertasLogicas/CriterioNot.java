package eCommerce.filtro.compuertasLogicas;

import eCommerce.filtro.CriterioDeBusqueda;
import eCommerce.item.ItemCatalogo;

public class CriterioNot extends CriterioDeBusqueda {

	private CriterioDeBusqueda criterio;
	
	public CriterioNot(CriterioDeBusqueda criterio) {
		this.criterio = criterio;
	}
	
	@Override
	public Boolean cumple(ItemCatalogo item) {
		return !criterio.cumple(item);
	}

}
