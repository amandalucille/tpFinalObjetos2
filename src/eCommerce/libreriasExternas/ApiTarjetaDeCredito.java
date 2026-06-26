package eCommerce.libreriasExternas;

import java.util.Date;

public interface ApiTarjetaDeCredito {

	void validarDatos(Integer nroTarjeta, Integer cvv, Date fechaDeVencimiento);

	void preAutorizar(Double monto, Integer nroTarjeta);

	void transferir(Double monto, Integer nroTarjeta);

}
