package eCommerce;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import eCommerce.comprobantes.Comprobante;
import eCommerce.comprobantes.NotaDeCredito;
import eCommerce.filtro.CriterioDeBusqueda;
import eCommerce.item.ItemCatalogo;

public class ECommerce{

	private Set<ItemCatalogo> itemsDelCatalogo;
	private List<Pedido> pedidos;
	
	
	public ECommerce() {
		this.itemsDelCatalogo = new HashSet<>();
		this.pedidos = new ArrayList<>();
	}
	
	public void agregarItem(ItemCatalogo item) {
		this.itemsDelCatalogo.add(item);
	}
	
	public void agregarPedido(Pedido pedido) {
		this.pedidos.add(pedido);
	}
	
	public List<NotaDeCredito> getNotasDeCredito() {
		return pedidos.stream()
					  .map(pedido -> pedido.getNotaDeCredito())
					  .filter(nc -> nc != null) 
					  .toList();
	}
	
	public List<Comprobante> getComprobante() {
		return pedidos.stream()
					  .map(pedido -> pedido.getComprobante())
					  .filter(c -> c != null) 
					  .toList();
	}
	
	public List<ItemCatalogo> filtrar(CriterioDeBusqueda criterio){
		return itemsDelCatalogo.stream()
							   .filter(item -> criterio.cumple(item))
							   .toList();
		
	}
}


