package eCommerce;

import java.util.Objects;

public abstract class Atributo <T> {
	
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
	
	@SuppressWarnings("unchecked")
	@Override
    public boolean equals(Object obj) {
        if (this == obj){ 
        	return true;
        }
        if (!(obj instanceof Atributo)){ 
        	return false;
        }	

        Atributo<T> otro = (Atributo<T> ) obj;
        return Objects.equals(this.descripcion, otro.getDescripcion());
    }
	 
	@Override
	public int hashCode() {
		return Objects.hash(descripcion); // mismo criterio que equals
	}


//	public void setDescripcion(String nuevaDescripcion) {
//		this.descripcion = nuevaDescripcion;
//	}
//	public void setValor(T nuevoValor) {
//		this.valor = nuevoValor;
//	}
	
}

