package eCommerce.filtro.compuertasLogicas;

import java.util.List;

import eCommerce.filtro.CriterioDeBusqueda;
import eCommerce.item.ItemCatalogo;

public class CriterioAnd extends CriterioDeBusqueda  {
	private List<CriterioDeBusqueda> criterios;
	
	
	public CriterioAnd(CriterioDeBusqueda... criterios) { //  El ... es varargs en Java, podés pasar todos los CriteriosDeBusqueda que quieras
		this.criterios = List.of(criterios);
	}
	
	@Override
	public Boolean cumple(ItemCatalogo item) {
		return criterios.stream()
						.allMatch(c -> c.cumple(item));
	}
	
	
	
	
	//  filtro1 = new CriterioAnd(new PorCategoria("Electrónica"), new PorDisponibilidad()),
	
	//filtro2 = new CriterioAnd(filtro1, new BusquedaPorNombre("book"), new BusuqedaPrecioMax(2000))
}


