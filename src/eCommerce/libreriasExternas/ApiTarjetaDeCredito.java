package eCommerce.libreriasExternas;


public interface ApiTarjetaDeCredito {

	public Boolean validarDatos(Integer nroTarjeta, Integer cvv, String fechaDeVencimiento);

	public Boolean preAutorizar(Double monto, Integer nroTarjeta);

	public String transferirYNotificar(Double monto, Integer nroTarjeta);

}
