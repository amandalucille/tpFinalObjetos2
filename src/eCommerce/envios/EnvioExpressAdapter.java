package eCommerce.envios;
import eCommerce.Pedido;
import eCommerce.libreriasExternas.*;


public class EnvioExpressAdapter implements MetodoDeEnvio{
	
	@Override
	public float calcularCostoDeEnvio(Pedido pedido) {
		float precio = pedido.montoTotalItems().floatValue();
		
		return EnvioExpress.calcularCosto(precio);
	}
	
	@Override
	public String estimacionDeDias() {
		return "Tu pedido llegará en un día hábil";
	}
	
	
}
