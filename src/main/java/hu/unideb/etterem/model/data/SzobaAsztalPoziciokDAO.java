package hu.unideb.etterem.model.data;

import hu.unideb.etterem.model.Szoba;

import java.util.List;

/**
 * Az interfész reprezentál Szobák és bennük lévő Asztalok kapcsolatának adatkezelésének felületét.
 * 
 * @author Szabo Tamas
 *
 */
public interface SzobaAsztalPoziciokDAO {
	
	/**
	 * Elmenti Szobák és bennük lévő Asztalok kapcsolatát.
	 * 
	 * @param szobak melyik szobakat kell menteni
	 */
	void saveAsztalPoziciok( List<Szoba> szobak );
	
	/**
	 * Visszadja Szobák és bennük lévő Asztalok kapcsolatát.
	 * 
	 * @return Szobák és bennük lévő Asztalok kapcsolata
	 */
	List<int[]> getPoziciok();
}
