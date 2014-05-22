/**
 * 
 */
package hu.unideb.etterem.model;

import static org.junit.Assert.*;
import hu.unideb.etterem.model.exceptions.NincsIlyenAsztalException;
import hu.unideb.etterem.model.exceptions.NincsIlyenHelysegException;

import org.junit.Before;
import org.junit.Test;

/**
 * @author TomTaylor
 *
 */
public class ElrendezesVezerloTest {
	private Etterem etterem;
	private ElrendezesVezerlo vezerlo;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		etterem = new Etterem();
		vezerlo = new ElrendezesVezerlo(etterem);
	}

	/**
	 * Test method for {@link hu.unideb.etterem.model.ElrendezesVezerlo#getEtterem()}.
	 */
	@Test
	public void testGetEtterem() {
		assertEquals(etterem, vezerlo.getEtterem());
	}

	/**
	 * Test method for {@link hu.unideb.etterem.model.ElrendezesVezerlo#addUjAsztalSzobahoz(java.lang.String, hu.unideb.etterem.model.Pozicio, int)}.
	 * @throws NincsIlyenAsztalException 
	 * @throws NincsIlyenHelysegException 
	 */
	@Test
	public void testAddUjAsztalSzobahoz() {		
		vezerlo.getEtterem().addSzoba(new Szoba(1, "Hall", 10, 10, "Leiras"));
		vezerlo.addUjAsztalSzobahoz("Hall", new Pozicio(1, 2), 2);
		
		try {
			assertEquals(2, vezerlo.getAsztal("Hall", new Pozicio(1, 2)).getSzekekSzama() );
			
			assertEquals(2, vezerlo.getAsztal("Hall2", new Pozicio(1, 20)).getSzekekSzama() );
		} catch (NincsIlyenHelysegException | NincsIlyenAsztalException e) {}
		
		try {
			Asztal a = vezerlo.getAsztal("Hall", new Pozicio(2, 3));
			fail("Hiba");
		} catch (NincsIlyenHelysegException | NincsIlyenAsztalException e) {/**Elvárt kivétel*/}
		
	}

	/**
	 * Test method for {@link hu.unideb.etterem.model.ElrendezesVezerlo#getKovetkezoAsztalID()}.
	 */
	@Test
	public void testGetKovetkezoAsztalID() {		
		vezerlo.getEtterem().addSzoba(new Szoba(1, "Hall", 10, 10, "Leiras"));
		vezerlo.addUjAsztalSzobahoz("Hall", new Pozicio(1, 2), 2);
		assertEquals(2, vezerlo.getKovetkezoAsztalID());
	}

	/**
	 * Test method for {@link hu.unideb.etterem.model.ElrendezesVezerlo#getAsztal(java.lang.String, hu.unideb.etterem.model.Pozicio)}.
	 * @throws NincsIlyenAsztalException 
	 * @throws NincsIlyenHelysegException 
	 */
	@Test
	public void testGetAsztal() throws NincsIlyenHelysegException, NincsIlyenAsztalException {
		Asztal asztal = new Asztal(1, 10);
		Szoba szoba = new Szoba(1, "Hall", 10, 10, "Leiras");
		vezerlo.getEtterem().addSzoba(szoba);
		vezerlo.addUjAsztalSzobahoz("Hall", new Pozicio(1, 2), 10);
		
		Asztal a = vezerlo.getAsztal("Hall", new Pozicio(1, 2));
		
		assertEquals(asztal.getID(), a.getID());
		assertEquals(asztal.getSzekekSzama(), a.getSzekekSzama());
	}

	/**
	 * Test method for {@link hu.unideb.etterem.model.ElrendezesVezerlo#torolAsztal(java.lang.String, hu.unideb.etterem.model.Pozicio)}.
	 */
	@Test
	public void testTorolAsztal() {
		Asztal asztal = new Asztal(1, 10);
		Szoba szoba = new Szoba(1, "Hall", 10, 10, "Leiras");
		vezerlo.getEtterem().addSzoba(szoba);
		vezerlo.addUjAsztalSzobahoz("Hall", new Pozicio(1, 2), 10);
		
		vezerlo.torolAsztal("Hall", new Pozicio(1, 2));
		
		try {
			Asztal a = vezerlo.getAsztal("Hall", new Pozicio(1, 2));
			
			fail("Hiba");
		} catch (NincsIlyenHelysegException | NincsIlyenAsztalException e) {/**Elvárt kivétel*/}
	}

	/**
	 * Test method for {@link hu.unideb.etterem.model.ElrendezesVezerlo#torolSzoba(java.lang.String)}.
	 * @throws NincsIlyenHelysegException 
	 */
	@Test(expected=NincsIlyenHelysegException.class)
	public void testTorolSzoba() throws NincsIlyenHelysegException {
		Szoba szoba = new Szoba(1, "Hall", 10, 10, "Leiras");
		vezerlo.torolSzoba("Hall");
		vezerlo.torolSzoba("HallHiba");
		vezerlo.getEtterem().getSzoba("Hall");
	}

}
