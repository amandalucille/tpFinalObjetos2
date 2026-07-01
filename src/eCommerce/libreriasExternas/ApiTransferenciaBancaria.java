package eCommerce.libreriasExternas;


public interface ApiTransferenciaBancaria {

	public Boolean validarDatos(String cbu, String alias);

	public String transferirYNotificar(String cbu, Double montoPedido);
			
}
