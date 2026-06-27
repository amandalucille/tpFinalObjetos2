package eCommerce.metodoDePago;

import eCommerce.Pedido;
import eCommerce.libreriasExternas.ApiBilleteraVirtual;

public class PagoConBilleteraVirtual extends MetodoDePago {
	private ApiBilleteraVirtual apiBV;
	
	public PagoConBilleteraVirtual(ApiBilleteraVirtual apiBV) {
		this.apiBV = apiBV;
	}
	
	@Override
	public void validarDatos(Pedido pedido) {
			this.apiBV.saldoSuficiente(pedido.montoTotalAPagar());
		
	}

	@Override
	public void reservarFondos(Pedido pedido) {
		this.apiBV.bloquearMonto(pedido.montoTotalAPagar());
		
	}

	@Override
	public void ejecutarTransaccion(Pedido pedido) {
		super.setDatosTransaccion(this.apiBV.pagar(pedido.montoTotalAPagar()));
		
	}
	@Override
	public void notificarResultado(Pedido pedido) {
		this.apiBV.sendNotificacionPush(super.getDatosTransaccion());
	}
}
