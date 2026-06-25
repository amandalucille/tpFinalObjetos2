package eCommerce.envios;

import eCommerce.Pedido;
import eCommerce.libreriasExternas.Sucursal;

public class RetiroEnSucursal implements MetodoDeEnvio{

	private Sucursal sucursal;
	
	public RetiroEnSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}
	public float calcularCostoDeEnvio(Pedido pedido) {
		return 0;
	}

	public void estimacionDeDias() {
		
		System.out.println("Tu pedido" + (sucursal.hayStockLocal()? "está en el local":"llegará en 3 días"));
	}
	
}
