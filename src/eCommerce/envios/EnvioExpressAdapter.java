package eCommerce.envios;
import eCommerce.Pedido;
import eCommerce.libreriasExternas.*;


public class EnvioExpressAdapter implements MetodoDeEnvio{
	
	private EnvioExpress apiEnvio;
	
	public EnvioExpressAdapter(EnvioExpress apiEnvio) {
		this.apiEnvio = apiEnvio;

	}
	@Override
	public float calcularCostoDeEnvio(Pedido pedido) {
		float precio = pedido.montoTotalItems().floatValue();
		
		return apiEnvio.calcularCosto(precio);
	}
	
	@Override
	public String estimacionDeDias() {
		return "Tu pedido llegará en un día hábil";
	}
	
	
}
