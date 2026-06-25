package eCommerce.libreriasExternas;

public interface ApiTransferenciaBancaria {

	public void validarDatos(String cbu, String alias);

	public void transferir(String cbu, Double montoPedido);

}
