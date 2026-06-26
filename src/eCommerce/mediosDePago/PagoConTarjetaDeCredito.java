package eCommerce.mediosDePago;

import eCommerce.libreriasExternas.ApiTarjetaDeCredito;

public class PagoConTarjetaDeCredito extends MetodoDePago {
	

	private ApiTarjetaDeCredito apiTarjeta;
	
	public PagoConTarjetaDeCredito(ApiTarjetaDeCredito api) {
		this.apiTarjeta = api;
	}
	
	@Override
	public void validarDatos(DatosDePago datos) {
		DatosTarjeta losDatos = (DatosTarjeta) datos;
		apiTarjeta.validarDatos(losDatos.getNroTarjeta(), losDatos.getCVV(), losDatos.getFechaDeVencimiento());
	}

	@Override
	public void reservarFondos(DatosDePago datos, Double monto) {
		DatosTarjeta losDatos = (DatosTarjeta) datos;
		apiTarjeta.preAutorizar(monto, losDatos.getNroTarjeta());
		
	}

	@Override
	public void ejecutarTransaccion(DatosDePago datos, Double monto) {
		DatosTarjeta losDatos = (DatosTarjeta) datos;
		apiTarjeta.transferir(monto, losDatos.getNroTarjeta());
		
	}

	
}
