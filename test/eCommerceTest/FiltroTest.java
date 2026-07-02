package eCommerceTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import eCommerce.filtro.*;
import eCommerce.filtro.compuertasLogicas.*;
import eCommerce.item.*;

class FiltroTest extends SetUp {

	@Test
	public void testFiltroPorCategoria() {
		CriterioDeBusqueda filtro = new FiltroPorCategoria("Bazar");
		assertTrue(filtro.cumple(termo));
		assertTrue(filtro.cumple(mate));
		assertFalse(filtro.cumple(notebook));

		//Veo que no se sensible con  las mayusculas
		CriterioDeBusqueda filtroMinuscula = new FiltroPorCategoria("bazar");
		assertTrue(filtroMinuscula.cumple(termo));
	}

	@Test
	public void testFiltroPorDisponibilidad() {
		CriterioDeBusqueda filtro = new FiltroPorDisponibilidad();

		assertFalse(filtro.cumple(termo));   // stock 0
		assertTrue(filtro.cumple(mate));     // stock 1
		assertTrue(filtro.cumple(notebook)); // stock 100
	}

	@Test
	public void testFiltroPorNombre() {
		CriterioDeBusqueda filtro = new FiltroPorNombre("note");
		assertTrue(filtro.cumple(notebook));
		assertFalse(filtro.cumple(termo));

		
		CriterioDeBusqueda filtroMayus = new FiltroPorNombre("NOTE");
		assertTrue(filtroMayus.cumple(notebook));
	}

	@Test
	public void testFiltroPorPrecioMaximo() {
		CriterioDeBusqueda filtro = new FiltroPorPrecioMaximo(1000.0);

		assertTrue(filtro.cumple(termo));    // precio base 1000.0
		assertTrue(filtro.cumple(mate));     // precio base 1000.0 (antes del descuento)
		assertFalse(filtro.cumple(notebook)); // precio base 150000.0
	}

	@Test
	public void testFiltrarEnECommerceUsaElCriterio() {
		CriterioDeBusqueda filtroAudio = new FiltroPorCategoria("Audio");
		List<ItemCatalogo> resultado = mercadoLibre.filtrar(filtroAudio);

		assertTrue(resultado.contains(auriculares));
		assertTrue(resultado.contains(packAudioMovil));
		assertFalse(resultado.contains(notebook));
		
		resultado.stream().forEach(i -> assertTrue(filtroAudio.cumple(i))); // es medio redundante, pero chequeamos igual
		
		
	}
	
	@Test
	public void testCompuestoEnECommerce() {
		CriterioDeBusqueda filtroAudio = new FiltroPorCategoria("Audio"); // auriculares y packAudioMovil
		CriterioDeBusqueda filtroPrecio = new FiltroPorPrecioMaximo(1100d);   //termo, mate, cable, mouse
		CriterioDeBusqueda filtroAudioAndPrecio = new CriterioAnd(filtroAudio, filtroPrecio); // ninguno
		

		CriterioDeBusqueda filtroNombre = new FiltroPorNombre("note"); // notebook
		
		CriterioDeBusqueda filtroOr = new CriterioOr(filtroAudioAndPrecio, filtroNombre);
		
		
		
		Set<ItemCatalogo> resultado = new HashSet<>();
		resultado.addAll(mercadoLibre.filtrar(filtroOr)); // notebook
		
		assertEquals(Set.of(notebook), resultado);
		
		
	}
	
	@Test
	public void testNotEnECommerce() {
		CriterioDeBusqueda filtroAudio = new FiltroPorCategoria("Audio"); // auriculares y packAudioMovil
		
		CriterioDeBusqueda filtroNotAudio = new CriterioNot(filtroAudio); 

		
		Set<ItemCatalogo> resultado = new HashSet<>();
		resultado.addAll(mercadoLibre.filtrar(filtroNotAudio)); 
		
		assertEquals(Set.of(notebook,funda,termo,mate, mouse, cable,teclado,paqueteHomeOffice), resultado);
		
	}
}