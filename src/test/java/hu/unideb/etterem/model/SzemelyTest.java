package hu.unideb.etterem.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Szabo Tamas
 *
 */
public class SzemelyTest {

	private Cim cim;

    @Before
    public void beforeAllTests() {
    	cim = new Cim("Magyarorszag", "Debrecen", "Kassai ut", 7);
    }
    
	@Test
	public void testGetters() {
		Szemely szemely = new Szemely("Szabo Tamas", cim.toString() , "OTP 123129");
		
		assertEquals( cim.toString(), szemely.getCim() );
		assertEquals( "Szabo Tamas", szemely.getNev() );
		assertEquals( "OTP 123129", szemely.getSzamlaSzam() );
	}

}
