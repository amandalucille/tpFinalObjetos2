package eCommerceTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;


import eCommerce.envios.*;
import eCommerce.estados.*;


class EstadosTest extends SetUp{

	@Test
	public void testTransicionesInvalidasDeEstadoBorrador() {
		
		MetodoDeEnvio envioMock = mock(MetodoDeEnvio.class);

		RuntimeException exPrepararPedido = assertThrows(RuntimeException.class, () -> pedido1.prepararPedido(envioMock));
        assertEquals("Acción inválida", exPrepararPedido.getMessage());
        
        RuntimeException exEnviarPedido = assertThrows(RuntimeException.class, () -> pedido1.enviarPedido());
        assertEquals("Acción inválida", exEnviarPedido.getMessage());
        
        RuntimeException exEntregarPedido = assertThrows(RuntimeException.class, () -> pedido1.entregarPedido());
        assertEquals("Acción inválida", exEntregarPedido.getMessage());
	}
	
	@Test
	public void testTransicionesInvalidasDeEstadoConfirmado() {
	
		//Trancisiones validas: EN PREPARACION / CANCELADO
		pedido1.setEstado(new EstadoConfirmado());
		
		RuntimeException exConfirmarPedido = assertThrows(RuntimeException.class, () -> pedido1.confirmarPedido()); // ya estoy en este estado
        assertEquals("Acción inválida", exConfirmarPedido.getMessage());
                
        RuntimeException exEnviarPedido = assertThrows(RuntimeException.class, () -> pedido1.enviarPedido());
        assertEquals("Acción inválida", exEnviarPedido.getMessage());
        
        RuntimeException exEntregarPedido = assertThrows(RuntimeException.class, () -> pedido1.entregarPedido());
        assertEquals("Acción inválida", exEntregarPedido.getMessage());
	}

	@Test
	public void testTransicionesInvalidasDeEstadoEnPreparacion() {
		//Trancisiones validas: ENVIADO / CANCELADO
		
		MetodoDeEnvio envioMock = mock(MetodoDeEnvio.class);

		pedido1.setEstado(new EstadoEnPreparacion());
		
		RuntimeException exConfirmarPedido = assertThrows(RuntimeException.class, () -> pedido1.confirmarPedido());
        assertEquals("Acción inválida", exConfirmarPedido.getMessage());
              
		RuntimeException exEnPreparacion = assertThrows(RuntimeException.class, () -> pedido1.prepararPedido(envioMock)); // ya estoy en este estado
        assertEquals("Acción inválida", exEnPreparacion.getMessage());
                
        RuntimeException exEntregarPedido = assertThrows(RuntimeException.class, () -> pedido1.entregarPedido());
        assertEquals("Acción inválida", exEntregarPedido.getMessage());
	}
	
	@Test
	public void testTransicionesInvalidasDeEstadoEnviado() {
		//Trancisiones validas: ENTREGADO / CANCELADO 
		MetodoDeEnvio envioMock = mock(MetodoDeEnvio.class);

		pedido1.setEstado(new EstadoEnviado());
		
		RuntimeException exConfirmarPedido = assertThrows(RuntimeException.class, () -> pedido1.confirmarPedido());
        assertEquals("Acción inválida", exConfirmarPedido.getMessage());
              
		RuntimeException exEnPreparacion = assertThrows(RuntimeException.class, () -> pedido1.prepararPedido(envioMock));
        assertEquals("Acción inválida", exEnPreparacion.getMessage());
                
        RuntimeException exEnviado = assertThrows(RuntimeException.class, () -> pedido1.enviarPedido()); // ya estoy en este estado
        assertEquals("Acción inválida", exEnviado.getMessage());
	}
	
	@Test
	public void testTransicionesInvalidasDeEstadoEntregado() {
		//Trancisiones validas: ninguna
		MetodoDeEnvio envioMock = mock(MetodoDeEnvio.class);

		pedido1.setEstado(new EstadoEntregado());
		
		RuntimeException exConfirmarPedido = assertThrows(RuntimeException.class, () -> pedido1.confirmarPedido());
        assertEquals("Acción inválida", exConfirmarPedido.getMessage());
              
		RuntimeException exEnPreparacion = assertThrows(RuntimeException.class, () -> pedido1.prepararPedido(envioMock));
        assertEquals("Acción inválida", exEnPreparacion.getMessage());
        
        RuntimeException exEnviarPedido = assertThrows(RuntimeException.class, () -> pedido1.enviarPedido());
        assertEquals("Acción inválida", exEnviarPedido.getMessage());
                        
        RuntimeException exEntregarPedido = assertThrows(RuntimeException.class, () -> pedido1.entregarPedido()); // ya estoy en este estado
        assertEquals("Acción inválida", exEntregarPedido.getMessage());
        
        RuntimeException exCancelar = assertThrows(RuntimeException.class, () -> pedido1.cancelarPedido());
        assertEquals("Acción inválida", exCancelar.getMessage());
        
        
        
	}

	@Test
	public void testTransicionesValidasDeEstadoBorrador() {
		pedido1.confirmarPedido();
		assertInstanceOf(EstadoConfirmado.class, pedido1.getEstado());
		
		pedido1.setEstado(new EstadoBorrador());
		pedido1.cancelarPedido();
		assertInstanceOf(EstadoCancelado.class, pedido1.getEstado());
	}
	
	@Test
	public void testTransicionesValidasDeEstadoConfirmado() {
		
		MetodoDeEnvio envioMock = mock(MetodoDeEnvio.class);

		pedido1.setEstado(new EstadoConfirmado());
		pedido1.prepararPedido(envioMock);
		assertInstanceOf(EstadoEnPreparacion.class, pedido1.getEstado());
		
		pedido1.setEstado(new EstadoConfirmado());
		pedido1.cancelarPedido();
		assertInstanceOf(EstadoCancelado.class, pedido1.getEstado());
	}
	
	@Test
	public void testTransicionesValidasDeEstadoEnPreparacion() {
		pedido1.setEstado(new EstadoEnPreparacion());
		pedido1.enviarPedido();
		assertInstanceOf(EstadoEnviado.class, pedido1.getEstado());
		
		pedido1.setEstado(new EstadoEnPreparacion());
		pedido1.cancelarPedido();
		assertInstanceOf(EstadoCancelado.class, pedido1.getEstado());
	}
	
	@Test
	public void testTransicionesValidasDeEstadoEnviado() {
		pedido1.setEstado(new EstadoEnviado());
		pedido1.entregarPedido();
		assertInstanceOf(EstadoEntregado.class, pedido1.getEstado());
		
		pedido1.setEstado(new EstadoEnviado());
		pedido1.cancelarPedido();
		assertInstanceOf(EstadoCancelado.class, pedido1.getEstado());
	}
	
	//Cancelado y Entregado son terminales, no tienen transiciones válidas
}
