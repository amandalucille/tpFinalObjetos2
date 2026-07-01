package eCommerce.visitor;

import eCommerce.ResumenVentaItem;
import eCommerce.item.Paquete;
import eCommerce.item.Producto;

public class ReporteCSV implements ReporteVisitor {

	private StringBuilder contenido = new StringBuilder("Nombre,CantidadVendida,PrecioPromedioBase,PrecioPromedioFinal\n");
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
        contenido.append(String.format("%s,%d,%.2f,%.2f%n",
                nombre,
                resumenActual.getCantidadVendida(),
                resumenActual.getPrecioPromedioBase(),
                resumenActual.getPrecioPromedioFinal()));
    }

    @Override
    public String obtenerReporte() {
        return contenido.toString();
    }

}
