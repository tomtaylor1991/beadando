package hu.unideb.etterem.model;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Set;

import hu.unideb.etterem.model.exceptions.NincsElegHelyException;
import hu.unideb.etterem.model.interfaces.SzamlazasiFelelos;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author TomTaylor
 *
 */
public class AsztalFoglalasVezerloTest {
	
	private static AsztalFoglalasVezerlo vezerlo;
	private AsztalFoglalas asztalFoglalas1;
	private static Asztal a1;
	private static SzamlazasiFelelos felelos1;
	private static int helyigeny1;
	private AsztalFoglalas asztalFoglalas2;
	private static Asztal a2;
	private static SzamlazasiFelelos felelos2;
	private static int helyigeny2;
	private static ElrendezesVezerlo elrendezesVezerlo;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		a1= new Asztal(1,10);
		felelos1 = new Szemely("Teszt Elek1", "Tesztvaros1", "OTP1231");
		helyigeny1 = 1;
		a2= new Asztal(2,3);
		felelos2 = new Szemely("Teszt Elek2", "Tesztvaros2", "OTP1232");
		helyigeny2 = 2;
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		elrendezesVezerlo = new ElrendezesVezerlo(new Etterem());
		elrendezesVezerlo = new ElrendezesVezerlo(new Etterem());
		elrendezesVezerlo.getEtterem().addSzoba(new Szoba(1, "Hall", 10, 20, ""));
		elrendezesVezerlo.getEtterem().addSzoba(new Szoba(2, "Pince", 2, 3, ""));
		elrendezesVezerlo.getEtterem().getSzoba("Hall").addAsztal(a1, new Pozicio(1,1));
		elrendezesVezerlo.getEtterem().getSzoba("Pince").addAsztal(a2, new Pozicio(1,1));
		
		vezerlo = new AsztalFoglalasVezerlo( elrendezesVezerlo, new Menu());
		
		vezerlo.addAsztalFoglalas(
				new AsztalFoglalas(a1, felelos1, helyigeny1)
				);
		vezerlo.addAsztalFoglalas(
				new AsztalFoglalas(a2, felelos2, helyigeny2)
				);
	}

	/**
	 * Test method for {@link hu.unideb.etterem.model.AsztalFoglalasVezerlo#rendezSzamlat(int)}.
	 * @throws NincsElegHelyException 
	 */
	@Test
	public void testRendezSzamlat() throws NincsElegHelyException {
		Etel etel1 = new Etel("Etel1", 1000, "");
		Etel etel2 = new Etel("Etel2", 2000, "");

		int id = vezerlo.getIdByPozicio("Hall", new Pozicio(1,1));

		AsztalFoglalas asztalFoglalas = vezerlo.getFoglalas(id);
		asztalFoglalas.addRendeles(etel1, 1);
		asztalFoglalas.addRendeles(etel2, 1);
		
		vezerlo.rendezSzamlat(1000);
		
		assertEquals(0, vezerlo.getBevetel());
		
		vezerlo.rendezSzamlat(id);
		
		assertEquals(4000, vezerlo.getBevetel());
		
	}

	/**
	 * Test method for {@link hu.unideb.etterem.model.AsztalFoglalasVezerlo#getVegosszeg(int)}.
	 */
	@Test
	public void testGetVegosszeg() {
		Etel etel1 = new Etel("Etel1", 1000, "");
		Etel etel2 = new Etel("Etel2", 2000, "");

		int id = vezerlo.getIdByPozicio("Hall", new Pozicio(1,1));

		AsztalFoglalas asztalFoglalas = vezerlo.getFoglalas(id);
		asztalFoglalas.addRendeles(etel1, 1);
		asztalFoglalas.addRendeles(etel2, 1);
				
		assertEquals(4000, vezerlo.getVegosszeg(id));
		assertEquals(0, vezerlo.getVegosszeg(100));
	}

	/**
	 * Test method for {@link hu.unideb.etterem.model.AsztalFoglalasVezerlo#isFoglaltAsztal(hu.unideb.etterem.model.Asztal)}.
	 */
	@Test
	public void testIsFoglaltAsztal() {
		assertTrue(vezerlo.isFoglaltAsztal(a1));
		assertTrue(vezerlo.isFoglaltAsztal(a2));
		assertFalse(vezerlo.isFoglaltAsztal(new Asztal()));
	}

	/**
	 * Test method for {@link hu.unideb.etterem.model.AsztalFoglalasVezerlo#addAsztalFoglalas(int, hu.unideb.etterem.model.AsztalFoglalas)}.
	 * @throws NincsElegHelyException 
	 */
	@Test
	public void testAddAsztalFoglalas() throws NincsElegHelyException {
		AsztalFoglalas asztalFoglalas = new AsztalFoglalas(
				new Asztal(3,1), 
				new Szemely("", "", ""), 
				1);
		vezerlo.addAsztalFoglalas(10,asztalFoglalas);
		assertEquals(asztalFoglalas, vezerlo.getFoglalas(10));
		
		//nem szabad hogy hozzáadja a vezérlőhöz
		vezerlo.addAsztalFoglalas(11,asztalFoglalas);
		assertEquals(3, vezerlo.getFoglalasIdLista().size());
		
		vezerlo.addAsztalFoglalas(asztalFoglalas);
		assertEquals(3, vezerlo.getFoglalasIdLista().size());
	}



	/**
	 * Test method for {@link hu.unideb.etterem.model.AsztalFoglalasVezerlo#getNextId()}.
	 * @throws NincsElegHelyException 
	 */
	@Test
	public void testGetNextId() throws NincsElegHelyException {
		assertEquals(3, vezerlo.getNextId());
		
		AsztalFoglalas asztalFoglalas = new AsztalFoglalas(
				new Asztal(3,1), 
				new Szemely("", "", ""), 
				1);
		vezerlo.addAsztalFoglalas(10,asztalFoglalas);
		
		assertEquals(11, vezerlo.getNextId());
	}

	/**
	 * Test method for {@link hu.unideb.etterem.model.AsztalFoglalasVezerlo#getFoglalas(int)}.
	 * @throws NincsElegHelyException 
	 */
	@Test
	public void testGetFoglalas() throws NincsElegHelyException {
		AsztalFoglalas asztalFoglalas = new AsztalFoglalas(
				new Asztal(3,1), 
				new Szemely("", "", ""), 
				1);
		vezerlo.addAsztalFoglalas(10,asztalFoglalas);
		assertEquals(asztalFoglalas, vezerlo.getFoglalas(10));
	}

	/**
	 * Test method for {@link hu.unideb.etterem.model.AsztalFoglalasVezerlo#getFoglalasIdLista()}.
	 */
	@Test
	public void testGetFoglalasIdLista() {
		Set<Integer> foglalasok = vezerlo.getFoglalasIdLista();
		assertEquals(2, foglalasok.size());
		assertTrue( foglalasok.contains(1) );
		assertTrue( foglalasok.contains(2) );
		assertFalse( foglalasok.contains(3) );
	}

	/**
	 * Test method for {@link hu.unideb.etterem.model.AsztalFoglalasVezerlo#torolFoglalas(int)}.
	 */
	@Test
	public void testTorolFoglalas() {
		vezerlo.torolFoglalas(1);
		assertNull( vezerlo.getFoglalas(1) );
		
		vezerlo.torolFoglalas(4);
	}

	/**
	 * Test method for {@link hu.unideb.etterem.model.AsztalFoglalasVezerlo#getFoglaltAsztalok(java.lang.String)}.
	 */
	@Test
	public void testGetFoglaltAsztalok() {
		List<Asztal> asztalok = vezerlo.getFoglaltAsztalok("Hall");
		List<Asztal> asztalok2 = vezerlo.getFoglaltAsztalok("Pince");
		
		assertTrue( asztalok.contains(a1));
		assertFalse( asztalok.contains(a2));
		
		assertTrue( asztalok2.contains(a2));
		assertFalse( asztalok2.contains(a1));
		
		//belső exception tesztek
		assertEquals(0, vezerlo.getFoglaltAsztalok("Pince111").size() );
	}

	/**
	 * Test method for {@link hu.unideb.etterem.model.AsztalFoglalasVezerlo#getFoglaltAsztalokPozicioi(java.lang.String)}.
	 */
	@Test
	public void testGetFoglaltAsztalokPozicioi() {
		List<Pozicio> asztalok = vezerlo.getFoglaltAsztalokPozicioi("Hall");
		
		assertTrue( asztalok.contains(new Pozicio(1,1)));
		assertFalse( asztalok.contains(new Pozicio(2,1000)));
		
		//belső exception tesztek
		assertEquals(0, vezerlo.getFoglaltAsztalok("Pince111").size() );
	}

	/**
	 * Test method for {@link hu.unideb.etterem.model.AsztalFoglalasVezerlo#isFoglaltPozicionLevoAsztal(java.lang.String, hu.unideb.etterem.model.Pozicio)}.
	 */
	@Test
	public void testIsFoglaltPozicionLevoAsztal() {
		assertTrue( vezerlo.isFoglaltPozicionLevoAsztal( "Hall", new Pozicio(1,1) ) );
		assertTrue( vezerlo.isFoglaltPozicionLevoAsztal( "Pince", new Pozicio(1,1) ) );
		assertFalse( vezerlo.isFoglaltPozicionLevoAsztal( "Hall", new Pozicio(2,1) ) );
	}

	/**
	 * Test method for {@link hu.unideb.etterem.model.AsztalFoglalasVezerlo#getIdByPozicio(java.lang.String, hu.unideb.etterem.model.Pozicio)}.
	 */
	@Test
	public void testGetIdByPozicio() {
		assertEquals( 1, vezerlo.getIdByPozicio("Hall", new Pozicio(1,1) ));
		assertEquals( 0, vezerlo.getIdByPozicio("Hall", new Pozicio(1,2) ));
		assertEquals( 0, vezerlo.getIdByPozicio("2Hall", new Pozicio(1,1) ));
		assertEquals( 2, vezerlo.getIdByPozicio("Pince", new Pozicio(1,1) ));
	}

}
