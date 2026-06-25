package eCommerce.estados;

import eCommerce.Pedido;

public class EstadoConfirmado extends Estado {

	//Trancisiones validas: EN PREPARACION / CANCELADO
	//el stock se decrementa desde borrador, antes de awsignar este estado al pedido!!

	
	@Override
	public void enPreparacion(Pedido pedido) {
		pedido.setEstado(new EstadoEnPreparacion());
	}
	
	@Override
	public void cancelar(Pedido pedido) {
		pedido.devolverStock();
		pedido.setEstado(new EstadoCancelado());
	}
	
}
