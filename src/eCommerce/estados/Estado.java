package eCommerce.estados;

import eCommerce.Pedido;
import eCommerce.errores.TransicionDeEstadoInvalidaException;

public abstract class Estado {
	
	protected void errorDeTransicion(String operacion) {
		throw new TransicionDeEstadoInvalidaException(
			"Operación inválida: No se puede '" + operacion + "' en el estado actual."
		);
	}
	
	public void validarSiPuedoAgregarOSacarItems() {
		errorDeTransicion("agregar o quitar ítems");
	}
	
	public void confirmar(Pedido pedido) {
		errorDeTransicion("confirmar el pedido");
	 }
	public void enPreparacion(Pedido pedido){
		errorDeTransicion("pasar a preparación");
	 }
	public void enviar(Pedido pedido){
		errorDeTransicion("enviar el pedido");
	 }
	public void entregado(Pedido pedido){
		errorDeTransicion("entregar el pedido");
	 }
	public void cancelar(Pedido pedido){
		errorDeTransicion("cancelar el pedido");
	 }

}
