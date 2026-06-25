package eCommerce.envios;

import eCommerce.Pedido;

public interface MetodoDeEnvio {
	
	public float calcularCostoDeEnvio(Pedido pedido);
	public void estimacionDeDias(); 
	
}
