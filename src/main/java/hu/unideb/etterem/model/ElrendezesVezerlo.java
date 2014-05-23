package hu.unideb.etterem.model;

import hu.unideb.etterem.controller.Log;
import hu.unideb.etterem.model.exceptions.NemFerBeAHelysegbeException;
import hu.unideb.etterem.model.exceptions.NincsIlyenAsztalException;
import hu.unideb.etterem.model.exceptions.NincsIlyenHelysegException;


/**
 * Az osztály feladata egy {@code Etterem} elrendezéseinek vezérlése.
 * 
 * @author Szabo Tamas
 *
 */
public class ElrendezesVezerlo {
	
	/**
	 * Az {@code Etterem} amelynek berendezéseit ezen osztályon keresztül el lehet érni. 
	 */
	private Etterem etterem;

	/**
	 * Konstruktor {@code ElrendezesVezerlo} objektum példányosításához.
	 * 
	 * @param etterem {@code Etterem} amelynek berendezéseit ezen osztályon keresztül el lehet majd érni
	 */
	public ElrendezesVezerlo(Etterem etterem) {
		this.etterem = etterem;
	}

	/**
	 * Visszaadja az elrendezés vezérlő által használt {@code Ettermet}.
	 * 
	 * @return az elrendezés vezérlő által használt {@code Etterem}
	 */
	public Etterem getEtterem() {
		return etterem;
	}

	/**
	 * Megkeresi az adott {@code Szoba}-t és hozzáad egy új {@code Asztal}-t.
	 * 
	 * @param szobaNev melyik {@code Szoba}-hoz adjon {@code Asztal}-t
	 * @param pozicio milyen {@code Pozicio}-ra tegye az {@code Asztal}-t
	 * @param szekSzam hány szék tartozzon az {@code Asztal}-hoz
	 */
	public void addUjAsztalSzobahoz(String szobaNev, Pozicio pozicio, int szekSzam){
		try {
			Szoba szoba = etterem.getSzoba(szobaNev);
			szoba.addAsztal( new Asztal(
					getKovetkezoAsztalID(),
					szekSzam
					), 
					pozicio
				);
		} catch (NincsIlyenHelysegException e) {
			//LOG
			Log.logger.warn("Új asztal hozzáadása hiba: {}", e.getMessage());
		} catch (NemFerBeAHelysegbeException e) {
			//LOG
			Log.logger.warn("Új asztal hozzáadása hiba: {}", e.getMessage());
		}
	}

	
	/**
	 * Visszaad egy eddig még nem használt egyedi {@code Asztal} azonosítót.
	 *  
	 * @return eddig még nem használt egyedi {@code Asztal} azonosító
	 */
	public int getKovetkezoAsztalID(){
		int max = 0;
		for(Szoba szoba : etterem.getSzobak())
			for(int id : szoba.getIdList())
				if( id > max )
					max = id;
		return max + 1;
	}
	
	
	/**
	 * Visszaad egy {@code Asztal}-t az étterem adott {@code Szoba}-jából, adott {@code Pozicio}-jában.
	 * @param szobaNev melyik {@code Szoba}-ban van a keresett {@code Asztal}
	 * @param pozicio melyik {@code Pozicio}-jában van a keresett {@code Asztal}
	 * @return egy asztalt az {@code Etterem} adott {@code Szoba}-jából, adott @code Pozicio}-jában, ha létezik ilyen {@code Asztal}
	 * @throws NincsIlyenHelysegException ha nem létezik szobaNev -el megegyező {@code Szoba}
	 * @throws NincsIlyenAsztalException ha nincs az adott szobában adott pozíción {@code Asztal}
	 */
	public Asztal getAsztal(String szobaNev, Pozicio pozicio) throws NincsIlyenHelysegException, NincsIlyenAsztalException{
		Szoba szoba=etterem.getSzoba(szobaNev);
		if( szoba.getAsztalok().containsKey(pozicio) )
			return szoba.getAsztalok().get(pozicio);
		else
			throw new NincsIlyenAsztalException();
	}
	


	/**
	 * Törli adott {@code Szoba} adott {@code Pozicio}-jában lévő asztalt feltéve, hogy az az {@code Asztal} létezik.
	 * 
	 * @param szobaNev melyik {@code Szoba} van a keresett {@code Asztal}
	 * @param pozicio melyik {@code Pozicio}-jában  van a keresett {@code Asztal}
	 */
	public void torolAsztal(String szobaNev, Pozicio pozicio){
		try {
			Asztal asztal = getAsztal(szobaNev, pozicio);
			etterem.getSzoba(szobaNev).getAsztalok().remove(pozicio);
		} catch (NincsIlyenHelysegException | NincsIlyenAsztalException e) {
			//LOG
			Log.logger.warn("Asztal törlése hiba: ", e.getMessage());
		}
	}
	
	/**
	 * Törli egy adott nevű {@code Szoba}-t az {@code Etterem}-ből.
	 * 
	 * @param szobaNev a neve a {@code Szoba}-nak amelyet törölni kell
	 */
	public void torolSzoba(String szobaNev){
			Szoba szoba;
			try {
				szoba = etterem.getSzoba(szobaNev);
				etterem.getSzobak().remove(szoba);
			} catch (NincsIlyenHelysegException e) {
				//LOG
				Log.logger.warn("Szoba törlése hiba: ", e.getMessage());
			}
	}
	
}
