package eCommerce;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
	
	private Estado estado;
	private List<ItemCatalogo> items;

	
	public Pedido() {
		this.estado = new EstadoBorrador();
		this.items = new ArrayList<ItemCatalogo>();

	}
	
	public void addItem(ItemCatalogo item) {
		if(!estado.sePuedeAgregarProducto()) {
			throw new RuntimeException("No podes agregar item.");
		}
		this.items.add(item);
	}
	
	public void removeItem(ItemCatalogo item) {
		if(items.isEmpty()) {
			throw new RuntimeException("No hay items en el pedido.");
		}
		
		if(!items.contains(item)) {
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
		items.stream()
			 .forEach(item -> item.decrementarStock());
		
	}
	
	public void devolverStock() {
		items.stream()
		 .forEach(item -> item.aumentarStock());
	}
	
	public void setEstado(Estado nuevoEstado) {
		this.estado = nuevoEstado;
	}
	
	public Estado getEstado() {
		return this.estado;
	}
	

}
