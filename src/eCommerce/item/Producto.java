package eCommerce.item;

import java.util.HashSet;
import java.util.Set;

public class Producto implements ItemCatalogo{
	
	private String sku;
	private String nombre;
	private String descripcion;
	private String marca;
	private String categoria;
	private Double precio;
	private Double descuento; //este no es obligatorio!! Así que hay un constructor que lo contempla por si lo queres agregar. 
	private Set<Atributo<?>> atributos;
	private Integer stock;
	private Double peso;
	
	
	public Producto(String sku, String nombre, String descripcion,
	            String marca, String categoria, Double precio, Double descuento, Integer stock, Double peso) {
		this.sku = sku;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.marca = marca;
		this.categoria = categoria;
		this.precio = precio;
		this.descuento = descuento;
		this.atributos = new HashSet<>();
		this.stock = stock;
		this.peso = peso;
		
		this.validarItem(); 
	}
	
	public Producto(String sku, String nombre, String descripcion,
            String marca, String categoria, Double precio, Integer stock, Double peso) {
		this(sku, nombre, descripcion, marca, categoria, precio, 0.0, stock,peso);
	}
	
	
	public Double getPrecioFinal() {
		return this.getPrecioBase() * (1 - this.descuento / 100); 
	}
	
	public void validarItem() {
		if (!esItemValido()) {
			throw new RuntimeException("No es un producto válido");
		}
	}
	
	public Boolean esItemValido() {
		return (this.sku    	 != null && !this.sku.isBlank()) 		 &&   
			   (this.nombre 	 != null && !this.nombre.isBlank())  	 && 
			   (this.descripcion != null && !this.descripcion.isBlank()) && 
			   (this.marca 		 != null && !this.marca.isBlank()) 		 &&
			   (this.categoria 	 != null && !this.categoria.isBlank())	 &&
			   (this.precio 	 != null && this.precio > 0)             &&
			   (this.stock       != null && this.stock >= 0)			 &&
			   (this.peso 		 != null && this.peso > 0);
		
	}
	
	public void agregarAtributo(Atributo<?> atributo) {
		//ver si hace falta agregar una validación antes de agregar un nuevo atributo
		this.atributos.add(atributo);
	}
	
	public Set<Atributo<?>> getAtributos() {
		return atributos;
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
		return peso;
	}

	public Integer stockDisponible() {
		return this.stock ;
	}

	//Cuando necesite este valor en Float, puedo hacer getPeso().floatValue()
	
}
