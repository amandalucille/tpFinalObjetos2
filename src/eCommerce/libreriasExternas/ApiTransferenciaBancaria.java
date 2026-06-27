package eCommerce.libreriasExternas;


public interface ApiTransferenciaBancaria {

	public void validarDatos(String cbu, String alias);

	public String transferir(String cbu, Double montoPedido);
			
}
