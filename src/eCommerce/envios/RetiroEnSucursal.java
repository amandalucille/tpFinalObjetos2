package eCommerce.envios;

import eCommerce.Pedido;
import eCommerce.libreriasExternas.Sucursal;

public class RetiroEnSucursal implements MetodoDeEnvio{

	private Sucursal sucursal;
	
	public RetiroEnSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}
	
	@Override
	public float calcularCostoDeEnvio(Pedido pedido) {
		return 0;
	}

	@Override
	public String estimacionDeDias() {
		
		return "Tu pedido" + (sucursal.hayStockLocal()? "está en el local":"llegará en 3 días");
	}
	
}
