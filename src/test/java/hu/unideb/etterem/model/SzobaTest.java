/**
 * 
 */
package hu.unideb.etterem.model;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Set;

import hu.unideb.etterem.model.exceptions.NemFerBeAHelysegbeException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author TomTaylor
 *
 */
public class SzobaTest {

	private Szoba szoba;
	Asztal a1;
	Asztal a2;
	Asztal a3;
	Pozicio p1;
	Pozicio p2;
	Pozicio p3;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		szoba = new Szoba(1, "Teszt", 10, 15, "Ez egy teszt szoba.");
		a1 = new Asztal(1, 5);
		a2 = new Asztal(10, 10);
		a3 = new Asztal(2, 10);
		p1 = new Pozicio(1, 2);
		p2 = new Pozicio(3, 4);
		p3 = new Pozicio(-20, 20);
	}

	@Test
	public void testKonstruktorParameterek() {		
		assertEquals( 1, szoba.getSzobaID() );
		assertEquals( "Teszt", szoba.getSzobaNev() );
		assertEquals( 10, szoba.getMeretX() );
		assertEquals( 15, szoba.getMeretY() );
		assertEquals( "Ez egy teszt szoba.", szoba.getLeiras() );
	}
	
	@Test
	public void testSetterGetters() {
		szoba = new Szoba();
		szoba.setSzobaID(1);
		szoba.setSzobaNev("Teszt");
		szoba.setMeretX(10);
		szoba.setMeretY(15);
		szoba.setLeiras("Ez egy teszt szoba.");
		
		assertEquals( 1, szoba.getSzobaID() );
		assertEquals( "Teszt", szoba.getSzobaNev() );
		assertEquals( 10, szoba.getMeretX() );
		assertEquals( 15, szoba.getMeretY() );
		assertEquals( "Ez egy teszt szoba.", szoba.getLeiras() );
	}
	
	@Test(expected = NemFerBeAHelysegbeException.class)
	public void testNemFerBelef() throws NemFerBeAHelysegbeException {
		szoba.addAsztal(new Asztal(), new Pozicio(11 , 15));
	}
	
	@Test
	public void testHibasParameteresAsztalHozzaadas() throws NemFerBeAHelysegbeException {
		szoba.addAsztal(null, null);
		assertEquals( 0, szoba.getIdList().size()  );
		
		szoba.addAsztal(new Asztal(), null);
		assertEquals( 0, szoba.getIdList().size()  );
		
		szoba.addAsztal(null, new Pozicio(1, 2));
		assertEquals( 0, szoba.getIdList().size()  );
	}
	
	@Test
	public void testParameteresAsztalHozzaadas(){
		
		try {
			szoba.addAsztal( a1, p1);
			szoba.addAsztal( a2, p2);
			szoba.addAsztal( a3, p3);
		} catch (NemFerBeAHelysegbeException e) {
			}
		
		assertEquals(2, szoba.getIdList().size());
		
		int id = szoba.getIdList().get(0);
		assertEquals( 10, id );
	}
	
	@Test
	public void testJoPozicionLevoAsztalok(){
		
		try {
			szoba.addAsztal( a1, p1);
			szoba.addAsztal( a2, p2);
			szoba.addAsztal( a3, p3);
		} catch (NemFerBeAHelysegbeException e) {
			}
		
		 Set<Pozicio> poziciok = szoba.getPoziciok();
		 
		assertTrue( poziciok.contains(p1) );
		assertTrue( poziciok.contains(p2) );
		assertFalse( poziciok.contains(p3) );
	}
	
	@Test
	public void testMegfeleloAsztalok(){
		
		try {
			szoba.addAsztal( a1, p1);
			szoba.addAsztal( a2, p2);
			szoba.addAsztal( a3, p3);
		} catch (NemFerBeAHelysegbeException e) {
			}
		
		List<Asztal> asztalok = szoba.getAsztalLista();
		 
		assertTrue( asztalok.contains(a1) );
		assertTrue( asztalok.contains(a2) );
		assertFalse( asztalok.contains(a3) );
	}
	
	@Test
	public void testLehetsegesJoAsztalok(){

		try {
			szoba.addAsztal( a1, p1);
			szoba.addAsztal( a2, p2);
			szoba.addAsztal( a3, p3);
		} catch (NemFerBeAHelysegbeException e) {
			}
		
		
		List<Asztal> asztalok = szoba.getLehtesegesJoAsztalok(6);
		 
		assertTrue( asztalok.contains(a2) );
		assertFalse( asztalok.contains(a1) );
		
		asztalok = szoba.getLehtesegesJoAsztalok(1);
		
		assertTrue( asztalok.contains(a2) );
		assertTrue( asztalok.contains(a1) );
	}

	@Test
	public void testLehetsegesJoAsztalPoziciok(){

		try {
			szoba.addAsztal( a1, p1);
			szoba.addAsztal( a2, p2);
			szoba.addAsztal( a3, p3);
		} catch (NemFerBeAHelysegbeException e) {
			}
		
		
		 Set<Pozicio> poziciok = szoba.getLehtesegesJoAsztalPoziciok(6);
		 
		assertTrue( poziciok.contains(p2) );
		assertFalse( poziciok.contains(p1) );
		
		poziciok = szoba.getLehtesegesJoAsztalPoziciok(1);
		
		assertTrue( poziciok.contains(p2) );
		assertTrue( poziciok.contains(p1) );
	}
	
	@Test
	public void testKorrektID(){

		try {
			szoba.addAsztal( a1, p1);
			szoba.addAsztal( a2, p2);
			szoba.addAsztal( a3, p3);
		} catch (NemFerBeAHelysegbeException e) {
			}
		
		assertEquals(a1, szoba.getFoglalhatoById(1));
		assertEquals(a2, szoba.getFoglalhatoById(10));
		assertNull(szoba.getFoglalhatoById(100));
		
		assertTrue(szoba.letezoId(1));
		assertFalse(szoba.letezoId(9));
	}
	
	@Test
	public void testKorrektPozicio(){

		try {
			szoba.addAsztal( a1, p1);
			szoba.addAsztal( a2, p2);
			szoba.addAsztal( a3, p3);
		} catch (NemFerBeAHelysegbeException e) {
			}
		
		assertEquals(p1, szoba.getPozicioById(1));
		assertEquals(p2, szoba.getPozicioById(10));
		assertNull(szoba.getPozicioById(100));
	}
	
	
}
