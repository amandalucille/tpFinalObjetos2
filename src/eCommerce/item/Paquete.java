package eCommerce.item;

import java.util.HashMap;
import java.util.Map;

public class Paquete implements ItemCatalogo{
	private String nombre;
	private String descripcion;
	private Double descuento;
	private Map<ItemCatalogo, Integer> items;
	private Integer stock;
	
	public Paquete(String nombre, String descripcion, Double descuento, Integer stock) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.descuento = descuento;
		this.items = new HashMap<>();
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
		return this.items.entrySet()
						 .stream()
						 .mapToDouble(itemCant -> itemCant.getKey().getPrecioFinal() * itemCant.getValue()) 
						 .sum();
	}

	public Double getPrecioFinal() {
		return this.getPrecioBase() * (1 - this.descuento/100);
	}
	
	public void addItem(ItemCatalogo item, Integer cantidad) {
		item.decrementarStock(cantidad);
		this.items.put(item, cantidad);
	}
	
	
	public void removeItem(ItemCatalogo item) {
		this.items.remove(item);
	}
	
	public Boolean hayStock(Integer cantidad) {
		return this.stock >= cantidad;
	}
	
	public void decrementarStock(Integer cantidad) {
		
		if (!hayStock(cantidad)) {
		    throw new RuntimeException("Sin stock");
		}
		this.stock -= cantidad;
	}
	
	public void aumentarStock(Integer cantidad) {
		this.stock += cantidad;
	}
	
	public Double getPeso() {
		return items.entrySet()
					.stream()
					.mapToDouble(itemCant -> itemCant.getKey().getPeso() * itemCant.getValue())
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

	public Integer stockDisponible() {
		return this.stock;
	}
}
