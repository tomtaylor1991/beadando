
package hu.unideb.etterem.model.data;

import hu.unideb.etterem.model.Asztal;

import java.util.List;

/**
 * Az interfész reprezentál egy Éttermi Asztalok adatkezelésének felületét.
 * 
 * @author Szabo Tamas
 *
 */
public interface AsztalDAO {

	/**
	 * Elment egy Asztalokból álló listát.
	 * 
	 * @param asztalok egy Asztalokból álló lista
	 */
	void saveAsztalok(List<Asztal> asztalok);
	
	/**
	 * Visszaad egy Asztalokból álló listát.
	 * 
	 * @return egy Asztalokból álló lista
	 */
	List<Asztal> getAsztalok();
}
