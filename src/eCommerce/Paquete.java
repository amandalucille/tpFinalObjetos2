package eCommerce;

import java.util.ArrayList;
import java.util.List;

public class Paquete implements ItemCatalogo{
	private String nombre;
	private String descripcion;
	private Double descuento;
	private List<ItemCatalogo> items;
	private Integer stock;
	
	public Paquete(String nombre, String descripcion, Double descuento, Integer stock) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.descuento = descuento;
		this.items = new ArrayList<>();
		this.stock = stock;
		
		this.validarItem();
	}
	
	public Paquete(String nombre, String descripcion, Integer stock) {
		this(nombre, descripcion, 0.0, stock);
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
		item.decrementarStock();
		this.items.add(item);
	}
	
	public void removeItem(ItemCatalogo item) {
		this.items.remove(item);
	}
	
	public Boolean hayStock() {
		return this.stock >= 1;
	}
	
	public void decrementarStock() {
		
		if (!hayStock()) {
		    throw new RuntimeException("Sin stock");
		}
		this.stock -= 1;
	}
	
	public void aumentarStock() {
		this.stock += 1;
	}
	
	public Double getPeso() {
		return items.stream()
					.mapToDouble(item -> item.getPeso())
					.sum();
	}
	public void validarItem() {
		if (!esItemValido()) {
			throw new RuntimeException("No es un Paquete válido");
		}
		
	}
	public Boolean esItemValido() {
		return (this.nombre 	 != null && !this.nombre.isBlank()) 	 &&
			   (this.descripcion != null && !this.descripcion.isBlank()) &&
			   (this.stock       != null && this.stock >= 0);
	}
}
