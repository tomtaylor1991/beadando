package hu.unideb.etterem.model;

import static org.junit.Assert.*;
import hu.unideb.etterem.model.interfaces.LekerdezhetoRendeles;
import hu.unideb.etterem.model.interfaces.VegosszegKalkulatorSzabaly;

import org.junit.Test;

/**
 * @author TomTaylor
 *
 */
public class VegosszegKalkulatorTest {

	private class Rendeles implements LekerdezhetoRendeles{

		@Override
		public int getVegOsszeg() {
			return 10000;
		}
	}
	
	private class Szabalyzat implements VegosszegKalkulatorSzabaly{

		@Override
		public double getSzorzo() {
			return 2;
		}

		@Override
		public int getBonuszOsszeg() {
			return 2000;
		}
		
	}
	
	@Test
	public void testGetKalkulacio() {
		VegosszegKalkulator kalkulator = new VegosszegKalkulator(new Szabalyzat());
		
		assertEquals( 22000, kalkulator.getKalkulacio(new Rendeles()) ) ;
	}

}
