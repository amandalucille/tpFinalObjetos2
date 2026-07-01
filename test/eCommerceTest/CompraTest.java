package eCommerceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import eCommerce.comprobantes.Factura;
import eCommerce.envios.Direccion;
import eCommerce.envios.EnvioEstandarAdapter;
import eCommerce.envios.MetodoDeEnvio;
import eCommerce.libreriasExternas.MailSender;

@ExtendWith(MockitoExtension.class)
public class CompraTest extends SetUp {
	@Test
	public void compraExitosaConTarjetaEnvioEstandar() {
		when(apiTCMock.validarDatos(12345678, 123, "1/3/2027")).thenReturn(true);
		when(apiTCMock.preAutorizar(anyDouble(), 12345678)).thenReturn(true);
		when(apiTCMock.transferirYNotificar(anyDouble(), 12345678)).thenReturn("TX-TARJETA-001");
		
		@Mock MetodoDeEnvio envioEstandar =  mock(EnvioEstandarAdapter.class);
		Direccion direccion = new Direccion("Calle 123");
		when(correoMock.estimarEnvio(anyFloat(), ))

		// Borrador -> Confirmado
		pedidoAmi.confirmarPedido();

		// Confirmado -> EnPreparacion (dispara el pago con tarjeta)
		pedidoAmi.prepararPedido(envioEstandar, pagoTJAmi, direccion);
		verify(apiTCMock).transferirYNotificar(anyDouble(), 12345678); // chequeo si hubo un llamado a la api

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

		verify(apiMailSenderMock, times(3)).enviarMail("ami@gmail.com", anyString(), anyString(), anyString());
	}

}
