package eCommerce.visitor;

import java.util.List;

import eCommerce.ECommerce;
import eCommerce.ResumenVentaItem;

public class ReporteVentas {

	private ECommerce eCommerce;

	public ReporteVentas(ECommerce eCommerce) {
		this.eCommerce = eCommerce;
	}

	public String generarReporteDeMasVendidos(ReporteVisitor formato) {
		List<ResumenVentaItem> resumenes = eCommerce.cantidadDeVentasYPreciosPromedioPorItem();

		resumenes.forEach(resumen -> {
			formato.prepararResumen(resumen);      // le paso el contexto
			resumen.getItem().aceptar(formato);    // el item dispara el double dispatch por tipo
		});

		return formato.obtenerReporte();
	}

}
