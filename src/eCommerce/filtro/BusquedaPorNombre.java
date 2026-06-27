package eCommerce.filtro;

import eCommerce.item.ItemCatalogo;

public class BusquedaPorNombre extends CriterioDeBusqueda {
	private String nombre;
	
	public BusquedaPorNombre(String nombre) {
		this.nombre = nombre.toLowerCase(); // pasa todo el string a minúscula: JaVa --> java
	}
	
	@Override
	public Boolean cumple(ItemCatalogo item) {
		return item.getNombre().toLowerCase().contains(nombre); //  "programando en java".contains("java") --> va a dar true
	}

}
