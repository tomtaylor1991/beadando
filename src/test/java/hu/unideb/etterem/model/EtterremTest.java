/**
 * 
 */
package hu.unideb.etterem.model;

import static org.junit.Assert.*;

import java.util.List;

import hu.unideb.etterem.model.exceptions.NincsIlyenHelysegException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author TomTaylor
 *
 */
public class EtterremTest {

	private Etterem etterem;
	private static Szoba sz1;
	private static Szoba sz2;
	private static Szoba sz3;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		sz1 = new Szoba(1, "Hall", 10, 10, "Hall leiras");
		sz2 = new Szoba(2, "Terem", 200, 400, "Terem leiras");
		sz3 = new Szoba(3, "Pince", 5, 4, "Pince leiras");
	}

	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		etterem = new Etterem();
	}

	/**
	 * Test method for {@link hu.unideb.etterem.model.Etterem#Etterem()}.
	 */
	@Test
	public void testEtterem() {
		assertEquals(0, etterem.getSzobak().size());
		
		etterem.addSzoba(null);
		assertEquals(0, etterem.getSzobak().size());
	}

	/**
	 * Test method for {@link hu.unideb.etterem.model.Etterem#addSzoba(hu.unideb.etterem.model.Szoba)}.
	 */
	@Test
	public void testAddSzoba() {
		etterem.addSzoba(sz1);
		etterem.addSzoba(sz2);
		etterem.addSzoba(sz3);
		
		assertTrue(etterem.getSzobak().contains(sz1));
		assertTrue(etterem.getSzobak().contains(sz2));
		assertTrue(etterem.getSzobak().contains(sz3));
	}

	/**
	 * Test method for {@link hu.unideb.etterem.model.Etterem#getSzoba(java.lang.String)}.
	 */
	@Test
	public void testGetSzoba() {
		etterem.addSzoba(sz1);
		etterem.addSzoba(sz2);
		etterem.addSzoba(sz3);
		
		try {
			
			assertEquals(sz1, etterem.getSzoba(sz1.getSzobaNev()));
			assertEquals(sz2, etterem.getSzoba(sz2.getSzobaNev()));
			assertEquals(sz3, etterem.getSzoba(sz3.getSzobaNev()));
			
		} catch (NincsIlyenHelysegException e) {
			fail("Nem jo getter!");
		}
		
		try {
			
			assertEquals(sz1, etterem.getSzoba("Hibas szoba nev"));
			fail("Nem jo getter!");
		} catch (NincsIlyenHelysegException e) {
			//elvárt kivétel
		}
	}

	/**
	 * Test method for {@link hu.unideb.etterem.model.Etterem#getSzobaNevek()}.
	 */
	@Test
	public void testGetSzobaNevek() {
		etterem.addSzoba(sz1);
		etterem.addSzoba(sz2);
		
		List<String> szobanevek = etterem.getSzobaNevek();
		
		assertEquals(2, szobanevek.size());
		
		assertTrue(szobanevek.contains(sz1.getSzobaNev()));
		assertTrue(szobanevek.contains(sz2.getSzobaNev()));
		assertFalse(szobanevek.contains(sz3.getSzobaNev()));
	}

	/**
	 * Test method for {@link hu.unideb.etterem.model.Etterem#getSzobaNevById(int)}.
	 */
	@Test
	public void testGetSzobaNevById() {
		etterem.addSzoba(sz2);
		etterem.addSzoba(sz3);
		
		assertEquals(sz2.getSzobaNev(), etterem.getSzobaNevById(2));
		assertEquals(sz3.getSzobaNev(), etterem.getSzobaNevById(3));
		
		assertNull(etterem.getSzobaNevById(20));
	}

	/**
	 * Test method for {@link hu.unideb.etterem.model.Etterem#getSzobak()}.
	 */
	@Test
	public void testGetSzobak() {
		etterem.addSzoba(sz1);
		etterem.addSzoba(sz2);
		List<Szoba> szobak = etterem.getSzobak();
		
		assertTrue(szobak.contains(sz1));
		assertTrue(szobak.contains(sz2));
	}

}
