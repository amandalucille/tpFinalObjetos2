package eCommerceTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import eCommerce.*;

public class ProductoTest {
	Producto termo;
	Producto mate;
	Producto sinSKU;
	Producto sinNombre;
	Producto sinDescripcion;
	Producto sinMarca;
	Producto sinCategoria;
	Producto sinPrecio;
	
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
			
	
	@BeforeEach
	public void Setup() {
		this.termo = new Producto("SKU-123", "Termo", "Termo acero", "Lumilagro", "Bazar", 1000.0);
        this.mate = new Producto("SKU-456", "Mate", "Mate de madera", "Urquiza", "Bazar", 1000.0, 15.0);
        this.sinSKU = new Producto(null, "Remera", "Remera de algodón", "Nike", "Ropa", 500.0);
        this.sinNombre = new Producto("1", null, "Remera de algodón", "Nike", "Ropa", 500.0);
    	this.sinDescripcion = new Producto("2", "Remera", null, "Nike", "Ropa", 500.0);
    	this.sinMarca = new Producto("3", "Remera", "Remera de algodón", null, "Ropa", 500.0);
    	this.sinPrecio = new Producto("4", "Remera", "Remera de algodón", "Nike", null, 500.0);
    	this.sinCategoria = new Producto("5", "Remera", "Remera de algodón", "Nike", "Ropa", null);
        
        
        this.notebook = new Producto("SKU-999", "Notebook", "Computadora", "Dell", "Tech", 150000.0);
        
        notebook.agregarAtributo(peso);
        notebook.agregarAtributo(color);
        notebook.agregarAtributo(esNuevo);
        notebook.agregarAtributo(pulgadas);
        
        this.auriculares = new Producto("999", "Auriculares Bluetooth", "auriculares inalambricos", "JBL", "Audio", 8000.0);
    	this.funda = new Producto("111", "Funda protectora", "Funda de silicona", "shein", "Accesorio", 1500.0);
    	this.cable = new Producto("222", "Cable USC-C", "cable de carga para celulares multiples marcas", "sony", "electronica", 800.0);
    	this.packAudioMovil = new Paquete("Pack Audio Móvil", "Paquete de accesorios", 15.0);
    	
    	packAudioMovil.addItem(auriculares);
    	packAudioMovil.addItem(funda);
    	packAudioMovil.addItem(cable);
    	
    	this.teclado = new Producto("333", "Teclado", "Teclado inalambrico", "genius", "perifericos", 2000.0);
    	this.mouse = new Producto("444", "Mouse", "Mouse inalambrico", "sony", "electronica", 800.0);
        mouse.agregarAtributo(color);
    	
        
    	this.paqueteHomeOffice = new Paquete("Kit Home Office", "Paquete de auto + mouse + teclado");
    			
        paqueteHomeOffice.addItem(teclado);
        paqueteHomeOffice.addItem(mouse);
        paqueteHomeOffice.addItem(packAudioMovil);
    	
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
        assertFalse(sinSKU.esProductoValido());
        assertFalse(sinNombre.esProductoValido());
        assertFalse(sinDescripcion.esProductoValido());
        assertFalse(sinMarca.esProductoValido());
        assertFalse(sinCategoria.esProductoValido());
        assertFalse(sinPrecio.esProductoValido());
        
        assertTrue(notebook.esProductoValido());
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