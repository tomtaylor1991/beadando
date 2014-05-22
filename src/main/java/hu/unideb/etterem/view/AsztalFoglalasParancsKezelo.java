package hu.unideb.etterem.view;

import hu.unideb.etterem.model.Pozicio;
import hu.unideb.etterem.model.Szoba;

/**
 * Az osztály reprezentál statikus adattagokat amelyek a megjelenítés logikájának működését elősegítik.
 * 
 * @author Szabo Tamas
 *
 */
public class AsztalFoglalasParancsKezelo {
	
	/**
	 * Melyik az aktuális szoba.
	 */
	public static Szoba aktSzoba;
	
	/**
	 * Van -e kijelölve valami?
	 */
	public static Boolean vanKijelolt = false;
	
	/**
	 * Kijelölt elem pozíciója.
	 */
	public static Pozicio kijeloltPozicio;
	
	/**
	 * A kijelölt szoba neve. 
	 */
	public static String kijeloltSzoba;	
	
	/**
	 * Egy frissíthető szobákat tartalmazó nézet referenciája.
	 */
	public static IFrissithetoSzobaNezet frissithetoSzobaNezet = null;
	
	/**
	 * Frissíti a megadott szoba nézet kinézetét ha a referenciája nem null.
	 */
	public static void frissitSzobat(){
		if(frissithetoSzobaNezet != null)
			frissithetoSzobaNezet.szobatFrissitParancs();
	}
}
