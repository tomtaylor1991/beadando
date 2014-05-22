package hu.unideb.etterem.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author TomTaylor
 *
 */
public class EtelTest {

	private Etel etel;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		etel = new Etel();
	}

	/**
	 * Test method for {@link hu.unideb.etterem.model.Etel#Etel(java.lang.String, int, java.lang.String)}.
	 */
	@Test
	public void testEtelStringIntString() {
		etel = new Etel("Etel1", 1000, "");
		
		assertEquals("Etel1", etel.getNev());
		assertEquals(1000, etel.getAr());
		assertEquals("", etel.getLeiras());
	}

	/**
	 * Test method for {@link hu.unideb.etterem.model.Etel#Etel()}.
	 */
	@Test
	public void testEtel() {
		etel.setNev("Etel1");
		etel.setAr(1000);
		etel.setLeiras("");
		
		assertEquals("Etel1", etel.getNev());
		assertEquals(1000, etel.getAr());
		assertEquals("", etel.getLeiras());
	}

	/**
	 * Test method for {@link hu.unideb.etterem.model.Etel#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsObject() {
		Etel etel2 = new Etel("Etel1", 1000, "");
		etel.setNev("Etel1");
		etel.setAr(1000);
		etel.setLeiras("");
		
		assertTrue(etel.equals(etel2));
	}

}
