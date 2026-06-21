package eCommerce;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
	
	private Estado estado;
	private List<ItemCatalogo> items;
	private Catalogo catalogo;
	
	public Pedido() {
		this.estado = new EstadoBorrador();
		this.items = new ArrayList<ItemCatalogo>();
	}
	
	public void addItem(ItemCatalogo item) {
		this.items.add(item);
	}
	
	public void removeItem(ItemCatalogo item) {
		this.items.remove(item);
	}

	public void enPreparacion() {
		this.estado.enPreparacion(this); 
	}
	
	public void confirmarPedido() {
		this.estado.confirmar(this);
	}
	
	public void enviar() {
		this.estado.enviar(this); 
	}
	
	public void entregado() {
		this.estado.entregado(this); 
	}

	public void cancelar() {
		
	}
	
	public void decrementarStock() {
		catalogo.decrementarStock(items);
	}
	
	public void devolverStock() {
		catalogo.aumentarStock(items);
	}
	
	public void setEstado(Estado nuevoEstado) {
		this.estado = nuevoEstado;
	}
	

}
