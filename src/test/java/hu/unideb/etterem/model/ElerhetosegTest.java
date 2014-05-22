package hu.unideb.etterem.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Szabo Tamas
 *
 */
public class ElerhetosegTest {
	private Cim cim;
	private Elerhetoseg elerhetoseg;
	
    @Before
    public void beforeAllTests() {
    	cim = new Cim("Magyarorszag", "Debrecen", "Kassai ut", 7);
    }
    
	@Test
	public void testParameteresKonstruktor() {
		Elerhetoseg elerhetoseg2 = new Elerhetoseg();
		elerhetoseg2.cim = cim;
		elerhetoseg2.email = "email@email.info";
		elerhetoseg2.mobil = "1231231";
		
		elerhetoseg = new Elerhetoseg( cim , "email@email.info", "1231231");
		
		assertEquals( cim, elerhetoseg.cim );
		assertEquals( "email@email.info", elerhetoseg.email );
		assertEquals( "1231231", elerhetoseg.mobil );
		
		assertEquals(elerhetoseg.cim, elerhetoseg2.cim);
		assertEquals(elerhetoseg.email, elerhetoseg2.email);
		assertEquals(elerhetoseg.mobil, elerhetoseg2.mobil);
	}

}
