package eCommerce;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import eCommerce.comprobantes.Comprobante;
import eCommerce.item.ItemCatalogo;

public class ECommerce implements PedidoObserver{

	private Set<ItemCatalogo> itemsDelCatalogo;
	private List<Pedido> pedidos;
	private List<NotaDeCredito> notasDeCredito;
	private List<Comprobante> comprobantesDePago;
	
	public ECommerce() {
		this.itemsDelCatalogo = new HashSet<>();
		this.notasDeCredito = new ArrayList<>();
		this.pedidos = new ArrayList<>();
	}
	
	public void agregarItem(ItemCatalogo item) {
		this.itemsDelCatalogo.add(item);
	}
	
	public void agregarPedido(Pedido pedido) {
		this.pedidos.add(pedido);
		pedido.subscribeObserver(this);
	}
	
	@Override
	public void addNotaDeCredito(NotaDeCredito notaDeCredito) {
		this.notasDeCredito.add(notaDeCredito);
	}

	@Override
	public void addComprobanteDePago(Comprobante comprobanteDePago) {
		this.comprobantesDePago.add(comprobanteDePago);		
	}
	
}


