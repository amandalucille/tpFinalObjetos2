package eCommerce;


public abstract class Estado {

	protected RuntimeException errorDeTransicion = new RuntimeException("Acción inválida");
	
	public boolean sePuedeAgregarProducto() {
		return false;				//Se inicializa false para todos.
	}
	
	public void confirmar(Pedido pedido) {
		 throw errorDeTransicion;
	 }
	public void enPreparacion(Pedido pedido){
		 throw errorDeTransicion;
	 }
	public void enviar(Pedido pedido){
		 throw errorDeTransicion;
	 }
	public void entregado(Pedido pedido){
		 throw errorDeTransicion;
	 }
	public void cancelar(Pedido pedido){
		 throw errorDeTransicion;
	 }

}
