package eCommerce.suscriptores;

import eCommerce.Pedido;

public interface PedidoObserver {
	public default void notificarPedidoConfirmado(){}
	
	public default void notificarPedidoEnviado(){}
	
	public default void notificarPedidoEntregado(Pedido pedido){}
	
	public default void notificarPedidoCancelado(){}
}
