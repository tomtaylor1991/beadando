package hu.unideb.etterem.model;

import hu.unideb.etterem.controller.Log;
import hu.unideb.etterem.model.exceptions.NemFerBeAHelysegbeException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Az osztály reprezentál egy Szobát.
 * 
 * @author Szabo Tamas
 *
 */
public class Szoba {
	
	/**
	 * A szoba egységnyi hossza.
	 */
	private int meretX;
	
	/**
	 * A szoba egységnyi magassága.
	 */
	private int meretY;
	
	/**
	 * A szoba egyedi azonosítója.
	 */
	private int szobaID;
	
	/**
	 * A szoba neve.
	 */
	private String szobaNev;
	
	/**
	 * A szoba rövid leírása.
	 */
	private String leiras="-";
	
	/**
	 * HashMap amely tárolja a szobában elhelyezkedő asztalokat, kulcs = Pozíció és érték = Asztal párban. 
	 */
	private Map<Pozicio, Asztal> asztalok;
	
	/**
	 * Konstruktor {@code Szoba} objektum példányosításához.
	 * 
	 * @param szobaID a szoba egyedi azonosítója
	 * @param szobaNev a szoba neve
	 * @param meretX  a szoba egységnyi hossza
	 * @param meretY a szoba egységnyi magassága
	 * @param leiras a szoba egységnyi leírása
	 */
	public Szoba(int szobaID, String szobaNev, int meretX, int meretY, String leiras) {
		this.szobaID = szobaID;
		this.szobaNev = szobaNev;
		this.meretX = meretX;
		this.meretY = meretY;
		this.leiras = leiras;
		this.asztalok = new HashMap<Pozicio, Asztal>();
	}
	
	/**
	 * Konstruktor {@code Szoba} objektum példányosításához.
	 */
	public Szoba(){
		this.szobaID = -1;
		this.szobaNev = "";
		this.meretX = 0;
		this.meretY = 0;
		this.leiras = "";
		this.asztalok = new HashMap<Pozicio, Asztal>();
	}
	
	/**
	 * Visszaadja, hogy az adott pozíció a szoba méret korlátain belül van -e.
	 * 
	 * @param poz a keresett pozíció
	 * @return <code>true</code> ha a pozíció a szobában van, <code>false</code> különben
	 */
	private boolean isBelefer(Pozicio poz){
		if(this.meretX>=poz.getX() && this.meretY>=poz.getY())
			return true;
		return false;
	}
	
	/**
	 * Hozzáad a szobához egy új asztalt az adott pozíción, ha már volt ott asztal, akkor azt lecseréli. 
	 * 
	 * @param ujAsztal az asztal amelyiket a szobához kell adni
	 * @param ujPozicio a pozíció amelyen legyen az új asztal
	 * @throws NemFerBeAHelysegbeException ha a szobában a pozíció nem létezik
	 */
	public void addAsztal(Asztal ujAsztal, Pozicio ujPozicio) throws NemFerBeAHelysegbeException{
		
		if(ujAsztal == null || ujPozicio == null) {
		
			//LOG
			Log.logger.warn("Hiányos adatokkal nem lehet asztalt rendelni szobához! Adatok: {} - {}", ujAsztal, ujPozicio);
			
			return;
		}
		
		if(!isBelefer(ujPozicio))
			throw new NemFerBeAHelysegbeException(ujPozicio);
		
		if(asztalok.containsKey(ujPozicio)){
			//System.out.println("Lecserelt egy asztalt a(z) "+ujPozicio+" pozicion!");
		}
		asztalok.put(ujPozicio, ujAsztal);			
	}
	
	
	/**
	 * Visszaadja a szobában lévő asztalok pozícióinak halmazát.
	 * 
	 * @return a szobában lévő asztalok pozícióinak halmaza
	 */
	public Set<Pozicio> getPoziciok(){
		return asztalok.keySet();
	}
	
	/**
	 * Visszaadja a szobában lévő asztalok referenciáinak listáját.
	 * 
	 * @return a szobában lévő asztalok referenciáinak listája
	 */
	public List<Asztal> getAsztalLista() {
		return new LinkedList<Asztal>(asztalok.values());
	}

	/**
	 * Visszaadja a szobában lévő asztalok referenciáinak listáját amelyeknél elég ülő hely van.
	 * 
	 * @param minHely korlát, hogy legalább ennyi szabad helley rendelkeznie kell az asztalnak
	 * @return a szobában lévő asztalok referenciáinak listáját amelyeknél elég ülő hely van
	 */
	public List<Asztal> getLehtesegesJoAsztalok(int minHely){
		List<Asztal> ret=new LinkedList<Asztal>();
		for(Asztal asztal: getAsztalLista()){
			if( asztal.getSzekekSzama()>=minHely )
				ret.add(asztal);
		}
		return ret;
	}
	
	/**
	 * Visszaadja a szobában lévő asztalok pozícióinak listáját amelyeknél elég ülő hely van.
	 * 
	 * @param minHely korlát, hogy legalább ennyi szabad helley rendelkeznie kell az asztalnak
	 * @return a szobában lévő asztalok pozícióinak listáját amelyeknél elég ülő hely van
	 */
	public Set<Pozicio> getLehtesegesJoAsztalPoziciok(int minHely){
		List<Asztal> lehetsegesAsztalok = getLehtesegesJoAsztalok( minHely );
		
		Set<Pozicio> ret = new HashSet<Pozicio>();
		for(Pozicio poz: getPoziciok()){
			if( lehetsegesAsztalok.contains( asztalok.get(poz) ) )
				ret.add(poz);
		}
		return ret;
	}
	
	/**
	 * Visszaadja a szobában lévő asztalok egyedi azonosítóinak listáját.
	 * 
	 * @return a szobában lévő asztalok egyedi azonosítóinak listája
	 */
	public List<Integer> getIdList(){
		List<Integer> ret= new LinkedList<Integer>();
		for( Pozicio poz: getPoziciok() ){
			Asztal aktualis= asztalok.get(poz);
			ret.add( aktualis.getID() );
		}
		return ret;
	}
	
	/**
	 * Visszaadja a szobából azt az asztalt, amelynek id-ja megyezeik a paraméterként kapott id-val.
	 * 
	 * @param id melyik azonosítójú asztalt keressük
	 * @return Asztal, amelyiknek id-ja megyezeik a paraméterként kapott id-val, ha nincs ilyen asztal, null -al tér vissza
	 */
	public Asztal getFoglalhatoById(int id){
		for( Asztal aktualis: getAsztalLista() ){
			if ( aktualis.getID() == id )
				return aktualis;
		}
		
		//LOG
		Log.logger.warn("Nincs {} id -vel rendelkező asztal a {} szobában!", id, szobaNev);
		
		return null;
	}
	
	/**
	 * Visszaadja, hogy létezik -e a szobában adott id-vel rendelkező asztal.
	 * 
	 * @param id a keresett asztal id -ja
	 * @return <code>true</code> ha létezik ilyen id-vel rendelkező asztal a szobában, <code>false</code> különben
	 */
	public Boolean letezoId(int id){
		return getIdList().contains(id);
	}
	
	/**
	 * Visszaadja a szobában lévő asztalt id -ja alapján, milyen pozíción van az asztal.
	 * 
	 * @param id a keresett asztal id -ja
	 * @return a keresett asztal pozíciója, ha létezik ilyen id -jű asztal, null különben
	 */
	public Pozicio getPozicioById(int id){
		for( Pozicio poz: getPoziciok() ){
			Asztal aktualis= asztalok.get(poz);
			if ( aktualis.getID() == id )
				return poz;
		}
		
		//LOG
		Log.logger.warn("Nincs {} id -vel rendelkező asztal a {} szobában!", id, szobaNev);
		
		return null;
	}
	
	
	
	/**
	 * Visszaadja a szoba egységnyi hosszát.
	 * 
	 * @return a szoba egységnyi hossza
	 */
	public int getMeretX() {
		return meretX;
	}

	/**
	 * Beállítja a szoba egységnyi hosszát.
	 * 
	 * @param meretX a szoba egységnyi hossza
	 */
	public void setMeretX(int meretX) {
		this.meretX = meretX;
	}

	/**
	 * Visszaadja a szoba egységnyi magasságát.
	 * 
	 * @return a szoba egységnyi magassága
	 */
	public int getMeretY() {
		return meretY;
	}

	/**
	 * Beállítja a szoba egységnyi magasságát.
	 * 
	 * @param meretY a szoba egységnyi magassága
	 */
	public void setMeretY(int meretY) {
		this.meretY = meretY;
	}

	/**
	 * Visszaadja a szoba id-ját.
	 * 
	 * @return a szoba ID -ja
	 */
	public int getSzobaID() {
		return szobaID;
	}

	/**
	 * Beállítja a szoba id-ját.
	 * 
	 * @param szobaID  a szoba új ID -ja
	 */
	public void setSzobaID(int szobaID) {
		this.szobaID = szobaID;
	}

	/**
	 * Visszaadja a szoba nevét.
	 * 
	 * @return a szoba neve
	 */
	public String getSzobaNev() {
		return szobaNev;
	}

	/**
	 * Beállítja a szoba nevét.
	 * 
	 * @param szobaNev a szoba új neve
	 */
	public void setSzobaNev(String szobaNev) {
		this.szobaNev = szobaNev;
	}

	/**
	 * Visszaadja a szoba leírását.
	 * 
	 * @return  a szoba leírása
	 */
	public String getLeiras() {
		return leiras;
	}

	/**
	 * Beállítja a szoba leírását.
	 * 
	 * @param leiras a szoba új leírása
	 */
	public void setLeiras(String leiras) {
		this.leiras = leiras;
	}


	
	/**
	 * Visszaadja a szoba  asztalait és pozícióit.
	 * 
	 * @return  a szoba  asztalai és pozíciói
	 */
	public Map<Pozicio, Asztal> getAsztalok() {
		return asztalok;
	}

	/**
	 * Visszaadja a Szoba String reprezentációját.
	 * 
	 * @return a Szoba String reprezentációja
	 * */
	@Override
	public String toString() {
		return "Szoba [szobaID=" + szobaID + ", szobaNev=" + szobaNev
				+ ", leiras=" + leiras + "]";
	}
	
	
}
