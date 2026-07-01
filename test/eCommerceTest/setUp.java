package eCommerceTest;

import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Mockito.*; 

import eCommerce.*;
import eCommerce.item.*;
import eCommerce.libreriasExternas.MailSender;
import eCommerce.libreriasExternas.MessageSender;

class setUp {

	Producto termo;
	Producto mate;
	Producto notebook;
	
	// Atributos dinámicos
	Atributo<Double> peso = new Atributo<Double>("Peso", 1.8);
	Atributo<String> color = new Atributo<String>("Color", "Gris");
	Atributo<Boolean> esNuevo = new Atributo<Boolean>("Es Nuevo", true);
	Atributo<Integer> pulgadas = new Atributo<Integer>("Pulgadas", 16);

	Producto auriculares;
	Producto funda;
	Producto cable;
	Paquete packAudioMovil;
	Producto teclado;
	Producto mouse;   
	Producto productoSinStock;
	Paquete paqueteSinStock;
	Paquete paqueteHomeOffice;
	
	ECommerce mercadoLibre;
	Pedido pedido1; 
	Pedido pedidoVacio; 
	Pedido pedidoConPaquete;
	
	@BeforeEach
	public void SetUp() { 
	
		this.termo = new Producto("123", "Termo", "Termo acero", "Lumilagro", "Bazar", 1000.0, 0, 3.0);
		this.mate = new Producto("456", "Mate", "Mate de madera", "Urquiza", "Bazar", 1000.0, 15.0, 1, 4.5);
		this.notebook = new Producto("999", "Notebook", "Computadora", "Dell", "Tech", 150000.0, 100, 2.0);
		this.productoSinStock = new Producto("000", "Notebook", "Computadora", "Dell", "Tech", 150000.0, 0, 2.0);
		this.auriculares = new Producto("999", "Auriculares Bluetooth", "auriculares inalambricos", "JBL", "Audio", 8000.0, 10, 2.2);
		this.funda = new Producto("111", "Funda protectora", "Funda de silicona", "shein", "Accesorio", 1500.0, 9, 5.4);
		this.cable = new Producto("222", "Cable USC-C", "cable de carga para celulares multiples marcas", "sony", "electronica", 800.0, 8, 5.4);
		this.teclado = new Producto("333", "Teclado", "Teclado inalambrico", "genius", "perifericos", 2000.0, 5, 4.4);
		this.mouse = new Producto("444", "Mouse", "Mouse inalambrico", "sony", "electronica", 800.0, 4, 6.6);
    	
		this.packAudioMovil = new Paquete("Pack Audio Móvil", "Paquete de accesorios", "Audio", 15.0, 7);
		this.paqueteSinStock = new Paquete("paquete", "paquete", "Varios", 0);
		this.paqueteHomeOffice = new Paquete("Kit Home Office", "Paquete de audio + mouse + teclado", "Oficina", 3);
        
		notebook.agregarAtributo(peso);
		notebook.agregarAtributo(color);
		notebook.agregarAtributo(esNuevo);
		notebook.agregarAtributo(pulgadas);

		mouse.agregarAtributo(color);
        
		packAudioMovil.addItem(auriculares, 4);
		packAudioMovil.addItem(funda, 3);
		packAudioMovil.addItem(cable, 2);
    	
		paqueteHomeOffice.addItem(teclado, 2);
		paqueteHomeOffice.addItem(mouse, 1);
		paqueteHomeOffice.addItem(packAudioMovil, 1);
        
		
		MailSender apiMailMock = mock(MailSender.class);
		MessageSender apiMsgMock = mock(MessageSender.class);
		this.mercadoLibre = new ECommerce(apiMailMock, apiMsgMock);
		
		
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
        

		this.pedido1 = new Pedido(this.mercadoLibre);
		this.pedido1.addItem(mouse, 1);
		this.pedido1.addItem(teclado, 2);    
        
		this.pedidoVacio = new Pedido(this.mercadoLibre);
		
		this.pedidoConPaquete = new Pedido(this.mercadoLibre);
		this.pedidoConPaquete.addItem(packAudioMovil, 1); 
	}
}