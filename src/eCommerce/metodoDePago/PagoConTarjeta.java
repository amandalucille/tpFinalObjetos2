package eCommerce.metodoDePago;

import eCommerce.Pedido;
import eCommerce.comprobantes.Comprobante;
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
		this.apiTarjeta.validarDatos(nroTarjeta, cvv, fechaDeVencimiento);
		
	}
	@Override
	public void reservarFondos(Pedido pedido) {
		this.apiTarjeta.preAutorizar(pedido.montoTotalAPagar(), nroTarjeta);
		
	}
	@Override
	public void ejecutarTransaccion(Pedido pedido) {
		super.setDatosTransaccion(this.apiTarjeta.transferirYNotificar(pedido.montoTotalAPagar(),nroTarjeta));
	}
		
}
