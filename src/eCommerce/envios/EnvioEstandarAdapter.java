package eCommerce.envios;

import eCommerce.Pedido;
import eCommerce.libreriasExternas.CorreaArgentina;

public class EnvioEstandarAdapter implements MetodoDeEnvio{
	private Direccion direccion;
	
	public EnvioEstandarAdapter(Direccion direccion) {
		this.direccion = direccion;
	}
	
	public float calcularCostoDeEnvio(Pedido pedido) {
		float peso = pedido.getPeso().floatValue();
		return CorreaArgentina.estimarEnvio(peso,this.direccion);
	}
	public void estimacionDeDias() {
		System.out.println("Tu pedido llegará entre 5 y 7 días hábiles");
	}
	
}
