package eCommerceTest;

import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Mockito.*; 
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import eCommerce.*;
import eCommerce.item.*;
import eCommerce.libreriasExternas.*;


@ExtendWith(MockitoExtension.class)
class SetUp {
	
	@Mock protected  ApiBilleteraVirtual apiBVMock;
	@Mock protected ApiTarjetaDeCredito apiTCMock;
	@Mock protected ApiTransferenciaBancaria apiTransferenciaMock ;
	@Mock protected MailSender apiMailSenderMock;
	@Mock protected MessageSender apiMsgSenderMock;
	
	@Mock protected CorreoArgentina correoMock;
	@Mock protected EnvioExpress envioExpressMock;
	@Mock protected Sucursal sucursalMock;
	
	
	protected ECommerce mercadoLibre;
	protected Pedido pedido1; 
	protected Pedido pedidoVacio; 
	protected Pedido pedidoConPaquete;
	
	protected DatosCliente ami = new DatosCliente("ami@gmail.com", 123213223);
	protected DatosCliente azu = new DatosCliente("azu@gmail.com", 123254543);
	protected DatosCliente rena = new DatosCliente("rena@gmail.com", 123214343);
	
	
	protected Atributo<Double> peso = new Atributo<Double>("Peso", 1.8);
	protected Atributo<String> color = new Atributo<String>("Color", "Gris");
	protected Atributo<Boolean> esNuevo = new Atributo<Boolean>("Es Nuevo", true);
	protected Atributo<Integer> pulgadas = new Atributo<Integer>("Pulgadas", 16);

	protected Producto termo;
	protected Producto mate;
	protected Producto notebook;
	protected Producto auriculares;
	protected Producto funda;
	protected Producto cable;
	protected Paquete packAudioMovil;
	protected Producto teclado;
	protected Producto mouse;   
	protected Producto productoSinStock;
	protected Paquete paqueteSinStock;
	protected Paquete paqueteHomeOffice;
	
	
	
	@BeforeEach
	public void setUp() { 
		MailSender apiMailSenderMock = mock(MailSender.class);
		MessageSender apiMsgSenderMock = mock(MessageSender.class);
		this.mercadoLibre = new ECommerce(apiMailSenderMock, apiMsgSenderMock);
		setUpItems();
		armadoDePaquetes();
		armadoDeECommerce();
		armadoDePedidos();
		
	}
	public void setUpItems() {
		this.termo = new Producto("123", "Termo", "Termo acero", "Lumilagro", "Bazar", 1000.0, 0, 3.0);
		this.mate = new Producto("456", "Mate", "Mate de madera", "Urquiza", "Bazar", 1000.0, 15.0, 1, 4.5);
		this.notebook = new Producto("999", "Notebook", "Computadora", "Dell", "Tech", 150000.0, 100, 2.0);
		this.productoSinStock = new Producto("000", "Notebook", "Computadora", "Dell", "Tech", 150000.0, 0, 2.0);
		this.auriculares = new Producto("999", "Auriculares Bluetooth", "auriculares inalambricos", "JBL", "Audio", 8000.0, 30, 2.2);
		this.funda = new Producto("111", "Funda protectora", "Funda de silicona", "shein", "Accesorio", 1500.0, 30, 5.4);
		this.cable = new Producto("222", "Cable USC-C", "cable de carga para celulares multiples marcas", "sony", "electronica", 800.0, 20, 5.4);
		this.teclado = new Producto("333", "Teclado", "Teclado inalambrico", "genius", "perifericos", 2000.0, 10, 4.4);
		this.mouse = new Producto("444", "Mouse", "Mouse inalambrico", "sony", "electronica", 800.0, 4, 6.6);
    	
		this.packAudioMovil = new Paquete("Pack Audio Móvil", "Paquete de accesorios", "Audio", 15.0, 7);
		this.paqueteSinStock = new Paquete("paquete", "paquete", "Varios", 0);
		this.paqueteHomeOffice = new Paquete("Kit Home Office", "Paquete de audio + mouse + teclado", "Oficina", 3);
		
		notebook.agregarAtributo(peso);
		notebook.agregarAtributo(color);
		notebook.agregarAtributo(esNuevo);
		notebook.agregarAtributo(pulgadas);

		mouse.agregarAtributo(color);
	}
	
	public void armadoDePaquetes() {
		packAudioMovil.addItem(auriculares, 4);
		packAudioMovil.addItem(funda, 3);
		packAudioMovil.addItem(cable, 2);
    	
		paqueteHomeOffice.addItem(teclado, 2);
		paqueteHomeOffice.addItem(mouse, 1);
		paqueteHomeOffice.addItem(packAudioMovil, 1);
	}
	
	public void armadoDeECommerce() {
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
	}
	
	public void armadoDePedidos() {
		this.pedido1 = this.mercadoLibre.newPedido(ami);
		this.pedido1.addItem(mouse, 1);
		this.pedido1.addItem(teclado, 2);    
        
		this.pedidoVacio = this.mercadoLibre.newPedido(rena);
		this.pedidoConPaquete = this.mercadoLibre.newPedido(azu);
		
		this.pedidoConPaquete.addItem(packAudioMovil, 1);
		
	}
}