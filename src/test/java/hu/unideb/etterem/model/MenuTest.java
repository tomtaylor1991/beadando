/**
 * 
 */
package hu.unideb.etterem.model;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * @author TomTaylor
 *
 */
public class MenuTest {
	private Menu menu;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		menu = new Menu();
	}

	/**
	 * Test method for {@link hu.unideb.etterem.model.Menu#Menu(java.util.Map)}.
	 */
	@Test
	public void testMenuMapOfStringListOfEtel() {
		Menu menu2 = new Menu(new HashMap<String, List<Etel>>());
		assertEquals(menu.getMenu(), menu2.getMenu());
	}

	/**
	 * Test method for {@link hu.unideb.etterem.model.Menu#addEtel(java.lang.String, hu.unideb.etterem.model.Etel)}.
	 */
	@Test
	public void testAddEtel() {
		menu.addEtel("kat1", new Etel());
		menu.addEtel("kat1", new Etel());
		menu.addEtel("kat2", new Etel());
		
		assertEquals(2, menu.getMenu().size());
		assertEquals(2, menu.getMenu().get("kat1").size());
	}

	/**
	 * Test method for {@link hu.unideb.etterem.model.Menu#getEtelek(java.lang.String)}.
	 */
	@Test
	public void testGetEtelek() {
		Etel e1= new Etel("Etel1", 1, "");
		Etel e2= new Etel("Etel2", 2, "");
		Etel e3= new Etel("Etel3", 3, "");
		
		menu.addEtel("kat1", e1);
		menu.addEtel("kat1", e2);
		menu.addEtel("kat2", e3);
		
		assertTrue( menu.getMenu().get("kat1").contains(e1) );
		assertTrue( menu.getMenu().get("kat1").contains(e2) );
		
		assertFalse( menu.getMenu().get("kat2").contains(e1) );
		assertFalse( menu.getMenu().get("kat2").contains(e2) );
		
		assertTrue( menu.getMenu().get("kat2").contains(e3) );
		assertFalse( menu.getMenu().get("kat1").contains(e3) );
	}

	/**
	 * Test method for {@link hu.unideb.etterem.model.Menu#getKategoriaNevek()}.
	 */
	@Test
	public void testGetKategoriaNevek() {
		menu.addEtel("kat1", new Etel());
		menu.addEtel("kat1", new Etel());
		menu.addEtel("kat2", new Etel());
		
		assertEquals(2, menu.getKategoriaNevek().size());
		assertTrue(menu.getKategoriaNevek().contains("kat1"));
		assertTrue(menu.getKategoriaNevek().contains("kat2"));
	}

	/**
	 * Test method for {@link hu.unideb.etterem.model.Menu#getKategoriaNevekLista()}.
	 */
	@Test
	public void testGetKategoriaNevekLista() {
		menu.addEtel("kat1", new Etel());
		menu.addEtel("kat1", new Etel());
		menu.addEtel("kat2", new Etel());
		
		assertEquals(2, menu.getKategoriaNevek().size());
		assertTrue(menu.getKategoriaNevek().contains("kat1"));
		assertTrue(menu.getKategoriaNevek().contains("kat2"));
	}

}
