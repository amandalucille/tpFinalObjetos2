package eCommerce;

import java.util.ArrayList;
import java.util.List;

public class Producto implements ItemCatalogo{
	
	private String sku;
	private String nombre;
	private String descripcion;
	private String marca;
	private String categoria;
	private Double precio;
	private Double descuento; //este no es obligatorio!! Así que hay un constructor que lo contempla por si lo queres agregar. 
	private List<Atributo<?>> atributos;
	private int stock;
	
	
	public Producto(String sku, String nombre, String descripcion,
	            String marca, String categoria, Double precio, Double descuento, int stock) {
		this.sku = sku;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.marca = marca;
		this.categoria = categoria;
		this.precio = precio;
		this.descuento = descuento;
		this.atributos = new ArrayList<>();
		this.stock = stock;
	}
	
	public Producto(String sku, String nombre, String descripcion,
            String marca, String categoria, Double precio, int stock) {
		this(sku, nombre, descripcion, marca, categoria, precio, 0.0, stock);
	}
	
	
	public Double getPrecioFinal() {
		return this.getPrecioBase() * (1 - this.descuento / 100); 
	}
	
	public boolean esProductoValido() {
		return this.sku    		!= null &&
			   this.nombre 		!= null &&
			   this.descripcion != null && 
			   this.marca 		!= null &&
			   this.categoria 	!= null &&
			   this.precio 		!= null;

	}
	
	public void agregarAtributo(Atributo<?> atributo) {
		//ver si hace falta agregar una validación antes de agregar un nuevo atributo
		this.atributos.add(atributo);
	}
	
	
	@SuppressWarnings("unchecked")
	public <T> T obtenerAtributo(String descripcion) {
	
		return (T) this.atributos.stream()
							 	 .filter(atributo -> atributo.getDescripcion().equals(descripcion))
							     .findFirst()
							     .orElseThrow(() -> new RuntimeException("El atributo '" + descripcion + "' no existe"))
							     .getValor();	
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}

	public Double getPrecioBase() {
		return precio;
	}

	public void decrementarStock() {
		
		if (this.stock < 1) {
		    throw new RuntimeException("Sin stock");
		}
		this.stock -= 1;
	}
	
	public void aumentarStock() {
		this.stock += 1;
	}

}
