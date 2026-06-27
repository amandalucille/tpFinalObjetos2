package eCommerce;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import eCommerce.comprobantes.Comprobante;
import eCommerce.comprobantes.NotaDeCredito;
import eCommerce.filtro.CriterioDeBusqueda;
import eCommerce.item.ItemCatalogo;
import eCommerce.visitor.ReporteVisitor;

public class ECommerce{

	private Set<ItemCatalogo> itemsDelCatalogo;
	private List<Pedido> pedidos;
	private List<Venta> ventas;
	
	
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
	public void reporteDeProductosMasVendidos(ReporteVisitor reporte) {
		
	}
	public Map<ItemCatalogo,Integer> cantidadDeVentasPorItem(){
		Map<ItemCatalogo,Integer> ventas = new HashMap<>();
		itemsDelCatalogo.stream()
						.forEach(i -> ventas.put(i, this.cantidadDeVentasDelItem(i)));
		//Recorrer el set de itemsDelCatalogo y por cada item quiero que la clave sea
		//ese item y el valor sea el llamado a cantidadDeVentasDeItem()
		return ventas;
	}
	
	public List<Pedido> ventasExitosas(){
		return pedidos.stream()
					  .filter(p -> p.esVentaExitosa())
					  .toList();
	}
	
	public Integer cantidadDeVentasDelItem(ItemCatalogo item) {
		
		return this.ventasExitosas().stream()
									.mapToInt(p -> p.cantidadVendidaDe(item))
									.sum();
		
	}

	public void agregarNuevaVenta(Venta venta) {
		this.ventas.add(venta);
		
	}
}


