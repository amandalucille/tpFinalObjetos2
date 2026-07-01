package eCommerce.item;

import java.util.HashMap;
import java.util.Map;

import eCommerce.errores.*;
import eCommerce.visitor.ReporteVisitor;

public class Paquete implements ItemCatalogo{
	private String nombre;
	private String descripcion;
	private Double descuento;
	private Map<ItemCatalogo, Integer> items;
	private Integer stock;
	private String categoria;
	
	public Paquete(String nombre, String descripcion, String categoria, Double descuento, Integer stock) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.categoria = categoria;
		this.descuento = descuento;
		this.items = new HashMap<>();
		this.stock = stock;
		
		this.validarItem();
	}
	
	public Paquete(String nombre, String descripcion, String categoria, Integer stock) {
		this(nombre, descripcion, categoria, 0.0, stock);
	}
	
	@Override
	public String getNombre() {
		return this.nombre;
	}
	
	@Override
	public String getCategoria() {
		return this.categoria;
	}
	
	@Override
	public Integer stockDisponible() {
		return this.stock;
	}
	
	@Override
	public String getDescripcion() {
		return this.descripcion;
	}
	
	@Override
	public Double getPrecioBase() {
		return this.items.entrySet()
						 .stream()
						 .mapToDouble(itemCant -> itemCant.getKey().getPrecioFinal() * itemCant.getValue()) 
						 .sum();
	}

	@Override
	public Double getPrecioFinal() {
		return this.getPrecioBase() * (1 - this.descuento/100);
	}
	
	@Override
	public Boolean hayStock(Integer cantidad) {
		return this.stock >= cantidad;
	}
	
	
	public void addItem(ItemCatalogo item, Integer cantidad) {
		item.decrementarStock(cantidad * stockDisponible());
		this.items.put(item, cantidad);
	}
	
	
	public void removeItem(ItemCatalogo item) {
		this.items.remove(item); //Agregar que le devuelva el stock al item. (Cantidad * stock del paquete)s
	}
	
	@Override
	public void decrementarStock(Integer cantidad) {
	    if (!hayStock(cantidad)) {
	        throw new StockInsuficienteException(getNombre(), this.stock);
	    }
	    this.stock -= cantidad;
	}
	
	@Override
	public void aumentarStock(Integer cantidad) {
		this.items.forEach((item,cant) -> item.decrementarStock(cantidad*cant));
		
		this.stock += cantidad;
	}
	
	@Override
	public Double getPeso() {
		return items.entrySet()
					.stream()
					.mapToDouble(itemCant -> itemCant.getKey().getPeso() * itemCant.getValue())
					.sum();
	}
	
	@Override
	public void validarItem() {
	    if (this.nombre == null || this.nombre.isBlank()) {
	        throw new ItemInvalidoException("Nombre Desconocido", "El nombre del paquete no puede estar nulo o vacío.");
	    }
	    
	    if (this.descripcion == null || this.descripcion.isBlank()) {
	        throw new ItemInvalidoException(this.nombre, "La descripción del paquete es obligatoria.");
	    }
	    if (this.categoria == null || this.categoria.isBlank()) {
	        throw new ItemInvalidoException(this.nombre, "La categoría del paquete es obligatoria.");
	    }
	    if (this.stock == null || this.stock < 0) {
	        throw new ItemInvalidoException(this.nombre, "El stock no puede ser nulo ni tener un valor negativo.");
	    }
		
	}


	@Override
	public void aceptar(ReporteVisitor reporteVisitor) {
		reporteVisitor.visitar(this);
		
	}
}
