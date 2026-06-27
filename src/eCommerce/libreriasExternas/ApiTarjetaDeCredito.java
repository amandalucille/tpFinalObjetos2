package eCommerce.libreriasExternas;


public interface ApiTarjetaDeCredito {

	public void validarDatos(Integer nroTarjeta, Integer cvv, String fechaDeVencimiento);

	public void preAutorizar(Double monto, Integer nroTarjeta);

	public String transferir(Double monto, Integer nroTarjeta);

}
