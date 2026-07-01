package eCommerce;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import eCommerce.ResumenVentaItem.*;
import eCommerce.comprobantes.Comprobante;
import eCommerce.comprobantes.DetalleFacturableItem;
import eCommerce.comprobantes.Factura;
import eCommerce.comprobantes.NotaDeCredito;
import eCommerce.filtro.CriterioDeBusqueda;
import eCommerce.item.ItemCatalogo;
import eCommerce.libreriasExternas.MailSender;
import eCommerce.libreriasExternas.MessageSender;
import eCommerce.suscriptores.Fidelizador;
import eCommerce.suscriptores.GeneradorDeFactura;
import eCommerce.suscriptores.NotificadorDeEmail;
import eCommerce.suscriptores.PedidoObserver;
import eCommerce.visitor.ReporteVisitor;

public class ECommerce{

	private Set<ItemCatalogo> itemsDelCatalogo;
	private List<Pedido> pedidos;
	private List<Venta> ventas;
	private List<PedidoObserver> suscriptores;
	private GeneradorDeFactura generadorDeFacturas;
	private MailSender apiMail;
	private MessageSender apiMsg;
	
	
	public ECommerce(MailSender apiMail, MessageSender apiMsg) {
		this.itemsDelCatalogo = new HashSet<>();
		this.pedidos = new ArrayList<>();
		this.generadorDeFacturas = new GeneradorDeFactura();
		this.apiMail = apiMail;
		this.apiMsg = apiMsg;
		
		
		this.suscriptores = new ArrayList<>(List.of(generadorDeFacturas));
		
	}
	
	public Pedido newPedido(DatosCliente datosCliente ) {
		Pedido pedido = new Pedido(this);
        pedido.subscribeObserver(this.generadorDeFacturas);
        pedido.subscribeObserver(new NotificadorDeEmail(datosCliente.getEmail(), apiMail));
        pedido.subscribeObserver(new Fidelizador(datosCliente.getTelefono(), apiMsg));
        
        this.pedidos.add(pedido);
		return pedido;
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
		//FALTA!!
	}
	
	public List<Factura> getFacturas() {
		return generadorDeFacturas.getFacturas(); 
	}
	
	public List<ResumenVentaItem> cantidadDeVentasYPreciosPromedioPorItem(){
		//Guardo en una lista todos los DetalleFacturableItem de todas mis facturas generadas. El flaten es para pasar de una "lista de listas" a una "lista"
		List<DetalleFacturableItem> todosLosDetalles = generadorDeFacturas.getFacturas().stream()
	            																		.flatMap(f -> f.getDetalles().stream())
	            																		.toList();
		
		// recorro mi lista de items preguntandole a la lista auxiliar de DetalleFacturableItem los detalles de cada item de mi catalogo: cantidadVendida, 
		// totalBase, totalFinal, para despues calcular los promedios y generar un ResumenVentaItem que guarda toda esa información 
		return itemsDelCatalogo.stream()
	            .map(item -> {
	            				// me quedo con los DetalleFacturableItem del item que estoy consultando en este momento
				                List<DetalleFacturableItem> detallesDelItem = todosLosDetalles.stream()
				                        													  .filter(d -> d.getItem().equals(item))
				                        													  .toList(); 
				                // a esa lista de DetalleFacturableItem filtrada le consulto la cantidad vendida haciendo la suma
				                Integer cantidadVendida = detallesDelItem.stream()
												                         .mapToInt( dItem -> dItem.getCantidadVendida())
												                         .sum();
				                
    				            // a esa lista de DetalleFacturableItem filtrada le consulto el precio base de cada venta y lo sumo
				                Double totalBase = detallesDelItem.stream()
				                        						  .mapToDouble(d -> d.getPrecioTotalBase())
				                        						  .sum();
				                
				               // a esa lista de DetalleFacturableItem filtrada le consulto el precio final de cada venta y lo sumo
				                Double totalFinal = detallesDelItem.stream()
				                        						   .mapToDouble(d -> d.getPrecioTotalFinal())
				                        						   .sum();
				                
				                // calculo los promedios a partir de la info de arriba, salvando la posibilidad de que un producto puede no haberse vendido nunca
				                Double promedioBase  = cantidadVendida > 0 ? totalBase  / cantidadVendida : 0.0;
				                Double promedioFinal = cantidadVendida > 0 ? totalFinal / cantidadVendida : 0.0; 
				                
				                // genero el resumen de ese item
				                return new ResumenVentaItem(item, cantidadVendida, promedioBase, promedioFinal); 
				            })
	            // ordena de manera descendente segun el criterio que yo quiero. En este caso por cantidad de ventas de cada item
	            .sorted(Comparator.comparing( (ResumenVentaItem r) -> r.getCantidadVendida() ).reversed()) // lo ordena de mayor a menor
	            .toList();
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


