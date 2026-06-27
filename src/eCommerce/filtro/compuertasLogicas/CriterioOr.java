package eCommerce.filtro.compuertasLogicas;

import java.util.List;

import eCommerce.filtro.CriterioDeBusqueda;
import eCommerce.item.ItemCatalogo;

public class CriterioOr extends CriterioDeBusqueda {
	private List<CriterioDeBusqueda> criterios;
	
	
	public CriterioOr(CriterioDeBusqueda... criterios) { //  El ... es varargs en Java, podés pasar todos los CriteriosDeBusqueda que quieras
		this.criterios = List.of(criterios);
	}
	
	@Override
	public Boolean cumple(ItemCatalogo item) {
		return criterios.stream()
						.anyMatch(c -> c.cumple(item));
	}
}
