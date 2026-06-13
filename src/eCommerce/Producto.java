package eCommerce;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Producto {
	
	private String sku;
	private String nombre;
	private String descripcion;
	private String marca;
	private String categoria;
	private Double precio;
	private Double descuento; //este no es obligatorio!! Así que hay un constructor que lo contempla por si lo queres agregar. 
	private List<Atributo<?>> atributos;
	
	public Producto(String sku, String nombre, String descripcion, 
					String marca, String categoria, Double precio) {
		this.sku = sku;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.marca = marca;
		this.categoria = categoria;
		this.precio = precio;
		this.atributos = new ArrayList<Atributo<?>>();
		this.descuento = 0.0;
	}
	
	public Producto(String sku, String nombre, String descripcion, 
			String marca, String categoria, Double precio, Double descuento) {
		this.sku = sku;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.marca = marca;
		this.categoria = categoria;
		this.precio = precio;
		this.descuento = descuento;
		this.atributos = new ArrayList<Atributo<?>>();
}
		
	public Double precioFinal() {
		return this.precio * (1 - this.descuento / 100); 
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
	
	public <?> obtenerAtributo(String descripcion) {
		return this.atributos.stream().filter(a-> a.getDescripcion() == descripcion) ;
		
	}
	
	
}
