package eCommerce;

import java.util.ArrayList;
import java.util.List;

public class Paquete implements ItemCatalogo{
	private String nombre;
	private String descripcion;
	private Double descuento;
	private List<ItemCatalogo> items;
	
	public Paquete(String nombre, String descripcion, Double descuento) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.descuento = descuento;
		this.items = new ArrayList<>();
	}
	
	public Paquete(String nombre, String descripcion) {
		this(nombre, descripcion, 0.0);
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}

	public Double getPrecioBase() {
		return this.items.stream()
							 .mapToDouble(item -> item.getPrecioFinal()) // .mapToDouble(item -> item.getPrecioBase())
							 .sum();
	}

	public Double getPrecioFinal() {
		return this.getPrecioBase() * (1 - this.descuento/100);
	}
	
	public void addItem(ItemCatalogo item) {
		this.items.add(item);
	}
	
	public void removeItem(ItemCatalogo item) {
		this.items.remove(item);
	}
	
	
}
