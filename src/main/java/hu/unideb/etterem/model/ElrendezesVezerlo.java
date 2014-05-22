package hu.unideb.etterem.model;

import hu.unideb.etterem.controller.Log;
import hu.unideb.etterem.model.exceptions.NemFerBeAHelysegbeException;
import hu.unideb.etterem.model.exceptions.NincsIlyenAsztalException;
import hu.unideb.etterem.model.exceptions.NincsIlyenHelysegException;


/**
 * Az osztály feladata egy étterem elrendezéseinek vezérlése.
 * 
 * @author Szabo Tamas
 *
 */
public class ElrendezesVezerlo {
	
	/**
	 * Az étterem amelynek berendezéseit ezen osztályon keresztül el lehet érni. 
	 */
	private Etterem etterem;

	/**
	 * Konstruktor {@code ElrendezesVezerlo} objektum példányosításához.
	 * 
	 * @param etterem étterem amelynek berendezéseit ezen osztályon keresztül el lehet majd érni
	 */
	public ElrendezesVezerlo(Etterem etterem) {
		this.etterem = etterem;
	}

	/**
	 * Visszaadja az elrendezés vezérlő által használt éttermet.
	 * 
	 * @return az elrendezés vezérlő által használt étterm
	 */
	public Etterem getEtterem() {
		return etterem;
	}

	/**
	 * Megkeresi az adott szobát és hozzáad egy új asztalt.
	 * 
	 * @param szobaNev melyik szobábahoz adjon asztalt
	 * @param pozicio milyen pozícióra tegye az asztalt
	 * @param szekSzam hány szék tartozzon az asztalhoz
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
	 * Visszaad egy eddig még nem használt egyedi asztal azonosítót.
	 *  
	 * @return eddig még nem használt egyedi asztal azonosító
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
	 * Visszaad egy asztalt az étterem adott szobájából, adott pozíciójában.
	 * @param szobaNev melyik szobában van a keresett asztal
	 * @param pozicio melyik pozícióban van a keresett asztal
	 * @return egy asztalt az étterem adott szobájából, adott pozíciójában, ha létezik ilyen asztal
	 * @throws NincsIlyenHelysegException ha nem létezik szobaNev -el megegyező szoba
	 * @throws NincsIlyenAsztalException ha nincs az adott szobában adott pozíción asztal
	 */
	public Asztal getAsztal(String szobaNev, Pozicio pozicio) throws NincsIlyenHelysegException, NincsIlyenAsztalException{
		Szoba szoba=etterem.getSzoba(szobaNev);
		if( szoba.getAsztalok().containsKey(pozicio) )
			return szoba.getAsztalok().get(pozicio);
		else
			throw new NincsIlyenAsztalException();
	}
	


	/**
	 * Törli adott szoba adott pozícióján lévő asztalt feltéve, hogy az az asztal létezik.
	 * 
	 * @param szobaNev melyik szobában van a keresett asztal
	 * @param pozicio melyik pozícióban van a keresett asztal
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
	 * Törli egy adott nevű szobát az étteremből.
	 * 
	 * @param szobaNev a neve a szobának amelyet törölni kell
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
