package eCommerce.libreriasExternas;

import eCommerce.envios.Direccion;

public interface CorreoArgentina {
	
	public float estimarEnvio(float peso, Direccion envio);
}
