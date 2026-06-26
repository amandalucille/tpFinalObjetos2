package eCommerce.mediosDePago;

import eCommerce.libreriasExternas.ApiTransferenciaBancaria;

public class PagoConTransferenciaBancaria extends MetodoDePago {

	private ApiTransferenciaBancaria apiTransferencia;
	
	public PagoConTransferenciaBancaria( ApiTransferenciaBancaria api) {

		this.apiTransferencia = api;
	}

	@Override
	public void validarDatos(DatosDePago datos) {
		DatosTransferencia losDatos = (DatosTransferencia) datos;
		apiTransferencia.validarDatos(losDatos.getCBU(), losDatos.getAlias());
		
	}

	@Override
	public void reservarFondos(DatosDePago datos, Double montoPedido) {	
	}

	@Override
	public void ejecutarTransaccion(DatosDePago datos, Double montoPedido) {
		DatosTransferencia losDatos = (DatosTransferencia) datos;
		apiTransferencia.transferir(losDatos.getCBU(),montoPedido);
		
	}

}
