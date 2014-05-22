/**
 * 
 */
package hu.unideb.etterem.model;

import static org.junit.Assert.*;

import java.util.Map;

import hu.unideb.etterem.model.exceptions.NincsElegHelyException;
import hu.unideb.etterem.model.interfaces.SzamlazasiFelelos;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author TomTaylor
 *
 */
public class AsztalFoglalasTest {

	private AsztalFoglalas asztalFoglalas;
	private static Asztal a1;
	private static SzamlazasiFelelos felelos;
	private static int helyigeny;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass()  {
		a1= new Asztal(1,10);
		felelos = new Szemely("Teszt Elek", "Tesztvaros", "OTP123");
		helyigeny = 3;
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		asztalFoglalas = new AsztalFoglalas(a1, felelos, helyigeny);
	}

	/**
	 * Test method for {@link hu.unideb.etterem.model.AsztalFoglalas#AsztalFoglalas(hu.unideb.etterem.model.Asztal, hu.unideb.etterem.model.interfaces.SzamlazasiFelelos, int)}.
	 */
	@Test
	public void testAsztalFoglalas() {
		assertEquals(felelos, asztalFoglalas.getFelelos());
		assertEquals(a1, asztalFoglalas.getFoglaltAsztal());
	}

	/**
	 * Test method for {@link hu.unideb.etterem.model.AsztalFoglalas#getVegOsszeg()}.
	 */
	@Test
	public void testGetVegOsszeg() {
		assertEquals(0, asztalFoglalas.getVegOsszeg());
		
		asztalFoglalas.addRendeles(new Etel("", 1000, ""), 2);
		
		assertEquals(2000, asztalFoglalas.getVegOsszeg());
		
		asztalFoglalas.addRendeles(new Etel("", 4000, ""), 0);
		asztalFoglalas.addRendeles(new Etel("", 3000, ""), 1);
		
		assertEquals(5000, asztalFoglalas.getVegOsszeg());
		
		asztalFoglalas.addRendeles(new Etel("", -3000, ""), 1);
		
		assertEquals(2000, asztalFoglalas.getVegOsszeg());
	}

	/**
	 * Test method for {@link hu.unideb.etterem.model.AsztalFoglalas#addRendeles(hu.unideb.etterem.model.Etel, int)}.
	 */
	@Test
	public void testAddRendeles() {
		Etel etel1 = new Etel("Etel1", 1000, "");
		Etel etel2 = new Etel("Etel2", 2000, "");
		Etel etel3 = new Etel("Etel2", 2000, "");
		Etel etel4 = new Etel("Etel4", 2000, "");
		
		asztalFoglalas.addRendeles(etel1, 1);
		asztalFoglalas.addRendeles(etel2, 2);
		asztalFoglalas.addRendeles(etel3, 2);
		
		Map<Etel, Integer> rendeltek = asztalFoglalas.getRendeltek();
		
		assertTrue(rendeltek.keySet().contains(etel1));
		assertTrue(rendeltek.keySet().contains(etel2));
		assertTrue(rendeltek.keySet().contains(etel3));
		assertFalse(rendeltek.keySet().contains(etel4));
		
		int db = rendeltek.get(etel1);
		assertEquals(1, db);
		
		db = rendeltek.get(etel2);
		assertEquals(4, db);
	}

	/**
	 * Test method for {@link hu.unideb.etterem.model.AsztalFoglalas#torolRendeles(java.lang.String)}.
	 */
	@Test
	public void testTorolRendeles() {
		Etel etel1 = new Etel("Etel1", 1000, "");
		Etel etel2 = new Etel("Etel2", 2000, "");
		Etel etel3 = new Etel("Etel2", 2000, "");
		Etel etel4 = new Etel("Etel4", 2000, "");
		
		asztalFoglalas.addRendeles(etel1, 1);
		asztalFoglalas.addRendeles(etel2, 2);
		asztalFoglalas.addRendeles(etel3, 2);
		
		Map<Etel, Integer> rendeltek = asztalFoglalas.getRendeltek();
		
		asztalFoglalas.torolRendeles(etel1.getNev());
		asztalFoglalas.torolRendeles("Hibas Nev");
		
		assertFalse(rendeltek.keySet().contains(etel1));
		assertTrue(rendeltek.keySet().contains(etel2));
	}

	/**
	 * Test method for {@link hu.unideb.etterem.model.AsztalFoglalas#torolTeljesRendeles()}.
	 */
	@Test
	public void testTorolTeljesRendeles() {
		Etel etel1 = new Etel("Etel1", 1000, "");
		Etel etel2 = new Etel("Etel2", 2000, "");
		Etel etel3 = new Etel("Etel2", 2000, "");
		
		asztalFoglalas.addRendeles(etel1, 1);
		asztalFoglalas.addRendeles(etel2, 2);
		asztalFoglalas.addRendeles(etel3, 2);
		
		Map<Etel, Integer> rendeltek = asztalFoglalas.getRendeltek();
		
		asztalFoglalas.torolTeljesRendeles();
		System.out.println(rendeltek.keySet().size());
		assertEquals(0, rendeltek.keySet().size());
	}

	/**
	 * Test method for {@link hu.unideb.etterem.model.AsztalFoglalas#getFoglaltAsztal()}.
	 */
	@Test
	public void testGetFoglaltAsztal() {
		assertEquals(a1, asztalFoglalas.getFoglaltAsztal());
	}

	/**
	 * Test method for {@link hu.unideb.etterem.model.AsztalFoglalas#getFelelos()}.
	 */
	@Test
	public void testGetFelelos() {
		assertEquals(felelos, asztalFoglalas.getFelelos() );
	}

	/**
	 * Test method for {@link hu.unideb.etterem.model.AsztalFoglalas#getRendeltek()}.
	 */
	@Test
	public void testGetRendeltek() {
		Etel etel1 = new Etel("Etel1", 1000, "");
		Etel etel2 = new Etel("Etel2", 2000, "");
		Etel etel3 = new Etel("Etel2", 2000, "");
		Etel etel4 = new Etel("Etel4", 2000, "");
		
		asztalFoglalas.addRendeles(etel1, 1);
		asztalFoglalas.addRendeles(etel2, 2);
		asztalFoglalas.addRendeles(etel3, 2);
		
		Map<Etel, Integer> rendeltek = asztalFoglalas.getRendeltek();
		
		asztalFoglalas.torolRendeles(etel1.getNev());
		asztalFoglalas.torolRendeles("Hibas Nev");
		
		assertFalse(rendeltek.keySet().contains(etel1));
		assertTrue(rendeltek.keySet().contains(etel2));
	}

	/**
	 * Test method for {@link hu.unideb.etterem.model.AsztalFoglalas#foglalSzeket(int)}.
	 */
	@Test
	public void testFoglalSzeket() {
		
		try {
			asztalFoglalas.foglalSzeket(10);
			asztalFoglalas.foglalSzeket(2);
		} catch (NincsElegHelyException e) {
			//nem elvárt kivétel
		}
		
		try {
			asztalFoglalas.foglalSzeket(20);
			fail("Not yet implemented");
		} catch (NincsElegHelyException e) {
			//elvárt kivétel
		}
		
	}

}
