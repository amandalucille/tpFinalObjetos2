package eCommerce.envios;

import eCommerce.Pedido;
import eCommerce.libreriasExternas.CorreoArgentina;

public class EnvioEstandarAdapter implements MetodoDeEnvio{
	
	public float calcularCostoDeEnvio(Pedido pedido) {
		float peso = pedido.getPeso().floatValue();
		return CorreoArgentina.estimarEnvio(peso,pedido.getDireccion());
	}
	public String estimacionDeDias() {
		return "Tu pedido llegará entre 5 y 7 días hábiles";
	}
}

