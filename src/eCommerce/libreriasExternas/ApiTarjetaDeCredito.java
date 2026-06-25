package eCommerce.libreriasExternas;

public interface ApiTarjetaDeCredito {

	void validarDatos(Integer nroTarjeta, Integer cvv, String fechaDeVencimiento);

	void preAutorizar(Double monto, Integer nroTarjeta);

	void transferir(Double monto, Integer nroTarjeta);

}
