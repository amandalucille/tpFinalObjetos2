package eCommerce.metodoDePago;

import eCommerce.Pedido;
import eCommerce.comprobantes.Comprobante;
import eCommerce.libreriasExternas.ApiTransferenciaBancaria;

public class PagoConTransferencia extends MetodoDePago {
	private String cbu;
	private String alias;
	private ApiTransferenciaBancaria apiTransferencia;
	
	public PagoConTransferencia(String cbu, String alias, ApiTransferenciaBancaria apiTransferencia) {
		this.cbu = cbu;
		this.alias = alias;
		this.apiTransferencia = apiTransferencia;
	}
	@Override
	public void validarDatos(Pedido pedido) {
		this.apiTransferencia.validarDatos(cbu,alias);
	}
	@Override
	public void reservarFondos(Pedido pedido) {
		//No se usa porque este método no reserva fondos.
	}
	@Override
	public void ejecutarTransaccion(Pedido pedido) {
		super.setDatosTransaccion(this.apiTransferencia.transferirYNotificar(cbu,pedido.montoTotalAPagar()));
	}
	
}
