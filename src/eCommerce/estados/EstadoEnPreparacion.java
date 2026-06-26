package eCommerce.estados;

import eCommerce.Pedido;

public class EstadoEnPreparacion extends Estado {
	//Trancisiones validas: ENVIADO / CANCELADO
	
	
	@Override
	public void enviar(Pedido pedido){
		// esto tendría que llamar también a los envios?
		pedido.setEstado(new EstadoEnviado());
		
	}

	@Override
	public void cancelar(Pedido pedido){
		pedido.devolverStock();
		pedido.devolverCostoItemsYEnvio();
		
		pedido.setEstado(new EstadoCancelado());
	}
	
	
}
