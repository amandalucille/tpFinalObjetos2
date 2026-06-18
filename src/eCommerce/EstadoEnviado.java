package eCommerce;

public class EstadoEnviado extends Estado {

	//Trancisiones validas: ENTREGADO / CANCELADO 
	
	
	@Override
	public void entregado(Pedido pedido){
		pedido.setEstado(new EstadoEntregado());
	}

	@Override
	public void cancelar(Pedido pedido){
		pedido.devolverStock();
	//	pedido.devolverCosto(); 
		pedido.setEstado(new EstadoCancelado());
	}
}
