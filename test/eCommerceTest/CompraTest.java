package eCommerceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import eCommerce.comprobantes.Factura;
import eCommerce.envios.Direccion;
import eCommerce.envios.EnvioEstandarAdapter;
import eCommerce.envios.EnvioExpressAdapter;
import eCommerce.envios.MetodoDeEnvio;
import eCommerce.envios.RetiroEnSucursal;

@ExtendWith(MockitoExtension.class)
public class CompraTest extends SetUp {
	@Test
	public void sePuedeSacarUnItemDeUnPedidoEnBorrador() {
	    assertEquals(2, pedidoAmi.getItems().size()); // mouse + teclado

	    pedidoAmi.removeItem(mouse);

	    assertEquals(1, pedidoAmi.getItems().size());
	    assertFalse(pedidoAmi.getItems().containsKey(mouse));
	    assertTrue(pedidoAmi.getItems().containsKey(teclado));
	}
	
	@Test
	public void elCostoDeEnvioEstandarDeUnPedidoConPaqueteUsaElPesoTotalDeSusItems() {
	    Direccion direccion = new Direccion("Calle 123");
	    MetodoDeEnvio envioEstandar = new EnvioEstandarAdapter(correoArgentinaMock);

	    when(correoArgentinaMock.estimarEnvio(35.8f, direccion)).thenReturn(150f);

	    Double montoAPagar = packAudioMovil.getPrecioFinal() + 150.0; // items + envío

	    when(apiBVMock.saldoSuficiente(montoAPagar)).thenReturn(true);
	    when(apiBVMock.pagar(montoAPagar)).thenReturn("004");

	    // Borrador -> Confirmado
	    pedidoConPaqueteAzu.confirmarPedido();

	    // Confirmado -> EnPreparacion (calcula el envío real, dispara Paquete.getPeso())
	    pedidoConPaqueteAzu.prepararPedido(envioEstandar, pagoBVAzu, direccion);

	    verify(correoArgentinaMock, times(3)).estimarEnvio(35.8f, direccion);
	   
	}
	
	@Test
	public void compraExitosaConTarjetaEnvioEstandar() {
		when(apiTCMock.validarDatos(12345678, 123, "1/3/2027")).thenReturn(true);
		when(apiTCMock.preAutorizar(4900d, 12345678)).thenReturn(true);
		when(apiTCMock.transferirYNotificar(4900d, 12345678)).thenReturn("001");
		Direccion direccion = new Direccion("Calle 123");
		when(correoArgentinaMock.estimarEnvio(15.4f, direccion)).thenReturn(100f);
		
		MetodoDeEnvio envioEstandar =  new EnvioEstandarAdapter(correoArgentinaMock);
		
		// Borrador -> Confirmado
		pedidoAmi.confirmarPedido();

		// Confirmado -> EnPreparacion (dispara el pago con tarjeta)
		pedidoAmi.prepararPedido(envioEstandar, pagoTJAmi, direccion);
		verify(apiTCMock).transferirYNotificar(4900d, 12345678); // chequeo si hubo un llamado a la api

		// EnPreparacion -> Enviado
		pedidoAmi.enviarPedido();
		assertEquals(0, mercadoLibre.getFacturas().size()); // chequeo que ml no tiene facturas para despues chequear
															// que se haya generado una.

		// Enviado -> Entregado (se genera la Factura)
		pedidoAmi.entregarPedido();

		assertTrue(pedidoAmi.esVentaExitosa());
		assertNull(pedidoAmi.getNotaDeCredito());
		assertNotNull(pedidoAmi.getComprobante());

		assertEquals(1, mercadoLibre.getFacturas().size()); // chequea que ahora ml tiene una factura
		Factura facturaAmi = mercadoLibre.getFactura(pedidoAmi);
		assertEquals(2, facturaAmi.getDetalles().size()); // mouse + teclado

		verify(apiMailSenderMock, times(1)).enviarMail("ami@gmail.com", "Pedido confirmado", "Tu pedido ha sido confirmado", null);
		verify(apiMailSenderMock, times(1)).enviarMail("ami@gmail.com", "Pedido enviado", "Tu pedido ha sido enviado", null);
		verify(apiMailSenderMock, times(1)).enviarMail("ami@gmail.com","Pedido entregado", "Tu pedido ha sido entregado", null);
		
		assertEquals(4900.0,pedidoAmi.montoTotalAPagar());
		
		assertEquals(4900.0,facturaAmi.totalFacturado());
	}
	@Test
	public void renaCancelaPedidoLuegoDeEnviado() {
		Direccion direccion = new Direccion("Calle Falsa 456");
 
		// EnvioExpressAdapter calcula el costo a partir de pedido.montoTotalItems().floatValue(),
		// así que uso ese mismo valor (en vez de un literal a mano) para que matchee exacto.
		float montoItemsFloat = mate.getPrecioFinal().floatValue();
		MetodoDeEnvio envioExpress = new EnvioExpressAdapter(envioExpressMock);
 
		when(envioExpressMock.calcularCosto(montoItemsFloat)).thenReturn(200f);
		when(apiTransferenciaMock.validarDatos("1234567", "")).thenReturn(true);
 
		Double montoAPagar = mate.getPrecioFinal() + 200.0; // items + envío
		when(apiTransferenciaMock.transferirYNotificar("1234567", montoAPagar)).thenReturn("003");
 
		// Borrador -> Confirmado
		pedidoRena.confirmarPedido();
 
		// Confirmado -> EnPreparacion (dispara el pago con transferencia)
		pedidoRena.prepararPedido(envioExpress, pagoTransRena, direccion);
		verify(apiTransferenciaMock).transferirYNotificar("1234567", montoAPagar);
		assertNotNull(pedidoRena.getComprobante());
		assertEquals(0, mercadoLibre.getFacturas().size());
 
		// EnPreparacion -> Enviado
		pedidoRena.enviarPedido();
		assertEquals(0, mercadoLibre.getNotasDeCredito().size()); // todavía no se canceló nada
 
		// Enviado -> Cancelado (reembolsa solo items, no el envío que ya se efectuó)
		pedidoRena.cancelarPedido();
 
		assertFalse(pedidoRena.esVentaExitosa());
		assertEquals(mate.getPrecioFinal(), pedidoRena.getNotaDeCredito().getMonto());
		assertEquals(1, mercadoLibre.getNotasDeCredito().size());
		assertEquals(0, mercadoLibre.getFacturas().size()); // nunca se entregó, no hay factura
	}
	@Test
	public void azuCancelaPedidoConPaqueteEnPreparacion() {
		Direccion direccion = new Direccion("Sucursal Centro");
		MetodoDeEnvio retiroEnSucursal = new RetiroEnSucursal(sucursalMock);
 
		// El retiro en sucursal no suma costo de envío, así que el total a pagar es
		// exactamente el precio final del paquete. Lo pido al objeto en vez de
		// hardcodearlo porque packAudioMovil tiene descuento porcentual.
		Double montoPedido = packAudioMovil.getPrecioFinal();
 
		when(apiBVMock.saldoSuficiente(montoPedido)).thenReturn(true);
		when(apiBVMock.pagar(montoPedido)).thenReturn("002");
 
		// Borrador -> Confirmado
		pedidoConPaqueteAzu.confirmarPedido();
 
		// Confirmado -> EnPreparacion (dispara el pago con billetera virtual)
		pedidoConPaqueteAzu.prepararPedido(retiroEnSucursal, pagoBVAzu, direccion);
		verify(apiBVMock).pagar(montoPedido); // chequeo que se haya llamado a la api
		verify(apiBVMock).sendNotificacionPush("002");
 
		assertEquals(0, mercadoLibre.getNotasDeCredito().size()); // todavía no se canceló nada
 
		// EnPreparacion -> Cancelado (devuelve stock, reembolsa items + envío)
		pedidoConPaqueteAzu.cancelarPedido();
 
		assertFalse(pedidoConPaqueteAzu.esVentaExitosa());
		assertEquals(montoPedido, pedidoConPaqueteAzu.getNotaDeCredito().getMonto());
		assertEquals(1, mercadoLibre.getNotasDeCredito().size());
 
		verify(apiMsgSenderMock, times(1)).enviarMensaje(123254543,
				"¡¡Volve y te regalamos el cupon 1234 para que obtengas un 5% de descuento aplicable en todos los productos de la tienda!!");
 
		assertEquals(0, mercadoLibre.getFacturas().size()); // nunca se entregó, no hay factura
	}
	
	
}
