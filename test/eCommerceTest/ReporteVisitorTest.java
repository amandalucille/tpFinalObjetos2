package eCommerceTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import eCommerce.envios.Direccion;
import eCommerce.envios.EnvioEstandarAdapter;
import eCommerce.envios.MetodoDeEnvio;
import eCommerce.envios.RetiroEnSucursal;
import eCommerce.visitor.ReporteCSV;
import eCommerce.visitor.ReporteHTML;
import eCommerce.visitor.ReporteTxt;

class ReporteVisitorTest extends SetUp {
	
	
	private void completarPedidoAmiConProductos() {
		when(apiTCMock.validarDatos(12345678, 123, "1/3/2027")).thenReturn(true);
		when(apiTCMock.preAutorizar(4900d, 12345678)).thenReturn(true);
		when(apiTCMock.transferirYNotificar(4900d, 12345678)).thenReturn("001");
		Direccion direccion = new Direccion("Calle 123");
		when(correoArgentinaMock.estimarEnvio(15.4f, direccion)).thenReturn(100f);

		MetodoDeEnvio envioEstandar = new EnvioEstandarAdapter(correoArgentinaMock);

		pedidoAmi.confirmarPedido();
		pedidoAmi.prepararPedido(envioEstandar, pagoTJAmi, direccion);
		pedidoAmi.enviarPedido();
		pedidoAmi.entregarPedido();
	}

	
	private void completarPedidoAzuConPaquete() {
		Double montoPedido = packAudioMovil.getPrecioFinal();

		when(apiBVMock.saldoSuficiente(montoPedido)).thenReturn(true);
		when(apiBVMock.pagar(montoPedido)).thenReturn("002");

		Direccion direccion = new Direccion("Sucursal Centro");
		MetodoDeEnvio retiroEnSucursal = new RetiroEnSucursal(sucursalMock);

		pedidoConPaqueteAzu.confirmarPedido();
		pedidoConPaqueteAzu.prepararPedido(retiroEnSucursal, pagoBVAzu, direccion);
		pedidoConPaqueteAzu.enviarPedido();
		pedidoConPaqueteAzu.entregarPedido();
	}

	@Test
	public void testReporteCSVConProductos() {
		completarPedidoAmiConProductos();

		String reporte = mercadoLibre.reporteDeProductosMasVendidos(new ReporteCSV());

		assertTrue(reporte.startsWith("Nombre,CantidadVendida,PrecioPromedioBase,PrecioPromedioFinal"));
		assertTrue(reporte.contains("Mouse"));
		assertTrue(reporte.contains("Teclado"));
	}

	@Test
	public void testReporteTxtConProductos() {
		completarPedidoAmiConProductos();

		String reporte = mercadoLibre.reporteDeProductosMasVendidos(new ReporteTxt());

		assertTrue(reporte.contains("Mouse"));
		assertTrue(reporte.contains("Vendidos:"));
	}

	@Test
	public void testReporteHTMLConProductos() {
		completarPedidoAmiConProductos();

		String reporte = mercadoLibre.reporteDeProductosMasVendidos(new ReporteHTML());

		assertTrue(reporte.startsWith("<table>"));
		assertTrue(reporte.contains("<td>Mouse</td>"));
		assertTrue(reporte.endsWith("</table>"));
	}

	@Test
	public void testReporteCSVConPaquete() {
		completarPedidoAzuConPaquete();

		String reporte = mercadoLibre.reporteDeProductosMasVendidos(new ReporteCSV());

		assertTrue(reporte.contains("[Paquete] Pack Audio Móvil"));
	}

	@Test
	public void testReporteTxtConPaquete() {
		completarPedidoAzuConPaquete();

		String reporte = mercadoLibre.reporteDeProductosMasVendidos(new ReporteTxt());

		assertTrue(reporte.contains("[Paquete] Pack Audio Móvil"));
	}

	@Test
	public void testReporteHTMLConPaquete() {
		completarPedidoAzuConPaquete();

		String reporte = mercadoLibre.reporteDeProductosMasVendidos(new ReporteHTML());

		assertTrue(reporte.contains("<td>[Paquete] Pack Audio Móvil</td>"));
	}

	@Test
	public void testReporteSinVentasTraeTodosLosItemsDelCatalogoEnCero() {
		// cantidadDeVentasYPreciosPromedioPorItem() arma un resumen por CADA item
		// del catálogo (haya vendido o no), así que sin entregas el reporte no
		// queda vacío: trae una fila por item, todas con cantidad 0 (la rama que
		// evita la división por cero en los promedios).
		String reporte = mercadoLibre.reporteDeProductosMasVendidos(new ReporteCSV());

		String[] lineas = reporte.split("\n");
		assertEquals("Nombre,CantidadVendida,PrecioPromedioBase,PrecioPromedioFinal", lineas[0]);
		assertEquals(10, lineas.length - 1); // los 10 items cargados en SetUp.armadoDeECommerce()

		assertTrue(reporte.contains("Mouse,0,0.00,0.00"));
		assertTrue(reporte.contains("[Paquete] Pack Audio Móvil,0,0.00,0.00"));
	}
}