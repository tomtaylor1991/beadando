/**
 * 
 */
package hu.unideb.etterem.model;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author TomTaylor
 *
 */
public class PozicioTest {

	/**
	 * Test method for {@link hu.unideb.etterem.model.Pozicio#Pozicio(int, int)}.
	 */
	@Test
	public void testPozicio() {
		Pozicio pozicio = new Pozicio(21,10);
		
		assertEquals(21, pozicio.getX());
		assertEquals(10, pozicio.getY());
	}

	/**
	 * Test method for {@link hu.unideb.etterem.model.Pozicio#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsObject() {
		Pozicio pozicio1 = new Pozicio(21,10);
		Pozicio pozicio2 = new Pozicio(21,10);
		Pozicio pozicio3 = new Pozicio(22,10);
		
		assertTrue(pozicio1.equals(pozicio2));
		assertFalse(pozicio1.equals(pozicio3));
	}

}
