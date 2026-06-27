package eCommerce.libreriasExternas;

public interface ApiBilleteraVirtual {
	public Boolean saldoSuficiente(Double monto);
	
	public void bloquearMonto(Double monto);
	
	public String pagar(Double monto);
	
	public void sendNotificacionPush(String nroTransaccion);
}
