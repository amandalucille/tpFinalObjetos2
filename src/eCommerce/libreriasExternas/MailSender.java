package eCommerce.libreriasExternas;

public interface MailSender {
	public void enviarMail(String direcciónDestino, String titulo, String mensaje, String adjunto);

}
