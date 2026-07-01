package eCommerceTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import eCommerce.item.*;
import eCommerce.errores.ItemInvalidoException;
import eCommerce.errores.StockInsuficienteException;

public class ItemTest extends SetUp {
	
    @Test
    public void testPrecioFinalSinDescuento() {
        assertEquals(1000.0, termo.getPrecioFinal());
    }
    public void testNombresYDescripcion() {
        assertEquals("Notebook", notebook.getNombre());
        assertEquals("Computadora", notebook.getDescripcion());  
    	
        assertEquals("Pack Audio Móvil", packAudioMovil.getNombre());
        assertEquals("Paquete de accesorios", packAudioMovil.getDescripcion());  
    }
    @Test
    public void testDePaqueteDeProductos() {
      
        assertEquals(38100.0, packAudioMovil.getPrecioBase(), 0.01);
        assertEquals(32385.0, packAudioMovil.getPrecioFinal(), 0.01);
    	
        packAudioMovil.removeItem(auriculares);
        assertEquals(6100.0, packAudioMovil.getPrecioBase(), 0.01);
    	
        packAudioMovil.addItem(auriculares, 4);
    		
        assertEquals(37185.0, paqueteHomeOffice.getPrecioBase(), 0.01);
        assertEquals(37185.0, paqueteHomeOffice.getPrecioFinal(), 0.01);
    }
    @Test
    public void testPrecioFinalConDescuento() {
        assertEquals(850.0, mate.getPrecioFinal()); // Base 1000 con 15% de descuento
    }

    @Test
    public void testDeValidezDeProducto() {
        ItemInvalidoException exSinSKU = assertThrows(ItemInvalidoException.class, () -> 
            new Producto(null, "Remera", "Remera de algodón", "Nike", "Ropa", 500.0, 2, 9.0));
        assertEquals("El ítem 'Remera' es inválido: El SKU no puede ser nulo o vacío.", exSinSKU.getMessage());
        
        ItemInvalidoException exSKUIsBlank = assertThrows(ItemInvalidoException.class, () -> 
            new Producto("  ", "Remera", "Remera de algodón", "Nike", "Ropa", 500.0, 2, 9.0));
        assertEquals("El ítem 'Remera' es inválido: El SKU no puede ser nulo o vacío.", exSKUIsBlank.getMessage());
        
        ItemInvalidoException exSinNombre = assertThrows(ItemInvalidoException.class, () -> 
            new Producto("1", null, "Remera de algodón", "Nike", "Ropa", 500.0, 3, 10.0));
        assertEquals("El ítem 'Producto Desconocido' es inválido: El nombre del producto es obligatorio y no puede estar vacío.", exSinNombre.getMessage());
        
        ItemInvalidoException exNombreIsBlank = assertThrows(ItemInvalidoException.class, () -> 
            new Producto("1", "  ", "Remera de algodón", "Nike", "Ropa", 500.0, 3, 10.0));
        assertEquals("El ítem 'Producto Desconocido' es inválido: El nombre del producto es obligatorio y no puede estar vacío.", exNombreIsBlank.getMessage());
        
        ItemInvalidoException exSinDescripcion = assertThrows(ItemInvalidoException.class, () -> 
            new Producto("2", "Remera", null, "Nike", "Ropa", 500.0, 4, 16.0));
        assertEquals("El ítem 'Remera' es inválido: La descripción del producto es obligatoria.", exSinDescripcion.getMessage());
        
        ItemInvalidoException exDescripcionIsBlank = assertThrows(ItemInvalidoException.class, () -> 
            new Producto("2", "Remera", "  ", "Nike", "Ropa", 500.0, 4, 16.0));
        assertEquals("El ítem 'Remera' es inválido: La descripción del producto es obligatoria.", exDescripcionIsBlank.getMessage());
        
        ItemInvalidoException exSinMarca = assertThrows(ItemInvalidoException.class, () -> 
            new Producto("3", "Remera", "Remera de algodón", null, "Ropa", 500.0, 5, 2.4));
        assertEquals("El ítem 'Remera' es inválido: La marca del producto es obligatoria.", exSinMarca.getMessage());
        
        ItemInvalidoException exMarcaIsBlank = assertThrows(ItemInvalidoException.class, () -> 
            new Producto("3", "Remera", "Remera de algodón", "  ", "Ropa", 500.0, 5, 2.4));
        assertEquals("El ítem 'Remera' es inválido: La marca del producto es obligatoria.", exMarcaIsBlank.getMessage());
        
        ItemInvalidoException exSinCategoria = assertThrows(ItemInvalidoException.class, () -> 
            new Producto("4", "Remera", "Remera de algodón", "Nike", null, 500.0, 6, 12.4));
        assertEquals("El ítem 'Remera' es inválido: La categoría del producto es obligatoria.", exSinCategoria.getMessage());
        
        ItemInvalidoException exCategoriaIsBlank = assertThrows(ItemInvalidoException.class, () -> 
            new Producto("4", "Remera", "Remera de algodón", "Nike", "  ", 500.0, 6, 12.4));
        assertEquals("El ítem 'Remera' es inválido: La categoría del producto es obligatoria.", exCategoriaIsBlank.getMessage());
        
        ItemInvalidoException exPrecioNegativo = assertThrows(ItemInvalidoException.class, () -> 
            new Producto("5", "Remera", "Remera de algodón", "Nike", "Ropa", -10.0, 7, 5.76));
        assertEquals("El ítem 'Remera' es inválido: El precio debe ser un valor mayor a 0.", exPrecioNegativo.getMessage());
        
        ItemInvalidoException exPrecioNull = assertThrows(ItemInvalidoException.class, () -> 
            new Producto("5", "Remera", "Remera de algodón", "Nike", "Ropa", null, 7, 5.76));
        assertEquals("El ítem 'Remera' es inválido: El precio debe ser un valor mayor a 0.", exPrecioNull.getMessage());
        
        ItemInvalidoException exStockNegativo = assertThrows(ItemInvalidoException.class, () -> 
            new Producto("5", "Remera", "Remera de algodón", "Nike", "Ropa", 10.0, -3, 10.5));
        assertEquals("El ítem 'Remera' es inválido: El stock no puede ser nulo ni tener un valor negativo.", exStockNegativo.getMessage());
        
        ItemInvalidoException exStockNull = assertThrows(ItemInvalidoException.class, () -> 
            new Producto("5", "Remera", "Remera de algodón", "Nike", "Ropa", 10.0, null, 10.5));
        assertEquals("El ítem 'Remera' es inválido: El stock no puede ser nulo ni tener un valor negativo.", exStockNull.getMessage());
        
        ItemInvalidoException exPesoNegativo = assertThrows(ItemInvalidoException.class, () -> 
            new Producto("5", "Remera", "Remera de algodón", "Nike", "Ropa", 10.0, 7, -10.5));
        assertEquals("El ítem 'Remera' es inválido: El peso del producto debe ser un valor mayor a 0.", exPesoNegativo.getMessage());
        
        ItemInvalidoException exPesoNull = assertThrows(ItemInvalidoException.class, () -> 
            new Producto("5", "Remera", "Remera de algodón", "Nike", "Ropa", 10.0, 7, null));
        assertEquals("El ítem 'Remera' es inválido: El peso del producto debe ser un valor mayor a 0.", exPesoNull.getMessage());
    }
    @Test
    public void testValidezDePaquete() {
        ItemInvalidoException exSinNombre = assertThrows(ItemInvalidoException.class, () -> 
            new Paquete(null, "Paquete pruebas", "Categoría", 9)
        );
        assertEquals("El ítem 'Nombre Desconocido' es inválido: El nombre del paquete no puede estar nulo o vacío.", exSinNombre.getMessage());
        
        ItemInvalidoException exNombreIsBlank = assertThrows(ItemInvalidoException.class, () -> 
            new Paquete("  ", "Paquete pruebas", "Categoría", 9)
        );
        assertEquals("El ítem 'Nombre Desconocido' es inválido: El nombre del paquete no puede estar nulo o vacío.", exNombreIsBlank.getMessage());
        
        ItemInvalidoException exSinDescripcion = assertThrows(ItemInvalidoException.class, () -> 
            new Paquete("Combo", null, "Categoría", 9)
        );
        assertEquals("El ítem 'Combo' es inválido: La descripción del paquete es obligatoria.", exSinDescripcion.getMessage());
        
        ItemInvalidoException exDescripcionIsBlank = assertThrows(ItemInvalidoException.class, () -> 
            new Paquete("Combo", "   ", "Categoría", 9)
        );
        assertEquals("El ítem 'Combo' es inválido: La descripción del paquete es obligatoria.", exDescripcionIsBlank.getMessage());
        
        ItemInvalidoException exSinCategoria = assertThrows(ItemInvalidoException.class, () -> 
            new Paquete("Combo", "Descripción", null, 9)
        );
        assertEquals("El ítem 'Combo' es inválido: La categoría del paquete es obligatoria.", exSinCategoria.getMessage());
        
        ItemInvalidoException exCategoriaVacia = assertThrows(ItemInvalidoException.class, () -> 
            new Paquete("Combo", "Descripción", "   ", 9)
        );
        assertEquals("El ítem 'Combo' es inválido: La categoría del paquete es obligatoria.", exCategoriaVacia.getMessage());
        
        ItemInvalidoException exSinStock = assertThrows(ItemInvalidoException.class, () -> 
            new Paquete("Combo", "Descripción", "Categoría", null)
        );
        assertEquals("El ítem 'Combo' es inválido: El stock no puede ser nulo ni tener un valor negativo.", exSinStock.getMessage());
        
        ItemInvalidoException exStockNegativo = assertThrows(ItemInvalidoException.class, () -> 
            new Paquete("Combo", "Descripción", "Categoría", -5)
        );
        assertEquals("El ítem 'Combo' es inválido: El stock no puede ser nulo ni tener un valor negativo.", exStockNegativo.getMessage());
    }
    @Test
    public void testDecrementarStockInvalido() {
        StockInsuficienteException exSinStockProducto = assertThrows(StockInsuficienteException.class, () -> 
            productoSinStock.decrementarStock(4));
        assertEquals("No hay stock suficiente de Notebook, quedan 0", exSinStockProducto.getMessage());

        StockInsuficienteException exSinStockPaquete = assertThrows(StockInsuficienteException.class, () -> 
        paqueteSinStock.decrementarStock(3));
        assertEquals("No hay stock suficiente de paquete, quedan 0", exSinStockPaquete.getMessage());
    }

    @Test
    public void testAumentarStock() {
        int stockInicial = termo.stockDisponible();
        termo.aumentarStock(3);
        assertEquals(stockInicial + 3, termo.stockDisponible());
        
        int stockInicialPaquete = packAudioMovil.stockDisponible();
        packAudioMovil.aumentarStock(2);
        assertEquals(stockInicialPaquete + 2, packAudioMovil.stockDisponible());
    }
    
    
}