package eCommerce.suscriptores;

import eCommerce.libreriasExternas.MessageSender;

public class Fidelizador implements PedidoObserver {
	private Integer nroTelefono;
	private MessageSender api;

	public Fidelizador(Integer nroTelefono, MessageSender api) {
		this.nroTelefono = nroTelefono;
		this.api = api;
	}

	public void notificarPedidoCancelado(){
		this.api.enviarMensaje(this.nroTelefono, "¡¡Volve y te regalamos el cupon 1234 para que obtengas un 5% de descuento aplicable en todos los productos de la tienda!!");
	}


}
