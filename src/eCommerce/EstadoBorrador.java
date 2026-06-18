package eCommerce;

public class EstadoBorrador extends Estado {
	
	//Trancisiones validas: CANCELADO / CONFIRMADO
	
	@Override
	public boolean sePuedeAgregarProducto(){
		return true;
		
	}
	
	@Override
	public void confirmar(Pedido pedido){
		pedido.decrementarStock();
		pedido.setEstado(new EstadoConfirmado());
		
		//Ver el orden de estos dos. Que pasa primero? 
		//Si se lanza una excepción porque no hay stock, el pedido no se confirma!
	}

	@Override
	public void cancelar(Pedido pedido){
		pedido.setEstado(new EstadoCancelado());
	}


}
