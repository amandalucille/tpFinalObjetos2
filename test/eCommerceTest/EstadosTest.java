package eCommerceTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import eCommerce.Pedido;
import eCommerce.errores.TransicionDeEstadoInvalidaException;
import eCommerce.estados.Estado;
import eCommerce.estados.EstadoBorrador;
import eCommerce.estados.EstadoCancelado;
import eCommerce.estados.EstadoConfirmado;
import eCommerce.estados.EstadoEnPreparacion;
import eCommerce.estados.EstadoEntregado;
import eCommerce.estados.EstadoEnviado;


class EstadosTest extends SetUp {

	@Test
	public void testTransicionesEstadoBorrador() {
		Estado estadoBorrador = new EstadoBorrador();
		Pedido pedidoMock = mock(Pedido.class);

		
		// validarSiPuedoAgregarOSacarItems() no lanza excepción
		assertDoesNotThrow(() -> estadoBorrador.validarSiPuedoAgregarOSacarItems());

		// confirmar() -> pasa a EstadoConfirmado
		estadoBorrador.confirmar(pedidoMock);
		// un objeto que "atrapa" (captura) el argumento real que se usó en la llamada
		ArgumentCaptor<Estado> captorConfirmar = ArgumentCaptor.forClass(Estado.class);
		verify(pedidoMock).setEstado(captorConfirmar.capture());
		assertInstanceOf(EstadoConfirmado.class, captorConfirmar.getValue());

		// cancelar() -> pasa a EstadoCancelado, y NO devuelve stock ni plata
		// (desde Borrador nunca se decrementó nada)
		Pedido otroPedidoMock = mock(Pedido.class);
		estadoBorrador.cancelar(otroPedidoMock);
		ArgumentCaptor<Estado> captorCancelar = ArgumentCaptor.forClass(Estado.class);
		verify(otroPedidoMock).setEstado(captorCancelar.capture());
		assertInstanceOf(EstadoCancelado.class, captorCancelar.getValue());
		verify(otroPedidoMock, never()).devolverStock();
		verify(otroPedidoMock, never()).devolverCostoItems();
		verify(otroPedidoMock, never()).devolverCostoItemsYEnvio();

		// transiciones inválidas
		Pedido pedidoInvalidoMock = mock(Pedido.class);

		assertThrows(TransicionDeEstadoInvalidaException.class,
				() -> estadoBorrador.enPreparacion(pedidoInvalidoMock));

		assertThrows(TransicionDeEstadoInvalidaException.class,
				() -> estadoBorrador.enviar(pedidoInvalidoMock));

		assertThrows(TransicionDeEstadoInvalidaException.class,
				() -> estadoBorrador.entregado(pedidoInvalidoMock));

		// Confirmamos que ninguna de las 3 tocó al pedido de ninguna forma
		verifyNoInteractions(pedidoInvalidoMock);
	}
	
	@Test
	public void testTransicionesEstadoConfirmado() {
		Estado estadoConfirmado = new EstadoConfirmado();

		// validarSiPuedoAgregarOSacarItems() lanza excepción (hereda el comportamiento base)
		assertThrows(TransicionDeEstadoInvalidaException.class,
				() -> estadoConfirmado.validarSiPuedoAgregarOSacarItems());

		// enPreparacion() -> pasa a EstadoEnPreparacion
		Pedido pedidoMock = mock(Pedido.class);
		estadoConfirmado.enPreparacion(pedidoMock);
		ArgumentCaptor<Estado> captorEnPreparacion = ArgumentCaptor.forClass(Estado.class);
		verify(pedidoMock).setEstado(captorEnPreparacion.capture());
		assertInstanceOf(EstadoEnPreparacion.class, captorEnPreparacion.getValue());

		// cancelar() -> pasa a EstadoCancelado, devuelve stock, pero NO devuelve costos
		Pedido otroPedidoMock = mock(Pedido.class);
		estadoConfirmado.cancelar(otroPedidoMock);
		ArgumentCaptor<Estado> captorCancelar = ArgumentCaptor.forClass(Estado.class);
		verify(otroPedidoMock).setEstado(captorCancelar.capture());
		assertInstanceOf(EstadoCancelado.class, captorCancelar.getValue());
		verify(otroPedidoMock).devolverStock();
		//
		verify(otroPedidoMock, never()).devolverCostoItems();
		verify(otroPedidoMock, never()).devolverCostoItemsYEnvio();

		// transiciones inválidas
		Pedido pedidoInvalidoMock = mock(Pedido.class);

		assertThrows(TransicionDeEstadoInvalidaException.class,
				() -> estadoConfirmado.confirmar(pedidoInvalidoMock));

		assertThrows(TransicionDeEstadoInvalidaException.class,
				() -> estadoConfirmado.enviar(pedidoInvalidoMock));

		assertThrows(TransicionDeEstadoInvalidaException.class,
				() -> estadoConfirmado.entregado(pedidoInvalidoMock));

		verifyNoInteractions(pedidoInvalidoMock);
	}
	
	@Test
	public void testTransicionesEstadoEnPreparacion() {
		Estado estadoEnPreparacion = new EstadoEnPreparacion();

		// validarSiPuedoAgregarOSacarItems() lanza excepción
		assertThrows(TransicionDeEstadoInvalidaException.class,
				() -> estadoEnPreparacion.validarSiPuedoAgregarOSacarItems());

		// enviar() -> pasa a EstadoEnviado
		Pedido pedidoMock = mock(Pedido.class);
		estadoEnPreparacion.enviar(pedidoMock);
		ArgumentCaptor<Estado> captorEnviar = ArgumentCaptor.forClass(Estado.class);
		verify(pedidoMock).setEstado(captorEnviar.capture());
		assertInstanceOf(EstadoEnviado.class, captorEnviar.getValue());

		// cancelar() -> pasa a EstadoCancelado, devuelve stock Y costo de items + envío
		Pedido otroPedidoMock = mock(Pedido.class);
		estadoEnPreparacion.cancelar(otroPedidoMock);
		ArgumentCaptor<Estado> captorCancelar = ArgumentCaptor.forClass(Estado.class);
		verify(otroPedidoMock).setEstado(captorCancelar.capture());
		assertInstanceOf(EstadoCancelado.class, captorCancelar.getValue());
		verify(otroPedidoMock).devolverStock();
		verify(otroPedidoMock).devolverCostoItemsYEnvio();
		verify(otroPedidoMock, never()).devolverCostoItems();

		// transiciones inválidas
		Pedido pedidoInvalidoMock = mock(Pedido.class);

		assertThrows(TransicionDeEstadoInvalidaException.class,
				() -> estadoEnPreparacion.confirmar(pedidoInvalidoMock));

		assertThrows(TransicionDeEstadoInvalidaException.class,
				() -> estadoEnPreparacion.enPreparacion(pedidoInvalidoMock));

		assertThrows(TransicionDeEstadoInvalidaException.class,
				() -> estadoEnPreparacion.entregado(pedidoInvalidoMock));

		verifyNoInteractions(pedidoInvalidoMock);
	}
	
	@Test
	public void testTransicionesEstadoEnviado() {
		Estado estadoEnviado = new EstadoEnviado();

		// 19) validarSiPuedoAgregarOSacarItems() lanza excepción
		assertThrows(TransicionDeEstadoInvalidaException.class,
				() -> estadoEnviado.validarSiPuedoAgregarOSacarItems());

		// 20) entregado() -> pasa a EstadoEntregado
		Pedido pedidoMock = mock(Pedido.class);
		estadoEnviado.entregado(pedidoMock);
		ArgumentCaptor<Estado> captorEntregado = ArgumentCaptor.forClass(Estado.class);
		verify(pedidoMock).setEstado(captorEntregado.capture());
		assertInstanceOf(EstadoEntregado.class, captorEntregado.getValue());

		// 21) cancelar() -> pasa a EstadoCancelado, devuelve stock Y costo de items, NUNCA envío
		Pedido otroPedidoMock = mock(Pedido.class);
		estadoEnviado.cancelar(otroPedidoMock);
		ArgumentCaptor<Estado> captorCancelar = ArgumentCaptor.forClass(Estado.class);
		verify(otroPedidoMock).setEstado(captorCancelar.capture());
		assertInstanceOf(EstadoCancelado.class, captorCancelar.getValue());
		verify(otroPedidoMock).devolverStock();
		verify(otroPedidoMock).devolverCostoItems();
		verify(otroPedidoMock, never()).devolverCostoItemsYEnvio();

		// 22), 23), 24) transiciones inválidas
		Pedido pedidoInvalidoMock = mock(Pedido.class);

		assertThrows(TransicionDeEstadoInvalidaException.class,
				() -> estadoEnviado.confirmar(pedidoInvalidoMock));

		assertThrows(TransicionDeEstadoInvalidaException.class,
				() -> estadoEnviado.enPreparacion(pedidoInvalidoMock));

		assertThrows(TransicionDeEstadoInvalidaException.class,
				() -> estadoEnviado.enviar(pedidoInvalidoMock));

		verifyNoInteractions(pedidoInvalidoMock);
	}
	
	@Test
	public void testTransicionesEstadoEntregado() {
		Estado estadoEntregado = new EstadoEntregado();
		Pedido pedidoMock = mock(Pedido.class);

		assertThrows(TransicionDeEstadoInvalidaException.class,
				() -> estadoEntregado.validarSiPuedoAgregarOSacarItems());

		assertThrows(TransicionDeEstadoInvalidaException.class,
				() -> estadoEntregado.confirmar(pedidoMock));

		assertThrows(TransicionDeEstadoInvalidaException.class,
				() -> estadoEntregado.enPreparacion(pedidoMock));

		assertThrows(TransicionDeEstadoInvalidaException.class,
				() -> estadoEntregado.enviar(pedidoMock));

		assertThrows(TransicionDeEstadoInvalidaException.class,
				() -> estadoEntregado.entregado(pedidoMock));

		assertThrows(TransicionDeEstadoInvalidaException.class,
				() -> estadoEntregado.cancelar(pedidoMock));

		verifyNoInteractions(pedidoMock);
	}

	@Test
	public void testTransicionesEstadoCancelado() {
		Estado estadoCancelado = new EstadoCancelado();
		Pedido pedidoMock = mock(Pedido.class);

		assertThrows(TransicionDeEstadoInvalidaException.class,
				() -> estadoCancelado.validarSiPuedoAgregarOSacarItems());

		assertThrows(TransicionDeEstadoInvalidaException.class,
				() -> estadoCancelado.confirmar(pedidoMock));

		assertThrows(TransicionDeEstadoInvalidaException.class,
				() -> estadoCancelado.enPreparacion(pedidoMock));

		assertThrows(TransicionDeEstadoInvalidaException.class,
				() -> estadoCancelado.enviar(pedidoMock));

		assertThrows(TransicionDeEstadoInvalidaException.class,
				() -> estadoCancelado.entregado(pedidoMock));

		assertThrows(TransicionDeEstadoInvalidaException.class,
				() -> estadoCancelado.cancelar(pedidoMock));

		verifyNoInteractions(pedidoMock);
	}

}