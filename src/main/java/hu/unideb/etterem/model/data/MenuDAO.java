
package hu.unideb.etterem.model.data;

import hu.unideb.etterem.model.Menu;

/**
 * Az interfész reprezentál egy Éttermi Menü adatkezelésének felületét.
 * 
 * @author Szabo Tamas
 *
 */
public interface MenuDAO {
	
	/**
	 * Elment egy Menüt.
	 * 
	 * @param menuObj az elementeni kívánt Menu referenciája
	 */
	void saveMenu(Menu menuObj );
	
	/**
	 * Visszaad egy Menüt.
	 * 
	 * @return a betöltött Menüt
	 */
	Menu getMenu();
}
