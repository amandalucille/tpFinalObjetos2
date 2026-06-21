package eCommerceTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eCommerce.*;

public class ProductoTest {
	Producto termo;
	Producto mate;
	
	Producto notebook;
	AtributoDouble peso = new AtributoDouble("Peso", 1.8);
    AtributoString color = new AtributoString("Color", "Gris");
    AtributoBoolean esNuevo = new AtributoBoolean("Es Nuevo", true);
    AtributoInt pulgadas = new AtributoInt("Pulgadas", 16);
    Producto auriculares;
	Producto funda;
	Producto cable;
	Paquete packAudioMovil;
	Producto teclado;
	Producto mouse;    
	Paquete paqueteHomeOffice;
	Catalogo mercadoLibre;
	Pedido pedido1; 
	
	@BeforeEach
	public void Setup() {
		this.termo = new Producto("SKU-123", "Termo", "Termo acero", "Lumilagro", "Bazar", 1000.0, 0,3.0);
        this.mate = new Producto("SKU-456", "Mate", "Mate de madera", "Urquiza", "Bazar", 1000.0, 15.0, 1,4.5);
        this.notebook = new Producto("SKU-999", "Notebook", "Computadora", "Dell", "Tech", 150000.0, 100,2.0);
        
        notebook.agregarAtributo(peso);
        notebook.agregarAtributo(color);
        notebook.agregarAtributo(esNuevo);
        notebook.agregarAtributo(pulgadas);
        
        this.auriculares = new Producto("999", "Auriculares Bluetooth", "auriculares inalambricos", "JBL", "Audio", 8000.0, 10,2.2);
    	this.funda = new Producto("111", "Funda protectora", "Funda de silicona", "shein", "Accesorio", 1500.0, 9,5.4);
    	this.cable = new Producto("222", "Cable USC-C", "cable de carga para celulares multiples marcas", "sony", "electronica", 800.0, 8,5.4);
    	this.packAudioMovil = new Paquete("Pack Audio Móvil", "Paquete de accesorios", 15.0, 7);
    	
    	packAudioMovil.addItem(auriculares);
    	packAudioMovil.addItem(funda);
    	packAudioMovil.addItem(cable);
    	
    	this.teclado = new Producto("333", "Teclado", "Teclado inalambrico", "genius", "perifericos", 2000.0, 5,4.4);
    	this.mouse = new Producto("444", "Mouse", "Mouse inalambrico", "sony", "electronica", 800.0, 4,6.6);
        mouse.agregarAtributo(color);
    	
        
    	this.paqueteHomeOffice = new Paquete("Kit Home Office", "Paquete de auto + mouse + teclado", 3);
    			
        paqueteHomeOffice.addItem(teclado);
        paqueteHomeOffice.addItem(mouse);
        paqueteHomeOffice.addItem(packAudioMovil);
    	
        this.mercadoLibre = new Catalogo();
        this.mercadoLibre.agregarItem(auriculares);
        this.mercadoLibre.agregarItem(notebook);
        this.mercadoLibre.agregarItem(funda);
        this.mercadoLibre.agregarItem(termo);
        this.mercadoLibre.agregarItem(mate);
        this.mercadoLibre.agregarItem(mouse);
        this.mercadoLibre.agregarItem(cable);
        this.mercadoLibre.agregarItem(packAudioMovil);
        this.mercadoLibre.agregarItem(teclado);
        this.mercadoLibre.agregarItem(paqueteHomeOffice);
        
        this.pedido1 = new Pedido();
        this.pedido1.addItem(mouse);
        
	}

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
    
    
}