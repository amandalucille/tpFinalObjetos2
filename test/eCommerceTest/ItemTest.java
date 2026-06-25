package eCommerceTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import eCommerce.item.*;


public class ItemTest  extends setUp{
	

    @Test
    public void testPrecioFinalSinDescuento() {
        assertEquals(1000.0, termo.getPrecioFinal());
    }

    @Test
    public void testPrecioFinalConDescuento() {
        assertEquals(850.0, mate.getPrecioFinal());
    }

    @Test
    public void testDeValidezDeProducto() {
     	
        RuntimeException exSinSKU = assertThrows(RuntimeException.class, () -> new Producto(null, "Remera", "Remera de algodón", "Nike", "Ropa", 500.0, 2,9.0));
        assertEquals("No es un producto válido", exSinSKU.getMessage());
        
        RuntimeException exSKUIsBlank = assertThrows(RuntimeException.class, () -> new Producto("  ", "Remera", "Remera de algodón", "Nike", "Ropa", 500.0, 2,9.0));
        assertEquals("No es un producto válido", exSKUIsBlank.getMessage());
        
        RuntimeException exSinNombre = assertThrows(RuntimeException.class, () -> new Producto("1", null, "Remera de algodón", "Nike", "Ropa", 500.0, 3,10.0));
        assertEquals("No es un producto válido", exSinNombre.getMessage());
        
        RuntimeException exNombreIsBlank = assertThrows(RuntimeException.class, () -> new Producto("1", "  ", "Remera de algodón", "Nike", "Ropa", 500.0, 3,10.0));
        assertEquals("No es un producto válido", exNombreIsBlank.getMessage());
        
        RuntimeException exSinDescripcion= assertThrows(RuntimeException.class, () -> new Producto("2", "Remera", null, "Nike", "Ropa", 500.0, 4,16.0));
        assertEquals("No es un producto válido", exSinDescripcion.getMessage());
        
        
        RuntimeException exDescripcionIsBlank= assertThrows(RuntimeException.class, () -> new Producto("2", "Remera", "  ", "Nike", "Ropa", 500.0, 4,16.0));
        assertEquals("No es un producto válido", exDescripcionIsBlank.getMessage());
        
        RuntimeException exSinMarca= assertThrows(RuntimeException.class, () -> new Producto("3", "Remera", "Remera de algodón", null, "Ropa", 500.0, 5,2.4));
        assertEquals("No es un producto válido", exSinMarca.getMessage());
        
        RuntimeException exMarcaIsBlank= assertThrows(RuntimeException.class, () -> new Producto("3", "Remera", "Remera de algodón", "  ", "Ropa", 500.0, 5,2.4));
        assertEquals("No es un producto válido", exMarcaIsBlank.getMessage());
        
        RuntimeException exSinCategoria= assertThrows(RuntimeException.class, () -> new Producto("4", "Remera", "Remera de algodón", "Nike", null, 500.0, 6,12.4));
        assertEquals("No es un producto válido", exSinCategoria.getMessage());
        
        RuntimeException exCategoriaIsBlank = assertThrows(RuntimeException.class, () -> new Producto("4", "Remera", "Remera de algodón", "Nike", "  ", 500.0, 6, 12.4));
        assertEquals("No es un producto válido", exCategoriaIsBlank.getMessage());
        
        RuntimeException exPrecioNegativo = assertThrows(RuntimeException.class, () -> new Producto("5", "Remera", "Remera de algodón", "Nike", "Ropa", -10.0, 7,5.76));
        assertEquals("No es un producto válido", exPrecioNegativo.getMessage());
        
        RuntimeException exPrecioNull = assertThrows(RuntimeException.class, () -> new Producto("5", "Remera", "Remera de algodón", "Nike", "Ropa", null, 7,5.76));
        assertEquals("No es un producto válido", exPrecioNull.getMessage());
        
        RuntimeException exStockNegativo = assertThrows(RuntimeException.class, () -> new Producto("5", "Remera", "Remera de algodón", "Nike", "Ropa", 10.0, -3,10.5));
        assertEquals("No es un producto válido", exStockNegativo.getMessage());
        
        RuntimeException exStockNull = assertThrows(RuntimeException.class, () -> new Producto("5", "Remera", "Remera de algodón", "Nike", "Ropa", 10.0, null,10.5));
        assertEquals("No es un producto válido", exStockNull.getMessage());
        
        RuntimeException exPesoNegativo = assertThrows(RuntimeException.class, () -> new Producto("5", "Remera", "Remera de algodón", "Nike", "Ropa", 10.0, 7,-10.5));
        assertEquals("No es un producto válido", exPesoNegativo.getMessage());
        
        RuntimeException exPesoNull = assertThrows(RuntimeException.class, () -> new Producto("5", "Remera", "Remera de algodón", "Nike", "Ropa", 10.0, 7,null));
        assertEquals("No es un producto válido", exPesoNull.getMessage());
        
    }

    @Test
    public void testDePaqueteDeProductos() {
    	
    	assertEquals(8755.0, packAudioMovil.getPrecioFinal() );
    	
    	
    	packAudioMovil.removeItem(auriculares);
    	assertEquals(2300.0, packAudioMovil.getPrecioBase() );
    	
    	packAudioMovil.addItem(auriculares);
    		
        
        assertEquals(11555.0, paqueteHomeOffice.getPrecioBase() );
        assertEquals(11555.0, paqueteHomeOffice.getPrecioFinal() );
    
        
    }
    
    @Test
    public void testNombresYDescripcion() {
    	assertEquals("Notebook", notebook.getNombre());
    	assertEquals("Computadora", notebook.getDescripcion());  
    	
    	assertEquals("Pack Audio Móvil", packAudioMovil.getNombre());
    	assertEquals("Paquete de accesorios", packAudioMovil.getDescripcion());  
	
    }
    
    @Test
    public void testValidezDePaquete() {

        RuntimeException exSinNombre = assertThrows(RuntimeException.class, () -> new Paquete(null,"Paquete pruebas", 9));
        assertEquals("No es un Paquete válido", exSinNombre.getMessage());
        
        RuntimeException exNombreIsBlank = assertThrows(RuntimeException.class, () -> new Paquete("","Paquete pruebas", 9));
        assertEquals("No es un Paquete válido", exNombreIsBlank.getMessage());
        
        RuntimeException exSinDescripcion = assertThrows(RuntimeException.class, () -> new Paquete("Paquete pruebas", null, 9));
        assertEquals("No es un Paquete válido", exSinDescripcion.getMessage());
        
        RuntimeException exDescripcionIsBlank = assertThrows(RuntimeException.class, () -> new Paquete("Paquete pruebas","", 9));
        assertEquals("No es un Paquete válido", exDescripcionIsBlank.getMessage());
        
        RuntimeException exSinStock= assertThrows(RuntimeException.class, () -> new Paquete("Paquete pruebas", "Paquete prueba", null));
        assertEquals("No es un Paquete válido", exSinStock.getMessage());
        
        RuntimeException exStockNegativo = assertThrows(RuntimeException.class, () -> new Paquete("Paquete pruebas", "Paquete prueba", -5));
        assertEquals("No es un Paquete válido", exStockNegativo.getMessage());
        
    }

    @Test
    public void testDecrementarStockInvalido() {
    	RuntimeException exSinStockProducto = assertThrows(RuntimeException.class, () -> productoSinStock.decrementarStock());
        assertEquals("Sin stock", exSinStockProducto.getMessage());
        
        RuntimeException exSinStockPaquete = assertThrows(RuntimeException.class, () -> paqueteSinStock.decrementarStock());
        assertEquals("Sin stock", exSinStockPaquete.getMessage());
        
    
    }

    @Test
    public void testGetPeso() {
    	assertEquals(3.0,termo.getPeso());
    	assertEquals(13.0, packAudioMovil.getPeso());
    	
    }
    
    @Test
    public void testAumentarStock() {
    	termo.aumentarStock();
    	packAudioMovil.aumentarStock();
    }
    
    @Test
    public void testAtributosDinamicosRepetidos() {
    	//invento dos atributos dinamicos iguales
    	Atributo<Boolean> tecladoEsp = new Atributo<Boolean>("Teclado Español", true);
    	Atributo<Boolean> tecladoEsp2 = new Atributo<Boolean>("Teclado Español", true);
    		
    	//veo que efectivamente ninguno de los dos esta como atributo de la notebook
    	assertFalse(notebook.getAtributos().contains(tecladoEsp)); 
    	assertFalse(notebook.getAtributos().contains(tecladoEsp2));

    	//agrego uno de los dos y veo que ahora me va a decir como si los 2 estuvieran xq el equals que nosotras hicimos, compara no soo por instancia, sino que tambien por nombre
    	
    	
    	notebook.agregarAtributo(tecladoEsp);
    	assertTrue(notebook.getAtributos().contains(tecladoEsp));
    	assertTrue(notebook.getAtributos().contains(tecladoEsp2));

    	
    	//Por mas que hayan dos instancias gemelas de un atributo, por como redefinimos el equals,
    	//al ser identicas, si tengo una, tengo la otra también. 
    	
    	
        
    	
    }
    
}