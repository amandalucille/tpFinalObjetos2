package eCommerce.metodoDePago;

import eCommerce.Pedido;
import eCommerce.comprobantes.Comprobante;
import eCommerce.libreriasExternas.ApiTransferenciaBancaria;

public class PagoConTransferencia extends MetodoDePago {
	private String nroComprobante;
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
		
	}
	@Override
	public void ejecutarTransaccion(Pedido pedido) {
		this.nroComprobante = this.apiTransferencia.transferir(cbu,pedido.montoTotalAPagar()); 
		this.setComprobante(new Comprobante(nroComprobante)); 
	}
	
}
