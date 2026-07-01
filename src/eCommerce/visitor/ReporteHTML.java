package eCommerce.visitor;

import eCommerce.ResumenVentaItem;
import eCommerce.item.Paquete;
import eCommerce.item.Producto;

public class ReporteHTML implements ReporteVisitor {

	private StringBuilder filas = new StringBuilder();
    private ResumenVentaItem resumenActual;

    @Override
    public void prepararResumen(ResumenVentaItem resumen) {
        this.resumenActual = resumen;
    }

    @Override
    public void visitar(Producto producto) {
        agregarFila(producto.getNombre());
    }

    @Override
    public void visitar(Paquete paquete) {
        agregarFila("[Paquete] " + paquete.getNombre());
    }

    private void agregarFila(String nombre) {
        filas.append(String.format(
                "<tr><td>%s</td><td>%d</td><td>$%.2f</td><td>$%.2f</td></tr>%n",
                nombre,
                resumenActual.getCantidadVendida(),
                resumenActual.getPrecioPromedioBase(),
                resumenActual.getPrecioPromedioFinal()));
    }

    @Override
    public String obtenerReporte() {
        return "<table>"
                + "<tr><th>Nombre</th><th>Vendidos</th><th>Prom. base</th><th>Prom. final</th></tr>"
                + filas.toString()
                + "</table>";
    }


}
