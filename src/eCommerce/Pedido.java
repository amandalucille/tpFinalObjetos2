package eCommerce;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eCommerce.comprobantes.*;
import eCommerce.envios.*;
import eCommerce.errores.*;
import eCommerce.estados.*;
import eCommerce.item.ItemCatalogo;
import eCommerce.metodoDePago.*;
import eCommerce.suscriptores.PedidoObserver;

public class Pedido {
	
	private Estado estado;
	private Map<ItemCatalogo, Integer> items;
	private MetodoDeEnvio metodoEnvio;
	private MetodoDePago metodoPago;
	private Direccion direccionDeEnvio;
	private List<PedidoObserver> suscriptores;
	private NotaDeCredito notaDeCredito;
	
	public Pedido(ECommerce eCommerce) {
		this.estado 	  = new EstadoBorrador();
		this.items		  = new HashMap<ItemCatalogo, Integer>();
		this.suscriptores = new ArrayList<>();
	}
	
	public void addItem(ItemCatalogo item, Integer cantidad) {
		estado.validarSiPuedoAgregarOSacarItems(); 
		//Si ya existia en el map el item que estoy agregando, recupero el stock con getOrDefault y le sumo la cantidad pasada por parametros
		//si no existia getOrDefault te devuelve 0, y le sumo la cantidad. 
				
		this.items.put(item, this.items.getOrDefault(item, 0) + cantidad);
	}
	
	public Map<ItemCatalogo, Integer> getItems(){
		return this.items;
	}
	
	public void removeItem(ItemCatalogo item) {
		if(items.isEmpty()) {
			throw new PedidoVacioException("No se pueden quitar ítems: el pedido actual está vacío.");
		}
		
		if(!items.containsKey(item)) {
			throw new ItemNoEncontradoException("El ítem que intentas remover no se encuentra en este pedido.");			
		}
		
		estado.validarSiPuedoAgregarOSacarItems();
		this.items.remove(item);
	}

	public void prepararPedido(MetodoDeEnvio metodoDeEnvio, MetodoDePago metodoDePago, Direccion direccion) {
		setMetodoDeEnvio(metodoDeEnvio);
		setMetodoDePago(metodoDePago);
		setDireccionDeEnvio(direccion);
		this.estado.enPreparacion(this);
		metodoDePago.procesarPago(this);

	}
	
	public void confirmarPedido() {
		this.validarStockItems(); //Antes de cambiar de estado, tiene que validarse que haya stock de los items seleccionados. 
		this.estado.confirmar(this);	 
		this.decrementarStock();
		this.suscriptores.forEach(o -> o.notificarPedidoConfirmado());
	}
	
	public void validarStockItems() {
		items.forEach((item,cantidad)-> {
			if (!item.hayStock(cantidad)){
				throw new StockInsuficienteException(item.getNombre(),item.stockDisponible());
			}
		});
	}
	
	public void enviarPedido() {
		this.estado.enviar(this); 
		this.suscriptores.forEach(o -> o.notificarPedidoEnviado());
	}
	
	public void entregarPedido() {
		this.estado.entregado(this); 
		this.suscriptores.forEach(o -> o.notificarPedidoEntregado(this));
	}
	
	public void cancelarPedido() {
		this.estado.cancelar(this); 
		 this.suscriptores.forEach(o -> o.notificarPedidoCancelado());
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
	
	public Double montoTotalItems() {
		//La interfaz dada pide que el tipo sea float, pero manejamos todo con double entonces cuando lo necesitemos, podemos hacer .floatValue().
		return this.items.entrySet()
				   .stream()
				   .mapToDouble(itemCant -> itemCant.getKey().getPrecioFinal() * itemCant.getValue())
				   .sum();
	}
	
	public Double montoTotalAPagar() {
		return this.montoTotalItems() + this.montoEnvio();
	}
	
	public Float montoEnvio() {
		return this.metodoEnvio.calcularCostoDeEnvio(this);
	}
	
	public void setMetodoDeEnvio(MetodoDeEnvio metodo) {
		this.metodoEnvio = metodo;
	}
	
	public void setMetodoDePago(MetodoDePago metodoDePago) {
		this.metodoPago = metodoDePago;
	}
	
	public void setDireccionDeEnvio(Direccion direccion) {
		this.direccionDeEnvio = direccion;
	}
	
	public Direccion getDireccion() {
		return this.direccionDeEnvio;
	}
	
	public Double getPeso() {
		return this.items.entrySet()
				   .stream()
				   .mapToDouble(itemCant -> itemCant.getKey().getPeso() * itemCant.getValue())
				   .sum();
	}
	
	public void devolverCostoItems() {
		devolverMonto(this.montoTotalItems());
	}
	
	public void devolverCostoItemsYEnvio() {
		devolverMonto(this.montoTotalItems() + this.metodoEnvio.calcularCostoDeEnvio(this));
	}
	
	public void devolverMonto(Double monto) {
		this.notaDeCredito = new NotaDeCredito( monto, this);
		
	}

	public void subscribeObserver(PedidoObserver observer) {
		this.suscriptores.add(observer);
	}

	public void desubscribirObserver(PedidoObserver observer) {
		this.suscriptores.remove(observer);
	}
	
	public NotaDeCredito getNotaDeCredito() {
		return this.notaDeCredito; 
	}
	
	public Comprobante getComprobante() {
		return this.metodoPago != null ? this.metodoPago.getComprobante() : null;
	}
	public Boolean esVentaExitosa() {
		return this.notaDeCredito == null;
	}
	public Integer cantidadVendidaDe(ItemCatalogo item) {
		return this.items.getOrDefault(item, 0);
	}
	
}
