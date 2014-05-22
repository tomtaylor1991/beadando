package hu.unideb.etterem.model.data;

import hu.unideb.etterem.model.Nevjegy;

/**
 * Az interfész reprezentál egy étterem elérési adatai adatkezelésének felületét.
 * 
 * @author Szabo Tamas
 *
 */
public interface NevjegyDAO {
	
	/**
	 * Elmenti egy névjegy adatait.
	 * @param nevjegy a nevjegy amelynek adatait kell elmenteni
	 */
	void saveNevjegy( Nevjegy nevjegy );
	
	/**
	 * Visszaad egy beolvasott névjegy objektumot.
	 * 
	 * @return beolvasott névjegy objektum
	 */
	Nevjegy getNevjegy();
}
