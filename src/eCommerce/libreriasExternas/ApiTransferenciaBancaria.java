package eCommerce.libreriasExternas;


public interface ApiTransferenciaBancaria {

	public void validarDatos(String cbu, String alias);

	public String transferirYNotificar(String cbu, Double montoPedido);
			
}
