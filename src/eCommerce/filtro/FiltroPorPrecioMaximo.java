package eCommerce.filtro;

import eCommerce.item.ItemCatalogo;

public class FiltroPorPrecioMaximo extends CriterioDeBusqueda {

	private Double precioMaximo;
	
	public FiltroPorPrecioMaximo(Double precioMaximo) {
		this.precioMaximo = precioMaximo;
	}

	@Override
	public Boolean cumple(ItemCatalogo item) {
		return item.getPrecioBase() <= this.precioMaximo ;
	}

}
