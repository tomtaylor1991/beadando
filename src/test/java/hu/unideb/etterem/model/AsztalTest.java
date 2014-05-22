package hu.unideb.etterem.model;

import static org.junit.Assert.*;
import hu.unideb.etterem.model.Asztal;


import org.junit.Test;

/**
 * @author Szabo Tamas
 *
 */
public class AsztalTest {

	@Test
	public void testUresAsztalKonstruktorral() {
		Asztal asztal = new Asztal();
		asztal.setAsztalID(1);
		asztal.setSzekekSzama(10);
		
		assertEquals(1, asztal.getID());
		assertEquals(10, asztal.getSzekekSzama());
	}

	@Test
	public void testKonstruktorral() {
		Asztal asztal = new Asztal(2, 20);
		
		assertEquals(2, asztal.getID());
		assertEquals(20, asztal.getSzekekSzama());
	}
	
	@Test
	public void testID() {
		Asztal asztal = new Asztal(2, 20);
		asztal.setAsztalID(100);
		
		assertEquals(100, asztal.getID());
	}
	
	@Test
	public void testSzekek() {
		Asztal asztal = new Asztal(2, 20);
		asztal.setSzekekSzama(1);
		
		assertEquals(1, asztal.getSzekekSzama());
	}
	
}
