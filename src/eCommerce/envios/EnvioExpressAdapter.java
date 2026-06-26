package eCommerce.envios;

import eCommerce.Pedido;
import eCommerce.libreriasExternas.*;


public class EnvioExpressAdapter implements MetodoDeEnvio{
	
	
	public float calcularCostoDeEnvio(Pedido pedido) {
		float precio = pedido.valorTotalPedido().floatValue();
		
		return EnvioExpress.calcularCosto(precio);
	}
	public String estimacionDeDias() {
		return "Tu pedido llegará en un día hábil";
	}
	
	
}
