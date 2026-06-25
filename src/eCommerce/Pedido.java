package eCommerce;

import java.util.HashMap;
import java.util.Map;

import eCommerce.estados.Estado;
import eCommerce.estados.EstadoBorrador;
import eCommerce.item.ItemCatalogo;

public class Pedido {
	
	private Estado estado;
	private Map<ItemCatalogo, Integer> items;

	
	public Pedido() {
		this.estado = new EstadoBorrador();
		this.items = new HashMap<ItemCatalogo, Integer>();

	}
	
	public void addItem(ItemCatalogo item, Integer cantidad) {
		if(!estado.sePuedeAgregarProducto()) {
			throw new RuntimeException("No podes agregar item.");
		}
		if (!item.hayStock(cantidad)) {
			throw new RuntimeException("¡No hay stock del item que queres agregar!");
		}
//Si ya existia en el map el item que estoy agregando, recupero el stock con getOrDefault y le sumo la cantidad pasada por parametros
//si no existia getOrDefault te devuelve 0, y le sumo la cantidad. 
		
		this.items.put(item, this.items.getOrDefault(item, 0) + cantidad);
	}
	
	public void removeItem(ItemCatalogo item) {
		if(items.isEmpty()) {
			throw new RuntimeException("No hay items en el pedido.");
		}
		
		if(!items.containsKey(item)) {
			throw new RuntimeException("No tenes este item en el pedido.");			
		}
				
		this.items.remove(item);
	}

	public void prepararPedido() {
		this.estado.enPreparacion(this); 
	}
	
	public void confirmarPedido() {
		this.estado.confirmar(this);
	}
	
	public void enviarPedido() {
		this.estado.enviar(this); 
	}
	
	public void entregarPedido() {
		this.estado.entregado(this); 
	}
	
	public void cancelarPedido() {
		this.estado.cancelar(this); 
	}
	
	public void decrementarStock() {
		items.forEach((item, cantidad) -> item.decrementarStock(cantidad));

		
	}
	
	public void devolverStock() {
		items.forEach((item, cantidad) -> item.aumentarStock(cantidad));
	}
	
	public void setEstado(Estado nuevoEstado) {
		this.estado = nuevoEstado;
	}
	
	public Estado getEstado() {
		return this.estado;
	}
	
	public Double valorTotalPedido() {
		//La interfaz dada pide que el tipo sea float, pero manejamos todo con double entonces cuando lo necesitemos, podemos hacer .floatValue().
		return this.items.entrySet()
				   .stream()
				   .mapToDouble(itemCant -> itemCant.getKey().getPrecioFinal() * itemCant.getValue())
				   .sum();
	}

}
