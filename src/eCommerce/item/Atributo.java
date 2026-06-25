package eCommerce.item;

import java.util.Objects;

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
	
	@SuppressWarnings("unchecked")
	@Override
    public boolean equals(Object obj) {

        if (!(obj instanceof Atributo)){ 
        	return false;
        }	

        //El if es necesario para que no se intente castear objetos que no sean de tipo atributo 
        //Si no lo es, corta la ejecución antes de llegar a la comparación. 
        
        Atributo<T> otro = (Atributo<T> ) obj;
        return (this == obj) || Objects.equals(this.descripcion, otro.getDescripcion());
    }
	 
	@Override
	public int hashCode() {
		return Objects.hash(descripcion); // mismo criterio que equals
	}

}

