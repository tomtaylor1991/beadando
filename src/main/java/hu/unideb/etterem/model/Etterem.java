package hu.unideb.etterem.model;

import hu.unideb.etterem.controller.Log;
import hu.unideb.etterem.model.exceptions.NincsIlyenHelysegException;

import java.util.LinkedList;
import java.util.List;



/**
 *  Az osztály reprezentál egy éttermet, amelynek vannak szobái amiket tárolni tud.
 * 
 * @author Szabo Tamas
 *
 */
public class Etterem {
	
	/**
	 * Az {@code Etterem}-hez tartozó azonosító dokumentumok és elérhetőségei.
	 */
	public Nevjegy nevjegy = new Nevjegy();
	
	/**
	 *Az {@code Etterem}-hez tartozó {@code Szoba}-k. 
	 */
	private List<Szoba> szobak;
	
	/**
	 * Konstruktor {@code Etterem} objektum példányosításához.
	 */
	public Etterem() {
		szobak=new LinkedList<Szoba>();
	}
	
	/**
	 * Hozzáad az {@code Etterem}-hez egy új {@code Szoba}-t.
	 * 
	 * @param szoba {@code Etterem} egy új {@code Szoba}-ja.
	 */
	public void addSzoba(Szoba szoba){
		if(szoba != null)
			szobak.add(szoba);
	}
	

	/**
	 * Visszaad egy, az {@code Etterem}-ben lévő {@code Szoba}-t.
	 * @param szobaNev az {@code Etterem} egyik {@code Szoba}-jának neve
	 * @return egy {@code Etterem}-ben lévő {@code Szoba}
	 * @throws NincsIlyenHelysegException ha nincs az {@code Etterem}-ben a megadott névvel megeggyező nevű {@code Szoba} 
	 */
	public Szoba getSzoba(String szobaNev) throws NincsIlyenHelysegException{
		for(Szoba szoba: szobak)
			if( szoba.getSzobaNev().equals(szobaNev))
				return szoba;
		throw new NincsIlyenHelysegException(szobaNev);
	}

	/**
	 * Visszaadja String Listába szedve az {@code Etterem}-ben található összes {@code Szoba} nevét.
	 * 
	 * @return String Listába szedve az {@code Etterem}-ben található összes {@code Szoba} neve
	 */
	public List<String> getSzobaNevek() {
		List<String> ret=new LinkedList<String>();
		for( Szoba szoba: szobak )
			ret.add( szoba.getSzobaNev() );
		return ret;
	}
	

	/**
	 * Visszaadja egy {@code Szoba} nevét az etteremben, amelyik id-ja megegyezik a paraméterül kapott id-val.
	 * 
	 * @param id melyik id -val rendelkező {@code Szoba} nevét akarjuk lekérdezni
	 * @return egy szoba neve az {@code Etterem}-ben ha létező {@code Szoba-}név, különben {@code null}-t ad vissza
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
	 * Visszaadja az {@code Etterem}-ben lévő {@code Szoba}-k referenciáit Listában.
	 * 
	 * @return az {@code Etterem}-ben elhelyezkedő {@code Szoba}-k
	 */
	public List<Szoba> getSzobak() {
		return szobak;
	}



	/**
	 * Visszaadja az {@code Etterem} String reprezentációját.
	 * 
	 * @return az {@code Etterem} String reprezentációja
	 */
	public String toString() {
		return "Etterem [nevjegy=" + nevjegy + ", szobak=" + szobak + "]";
	}
	
	
}
