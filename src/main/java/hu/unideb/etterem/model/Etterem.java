package hu.unideb.etterem.model;

import hu.unideb.etterem.controller.Log;
import hu.unideb.etterem.model.exceptions.NincsIlyenHelysegException;

import java.util.LinkedList;
import java.util.List;



/**
 *  Az osztály reprezentál egy éttermet.
 * 
 * @author Szabo Tamas
 *
 */
public class Etterem {
	
	/**
	 * Az étteremhez tartozó azonosító dokumentumok és elérhetőségei.
	 */
	public Nevjegy nevjegy = new Nevjegy();
	
	/**
	 *Az étteremhez tartozó szobák. 
	 */
	private List<Szoba> szobak;
	
	/**
	 * Konstruktor {@code Etterem} objektum példányosításához.
	 */
	public Etterem() {
		szobak=new LinkedList<Szoba>();
	}
	
	/**
	 * Hozzáad az étteremhez egy új szobát.
	 * 
	 * @param szoba étterem egy új szobája.
	 */
	public void addSzoba(Szoba szoba){
		if(szoba != null)
			szobak.add(szoba);
	}
	

	/**
	 * Visszaad egy, az étteremben lévő szobát.
	 * @param szobaNev az étterem egyik szobájának neve
	 * @return egy étteremben lévő szoba
	 * @throws NincsIlyenHelysegException ha nincs az étteremben a megadott névvel megeggyező nevű szoba 
	 */
	public Szoba getSzoba(String szobaNev) throws NincsIlyenHelysegException{
		for(Szoba szoba: szobak)
			if( szoba.getSzobaNev().equals(szobaNev))
				return szoba;
		throw new NincsIlyenHelysegException(szobaNev);
	}

	/**
	 * Visszaadja String Listába szedve az étteremben található összes szoba nevét.
	 * 
	 * @return String Listába szedve az étteremben található összes szoba neve
	 */
	public List<String> getSzobaNevek() {
		List<String> ret=new LinkedList<String>();
		for( Szoba szoba: szobak )
			ret.add( szoba.getSzobaNev() );
		return ret;
	}
	

	/**
	 * Visszaadja egy szoba nevét az etteremben, amelyik id-ja megegyezik a paraméterül kapott id-val.
	 * 
	 * @param id melyik id -val rendelkező szoba nevét akarjuk lekérdezni
	 * @return egy szoba neve az etteremben ha létező szobanév, különben null -t ad vissza
	 */
	public String getSzobaNevById(int id) {
		for(Szoba szoba: szobak) {
			if( szoba.getSzobaID() == id )
				return szoba.getSzobaNev();
		}
		
		//LOG
		Log.logger.warn("Egyik szoba sem rendelkezik a megadott ({}) id -vel! ", id);
		
		return null;
		
	}
	


	/**
	 * Visszaadja az étteremben lévő szobák referenciáit Listában.
	 * 
	 * @return az étteremben elhelyezkedő szobák
	 */
	public List<Szoba> getSzobak() {
		return szobak;
	}



	/**
	 * Visszaadja az étterem String reprezentációját.
	 * 
	 * @return az étterem String reprezentációja, névjegy, szobák
	 */
	public String toString() {
		return "Etterem [nevjegy=" + nevjegy + ", szobak=" + szobak + "]";
	}
	
	
}
