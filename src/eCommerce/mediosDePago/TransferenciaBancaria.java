package eCommerce.mediosDePago;

import eCommerce.libreriasExternas.ApiTransferenciaBancaria;

public class TransferenciaBancaria extends MetodoDePago {
	private String cbu;
	private String alias;
	private ApiTransferenciaBancaria apiTransferencia;
	
	public TransferenciaBancaria(String cbu, String alias, ApiTransferenciaBancaria api) {
		this.cbu = cbu;
		this.alias = alias;
		this.apiTransferencia = api;
	}

	@Override
	public void validarDatos() {
		apiTransferencia.validarDatos(cbu, alias);
		
	}

	@Override
	public void reservarFondos(Double montoPedido) {	
	}

	@Override
	public void ejecutarTransaccion(Double montoPedido) {
		apiTransferencia.transferir(cbu,montoPedido);
		
	}

}
