package eCommerce.metodoDePago;

import eCommerce.Pedido;
import eCommerce.comprobantes.Comprobante;
import eCommerce.errores.PagoRechazadoException;
import eCommerce.libreriasExternas.ApiTarjetaDeCredito;

public class PagoConTarjeta extends MetodoDePago {
	
	private Integer nroTarjeta;
	private Integer cvv;
	private String fechaDeVencimiento;
	private ApiTarjetaDeCredito apiTarjeta;
	
	
	public PagoConTarjeta(Integer nroTarjeta, Integer cvv, String fechaDeVencimiento, ApiTarjetaDeCredito apiTarjeta) {
		
		this.nroTarjeta = nroTarjeta;
		this.cvv = cvv;
		this.fechaDeVencimiento = fechaDeVencimiento;
		this.apiTarjeta = apiTarjeta;
	}
	@Override
	public void validarDatos(Pedido pedido) {
		Boolean datosValidos = this.apiTarjeta.validarDatos(nroTarjeta, cvv, fechaDeVencimiento);
		if (!datosValidos) {
			throw new PagoRechazadoException("Los datos de la tarjeta de crédito son inválidos.");
		}		
	}
	@Override
	public void reservarFondos(Pedido pedido) {
		Boolean fondosReservados = this.apiTarjeta.preAutorizar(pedido.montoTotalAPagar(), nroTarjeta);
		
		if (!fondosReservados) {
			throw new PagoRechazadoException("La tarjeta no tiene límite suficiente o el banco rechazó la pre-autorización.");
		}
	}
	@Override
	public void ejecutarTransaccion(Pedido pedido) {
		super.setDatosTransaccion(this.apiTarjeta.transferirYNotificar(pedido.montoTotalAPagar(),nroTarjeta));
	}
		
}
