package eCommerce.item;

import eCommerce.errores.*;

import java.util.HashSet;
import java.util.Set;

import eCommerce.visitor.ReporteVisitor;

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
	                String marca, String categoria, Double precio, 
	                Double descuento, Integer stock, Double peso) {
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
	
	@Override
	public String getCategoria() {
		return this.categoria;
	}
	
	@Override
	public Double getPrecioFinal() {
		return this.getPrecioBase() * (1 - this.descuento / 100); 
	}
	
	@Override
	public void validarItem() {
	    if (this.nombre == null || this.nombre.isBlank()) {
	        throw new ItemInvalidoException("Producto Desconocido", "El nombre del producto es obligatorio y no puede estar vacío.");
	    }

	    if (this.sku == null || this.sku.isBlank()) {
	        throw new ItemInvalidoException(this.nombre, "El SKU no puede ser nulo o vacío.");
	    }

	    if (this.descripcion == null || this.descripcion.isBlank()) {
	        throw new ItemInvalidoException(this.nombre, "La descripción del producto es obligatoria.");
	    }

	    if (this.marca == null || this.marca.isBlank()) {
	        throw new ItemInvalidoException(this.nombre, "La marca del producto es obligatoria.");
	    }

	    if (this.categoria == null || this.categoria.isBlank()) {
	        throw new ItemInvalidoException(this.nombre, "La categoría del producto es obligatoria.");
	    }

	    if (this.precio == null || this.precio <= 0) {
	        throw new ItemInvalidoException(this.nombre, "El precio debe ser un valor mayor a 0.");
	    }

	    if (this.stock == null || this.stock < 0) {
	        throw new ItemInvalidoException(this.nombre, "El stock no puede ser nulo ni tener un valor negativo.");
	    }

	    if (this.peso == null || this.peso <= 0) {
	        throw new ItemInvalidoException(this.nombre, "El peso del producto debe ser un valor mayor a 0.");
	    }
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
							     .orElseThrow(() -> new AtributoNoEncontradoException("El atributo '" + descripcion + "' no existe"))
							     .getValor();	
	}
	
	@Override
	public String getNombre() {
		return this.nombre;
	}
	
	@Override
	public String getDescripcion() {
		return descripcion;
	}

	@Override
	public Double getPrecioBase() {
		return precio;
	}
	
	@Override
	public Boolean hayStock(Integer cantidad) {
		return this.stock >= cantidad;
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
		this.stock += cantidad;
	}

	@Override
	public Double getPeso() {
		return peso;
	}

	@Override
	public Integer stockDisponible() {
		return this.stock ;
	}
	
	@Override
	public void aceptar(ReporteVisitor reporteVisitor) {
		reporteVisitor.visitar(this);
		
	}

}
