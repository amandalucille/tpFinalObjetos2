package eCommerceTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


import eCommerce.estados.EstadoCancelado;
import eCommerce.estados.EstadoConfirmado;
import eCommerce.estados.EstadoEnPreparacion;
import eCommerce.estados.EstadoEntregado;
import eCommerce.estados.EstadoEnviado;

class PedidoTest  extends SetUp{

	@Test
	public void testRemoveItemDePedido() {
		RuntimeException exNoEstaEnElPedido = assertThrows(RuntimeException.class, () -> pedido1.removeItem(auriculares));
        assertEquals("No tenes este item en el pedido.", exNoEstaEnElPedido.getMessage());
        
        RuntimeException exNoHayItems = assertThrows(RuntimeException.class, () -> pedidoVacio.removeItem(auriculares));
        assertEquals("No hay items en el pedido.", exNoHayItems.getMessage());
        
        pedido1.removeItem(mouse);
    }
	
	@Test
	public void testNoEsValidoAgregarProducto() {
		
		pedido1.setEstado(new EstadoCancelado());
	
		RuntimeException exNoPuedeAgregar = assertThrows(RuntimeException.class, () -> pedido1.addItem(auriculares,1));
        assertEquals("No podes agregar item.", exNoPuedeAgregar.getMessage());
        
        
        pedido1.setEstado(new EstadoConfirmado());
    	
		RuntimeException exNoPuedeAgregar2 = assertThrows(RuntimeException.class, () -> pedido1.addItem(auriculares,2));
        assertEquals("No podes agregar item.", exNoPuedeAgregar2.getMessage());
        
        
        pedido1.setEstado(new EstadoEnPreparacion());
    	
		RuntimeException exNoPuedeAgregar3 = assertThrows(RuntimeException.class, () -> pedido1.addItem(auriculares,3));
        assertEquals("No podes agregar item.", exNoPuedeAgregar3.getMessage());
        
        
        pedido1.setEstado(new EstadoEnviado());
    	
		RuntimeException exNoPuedeAgregar4 = assertThrows(RuntimeException.class, () -> pedido1.addItem(auriculares,4));
        assertEquals("No podes agregar item.", exNoPuedeAgregar4.getMessage());
        
        pedido1.setEstado(new EstadoEntregado());
    	
		RuntimeException exNoPuedeAgregar5 = assertThrows(RuntimeException.class, () -> pedido1.addItem(auriculares,5));
        assertEquals("No podes agregar item.", exNoPuedeAgregar5.getMessage());
        
		
	}
	

}
