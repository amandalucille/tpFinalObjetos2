package eCommerce.mediosDePago;

import eCommerce.libreriasExternas.ApiTarjetaDeCredito;

public class TarjetaDeCredito extends MetodoDePago {
	
	private Integer nroTarjeta;
	private Integer cvv;
	private String fechaDeVencimiento;
	private ApiTarjetaDeCredito apiTarjeta;
	
	public TarjetaDeCredito(Integer nro, Integer cvv, String fecha, ApiTarjetaDeCredito api) {
		this.nroTarjeta = nro;
		this.cvv = cvv;
		this.fechaDeVencimiento = fecha;
		this.apiTarjeta = api;
				
	}
	
	@Override
	public void validarDatos() {
		apiTarjeta.validarDatos(nroTarjeta, cvv, fechaDeVencimiento);
	}

	@Override
	public void reservarFondos(Double monto) {
		apiTarjeta.preAutorizar(monto, nroTarjeta);
		
	}

	@Override
	public void ejecutarTransaccion(Double monto) {
		apiTarjeta.transferir(monto, nroTarjeta);
		
	}

	
}
