package eCommerce.suscriptores;

import eCommerce.Pedido;

public class Fidelizador implements PedidoObserver {
	public void notificarPedidoConfirmado(){}
	
	public void notificarPedidoEnviado(){}
	
	public void notificarPedidoEntregado(Pedido pedido){}
	
	public void notificarPedidoCancelado() {}


}
