package eCommerceTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import eCommerce.Pedido;
import eCommerce.envios.*;
import eCommerce.errores.*;
import eCommerce.item.*;
@ExtendWith(MockitoExtension.class)
class ErroresTest extends SetUp {
	 
	@Test
	public void noSePuedeComprarUnaCantidadMayorAlStockDisponible() {
		// mate tiene stock 1 . Intento que rena compre 50 mates - no hay stock
		Pedido pedido = mercadoLibre.newPedido(rena);
		pedido.addItem(mate, 50);
		
		StockInsuficienteException ex = assertThrows(StockInsuficienteException.class,() -> pedido.confirmarPedido());
		assertEquals("No hay stock suficiente de Mate, quedan 1", ex.getMessage());
		assertEquals("Mate", ex.getNombreItem());
		assertEquals(1, ex.getStockDisponible());
		assertEquals(1, mate.stockDisponible());
	}
 
	@Test
	public void noSePuedeConfirmarUnPedidoConUnProductoQueSeQuedoSinStock() {
		Pedido pedido = mercadoLibre.newPedido(rena);
		pedido.addItem(productoSinStock, 1);
 
		StockInsuficienteException ex = assertThrows(StockInsuficienteException.class,	() ->  pedido.confirmarPedido());
 
		assertEquals("Notebook", ex.getNombreItem());
		assertEquals(0, ex.getStockDisponible());
	}
 
	@Test
	public void noSePuedeDarDeAltaUnProductoConDatosIncompletosEnElCatalogo() {
		ItemInvalidoException ex = assertThrows(ItemInvalidoException.class,() -> new Producto(null, "Zapatillas", "Zapatillas urbanas", "Adidas", "Calzado", 25000.0, 10, 0.9));
 
		assertEquals("El ítem 'Zapatillas' es inválido: El SKU no puede ser nulo o vacío.", ex.getMessage());
		assertEquals("Zapatillas", ex.getNombreItem());
	}
 
	@Test
	public void noSePuedeConsultarUnAtributoQueElProductoNoTiene() {
		AtributoNoEncontradoException ex = assertThrows(AtributoNoEncontradoException.class,() -> auriculares.obtenerAtributo("Color"));
 
		assertEquals("El atributo 'Color' no existe", ex.getMessage());
	}
 
	@Test
	public void noSePuedeEtiquetarUnProductoConUnAtributoSinValorAsignado() {
		AtributoInvalidoException ex = assertThrows(AtributoInvalidoException.class,() -> teclado.agregarAtributo(new Atributo<Boolean>("Inalámbrico", null)));
 
		assertEquals("El atributo 'Inalámbrico' debe tener un valor asignado.", ex.getMessage());
	}
 
	@Test
	public void noSePuedeSacarDelPedidoUnItemQueNuncaSeAgrego() {
		ItemNoEncontradoException ex = assertThrows(ItemNoEncontradoException.class,() -> pedidoAmi.removeItem(termo));
 
		assertEquals("El ítem que intentas remover no se encuentra en este pedido.", ex.getMessage());
	}
 
	@Test
	public void noSePuedeSacarUnItemDeUnPedidoTodaviaVacio() {
		PedidoVacioException ex = assertThrows(PedidoVacioException.class,() -> pedidoVacioRena.removeItem(mouse));
 
		assertEquals("No se pueden quitar ítems: el pedido actual está vacío.", ex.getMessage());
	}
 
	@Test
	public void seRechazaLaCompraSiLaTarjetaDeCreditoTieneDatosInvalidos() {
		Direccion direccion = new Direccion("Calle 123");
		MetodoDeEnvio envioMock = mock(MetodoDeEnvio.class); 
 
		when(apiTCMock.validarDatos(12345678, 123, "1/3/2027")).thenReturn(false);
 
		pedidoAmi.confirmarPedido();
 
		PagoRechazadoException ex = assertThrows(PagoRechazadoException.class,() -> pedidoAmi.prepararPedido(envioMock, pagoTJAmi, direccion));
 
		assertEquals("Los datos de la tarjeta de crédito son inválidos.", ex.getMessage());
		verify(apiTCMock, never()).transferirYNotificar(anyDouble(), anyInt());
	}
 
	@Test
	public void seRechazaLaCompraSiLaBilleteraVirtualNoTieneSaldo() {
		Direccion direccion = new Direccion("Sucursal Centro");
		MetodoDeEnvio envioMock = mock(MetodoDeEnvio.class);
		when(envioMock.calcularCostoDeEnvio(pedidoConPaqueteAzu)).thenReturn(0f);
 
		when(apiBVMock.saldoSuficiente(pedidoConPaqueteAzu.montoTotalItems())).thenReturn(false);
 
		pedidoConPaqueteAzu.confirmarPedido();
 
		PagoRechazadoException ex = assertThrows(PagoRechazadoException.class,() -> pedidoConPaqueteAzu.prepararPedido(envioMock, pagoBVAzu, direccion));
 
		assertEquals("Fondos insuficientes en la Billetera Virtual para abonar el pedido.", ex.getMessage());
		verify(apiBVMock, never()).pagar(anyDouble());
	}
 
	@Test
	public void noSePuedeMarcarComoEnviadoUnPedidoQueTodaviaNoFuePreparado() {
		TransicionDeEstadoInvalidaException ex = assertThrows(TransicionDeEstadoInvalidaException.class,() -> pedidoRena.enviarPedido());
 
		assertEquals("Operación inválida: No se puede 'enviar el pedido' en el estado actual.", ex.getMessage());
	}
 
	@Test
	public void noSePuedeAgregarItemsAUnPedidoYaConfirmado() {
		pedidoAmi.confirmarPedido();
 
		TransicionDeEstadoInvalidaException ex = assertThrows(TransicionDeEstadoInvalidaException.class,() -> pedidoAmi.addItem(termo, 1));
 
		assertEquals("Operación inválida: No se puede 'agregar o quitar ítems' en el estado actual.", ex.getMessage());
	}
 
	@Test
	public void noSePuedeConsultarLaFacturaDeUnPedidoQueTodaviaNoFueEntregado() {
		NoTieneFacturaException ex = assertThrows(NoTieneFacturaException.class,() -> mercadoLibre.getFactura(pedidoAmi));
 
		assertEquals("No existe factura para este pedido", ex.getMessage());
	}
}