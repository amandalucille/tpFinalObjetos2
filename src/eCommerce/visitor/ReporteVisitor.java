package eCommerce.visitor;

import eCommerce.item.Paquete;
import eCommerce.item.Producto;

public interface ReporteVisitor {

	public void visitar(Producto producto);
	public void visitar(Paquete paquete);
}
