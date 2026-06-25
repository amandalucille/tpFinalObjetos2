package eCommerceTest;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import eCommerce.item.*;


class AtributoTest extends setUp{
	
	
	@Test
    public void testAtributosDinamicosDeDiferentesTipos() {
        assertEquals(1.8, peso.getValor());
        assertEquals("Gris", color.getValor());
        assertTrue(esNuevo.getValor());
        
        assertEquals(1.8, notebook.obtenerAtributo("Peso"));
        assertTrue((Boolean) notebook.obtenerAtributo("Es Nuevo"));
        
        
        RuntimeException ex = assertThrows(RuntimeException.class, () -> notebook.obtenerAtributo("Alto"));
        assertEquals("El atributo 'Alto' no existe", ex.getMessage());
    }
	
	@Test
	public void testEqualsAtributo() { //
		assertTrue(peso.equals(peso));
		assertFalse(peso.equals(notebook));
		
    	Atributo<Boolean> tecladoEsp = new Atributo<Boolean>("Teclado Español", true);
    	Atributo<Boolean> tecladoEsp2 = new Atributo<Boolean>("Teclado Español", true);
    	
    	assertEquals(tecladoEsp, tecladoEsp2);
    	
    	//Se prueban en este test los tres casos posibles de que dos atributos sean iguales
	}


}
