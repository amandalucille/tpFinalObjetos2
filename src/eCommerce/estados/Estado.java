package eCommerce.estados;

import eCommerce.Pedido;

public abstract class Estado {

//	protected RuntimeException errorDeTransicion = new RuntimeException("Acción inválida");
	
	public boolean sePuedeAgregarProducto() {
		return false;				//Se inicializa false para todos.
	}
	
	protected RuntimeException errorDeTransicion() {
	    return new RuntimeException("Acción inválida");
	}
	
	public void confirmar(Pedido pedido) {
		throw errorDeTransicion();
	 }
	public void enPreparacion(Pedido pedido){
		throw errorDeTransicion();
	 }
	public void enviar(Pedido pedido){
		throw errorDeTransicion();
	 }
	public void entregado(Pedido pedido){
		throw errorDeTransicion();
	 }
	public void cancelar(Pedido pedido){
		throw errorDeTransicion();
	 }

}
