package eCommerce.metodoDePago;

import eCommerce.Pedido;
import eCommerce.comprobantes.Comprobante;
import eCommerce.errores.PagoRechazadoException;
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
		Boolean datosValidos = this.apiTransferencia.validarDatos(cbu, alias);
		if (!datosValidos) {
			throw new PagoRechazadoException("El CBU o Alias ingresado para la transferencia es inválido.");
		}
	}

	@Override
	public void reservarFondos(Pedido pedido) {
		// No se usa porque este método no reserva fondos.
	}

	@Override
	public void ejecutarTransaccion(Pedido pedido) {
		super.setDatosTransaccion(this.apiTransferencia.transferirYNotificar(cbu, pedido.montoTotalAPagar()));
	}

}
