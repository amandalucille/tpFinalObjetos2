package eCommerceTest;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import eCommerce.*;

public class ProductoTest {

    @Test
    public void testPrecioFinalSinDescuento() {

        Producto termo = new Producto("SKU-123", "Termo", "Termo acero", "Lumilagro", "Bazar", 1000.0);
        
        assertEquals(1000.0, termo.precioFinal());
    }

    @Test
    public void testPrecioFinalConDescuento() {
        
        Producto mate = new Producto("SKU-456", "Mate", "Mate de madera", "Urquiza", "Bazar", 1000.0, 15.0);
        
        
        assertEquals(850.0, mate.precioFinal());
    }

    @Test
    public void testProductoInvalidoPorFaltaDeSku() {
        
        Producto invalido = new Producto(null, "Remera", "Remera de algodón", "Nike", "Ropa", 500.0);
        
        assertFalse(invalido.esProductoValido());
    }

    @Test
    public void testAtributosDinamicosDeDiferentesTipos() {
        Producto notebook = new Producto("SKU-999", "Notebook", "Computadora", "Dell", "Tech", 150000.0);
        
        
        Atributo<Double> peso = new Atributo<>("Peso", 1.8);
        Atributo<String> color = new Atributo<>("Color", "Gris");
        Atributo<Boolean> esNuevo = new Atributo<>("Es Nuevo", true);
        
        
        notebook.agregarAtributo(peso);
        notebook.agregarAtributo(color);
        notebook.agregarAtributo(esNuevo);
        
        
        assertEquals(1.8, peso.getValor());
        assertEquals("Gris", color.getValor());
        assertTrue(esNuevo.getValor());
    }
}