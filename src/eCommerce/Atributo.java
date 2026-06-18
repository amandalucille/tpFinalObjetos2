package eCommerce;

public class Atributo <T> {
	
	private String descripcion;
	private T valor;
	
	public Atributo (String descripcion, T valor) {
		this.descripcion = descripcion;
		this.valor = valor;

	}
	
	public String getDescripcion() {
		return this.descripcion;
	}
	public T getValor() {
		return this.valor;
	
	}
//	public void setDescripcion(String nuevaDescripcion) {
//		this.descripcion = nuevaDescripcion;
//	}
//	public void setValor(T nuevoValor) {
//		this.valor = nuevoValor;
//	}
	
}

