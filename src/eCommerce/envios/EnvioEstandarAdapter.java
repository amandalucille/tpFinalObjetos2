package eCommerce.envios;

import eCommerce.Pedido;
import eCommerce.libreriasExternas.CorreoArgentina;
import eCommerce.libreriasExternas.EnvioExpress;

public class EnvioEstandarAdapter implements MetodoDeEnvio{
	private CorreoArgentina apiEnvio;
	
	public EnvioEstandarAdapter(CorreoArgentina apiEnvio) {
		this.apiEnvio = apiEnvio;
	}
	@Override
	public float calcularCostoDeEnvio(Pedido pedido) {
		float peso = pedido.getPeso().floatValue();
		return apiEnvio.estimarEnvio(peso,pedido.getDireccion());
	}

	@Override
	public String estimacionDeDias() {
		return "Tu pedido llegará entre 5 y 7 días hábiles";
	}
}

