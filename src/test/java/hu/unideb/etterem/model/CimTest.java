package hu.unideb.etterem.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author Szabo Tamas
 *
 */
public class CimTest {
	@Test
	public void testCimAttributumok() {
		Cim cim = new Cim("Magyarorszag", "Debrecen", "Kassai ut", 7);
		
		assertEquals( "Magyarorszag", cim.orszag );
		assertEquals( "Debrecen", cim.varos );
		assertEquals( "Kassai ut", cim.utca );
		assertEquals( 7, cim.hazSzam );
	}
}
