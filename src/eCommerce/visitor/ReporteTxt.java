package eCommerce.visitor;

import eCommerce.ResumenVentaItem;
import eCommerce.item.Paquete;
import eCommerce.item.Producto;

public class ReporteTxt implements ReporteVisitor {

	private StringBuilder contenido = new StringBuilder();
	private ResumenVentaItem resumenActual;

	@Override
	public void prepararResumen(ResumenVentaItem resumen) {
		this.resumenActual = resumen;
	}

	@Override
	public void visitar(Producto producto) {
		agregarLinea(producto.getNombre());
	}

	@Override
	public void visitar(Paquete paquete) {
		agregarLinea("[Paquete] " + paquete.getNombre());
	}

	private void agregarLinea(String nombre) {
		contenido.append(String.format("%-30s | Vendidos: %-5d | Prom. base: $%.2f | Prom. final: $%.2f%n",
				nombre, resumenActual.getCantidadVendida(),
				resumenActual.getPrecioPromedioBase(), resumenActual.getPrecioPromedioFinal()));
	}

	@Override
	public String obtenerReporte() {
		return contenido.toString();
	}

}
