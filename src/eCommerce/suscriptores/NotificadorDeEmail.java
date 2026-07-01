package eCommerce.suscriptores;

import eCommerce.Pedido;
import eCommerce.libreriasExternas.MailSender;

public class NotificadorDeEmail implements PedidoObserver {
	private String email;
	private MailSender apiMail;
	
	public NotificadorDeEmail(String email, MailSender apiMail) {
		this.email = email;
		this.apiMail = apiMail;
	}

	@Override
	public void notificarPedidoConfirmado() {
		apiMail.enviarMail(email, "Pedido confirmado", "Tu pedido ha sido confirmado", null);
	}
	

	@Override
	public void notificarPedidoEnviado() {
		apiMail.enviarMail(email, "Pedido enviado", "Tu pedido ha sido enviado", null);
	}
	
	@Override
	public void notificarPedidoEntregado(Pedido pedido){
		apiMail.enviarMail(email, "Pedido entregado", "Tu pedido ha sido entregado", null);
	}
}
