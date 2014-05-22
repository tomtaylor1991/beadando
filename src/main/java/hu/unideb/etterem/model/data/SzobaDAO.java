package hu.unideb.etterem.model.data;

import hu.unideb.etterem.model.Szoba;

import java.util.List;


/**
 * Az interfész reprezentál egy Éttermi Szoba adatkezelésének felületét.
 * 
 * @author Szabo Tamas
 *
 */
public interface SzobaDAO {
	
	/**
	 * Elmenti egy szobákkal teli listát.
	 * 
	 * @param szobak a szobákkal teli lista amelyet el kell menteni
	 */
	void saveSzoba(List<Szoba> szobak);
	
	/**
	 * Visszaad egy szobákból álló listát.
	 * 
	 * @return egy szobákból álló lista
	 */
	List<Szoba> getSzobak();
}
