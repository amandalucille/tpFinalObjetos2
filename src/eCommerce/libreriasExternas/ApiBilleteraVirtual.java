package eCommerce.libreriasExternas;

public interface ApiBilleteraVirtual {
	public Boolean saldoSuficiente(Double monto);
	
	public void bloquearMonto(Double monto);
	
	public void pagar(Double monto);
}
