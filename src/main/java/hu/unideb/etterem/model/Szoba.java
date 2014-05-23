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
	 * @param szobaID a {@code Szoba} egyedi azonosítója
	 * @param szobaNev a {@code Szoba} neve
	 * @param meretX  a {@code Szoba} egységnyi hossza
	 * @param meretY a {@code Szoba} egységnyi magassága
	 * @param leiras a {@code Szoba} egységnyi leírása
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
	 * Visszaadja, hogy az adott {@code Pozicio} a {@code Szoba} méret korlátain belül van -e.
	 * 
	 * @param poz a keresett pozíció
	 * @return <code>true</code> ha a @code Pozicio} a {@code Szoba}-ban van, <code>false</code> különben
	 */
	private boolean isBelefer(Pozicio poz){
		if(this.meretX>=poz.getX() && this.meretY>=poz.getY())
			return true;
		return false;
	}
	
	/**
	 * Hozzáad a {@code Szoba}-hoz egy új {@code Asztal}-t az adott {@code Pozicio}-n, 
	 * ha már volt ott {@code Asztal}, akkor azt lecseréli. 
	 * 
	 * @param ujAsztal az {@code Asztal} amelyiket a {@code Szoba}-hoz kell adni
	 * @param ujPozicio a {@code Pozicio} amelyen legyen az új {@code Asztal}
	 * @throws NemFerBeAHelysegbeException ha a {@code Szoba}-ban a {@code Pozicio} nem létezik
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
	 * Visszaadja a {@code Szoba}-ban lévő {@code Asztal}-ok pozícióinak halmazát.
	 * 
	 * @return a {@code Szoba}-ban lévő {@code Asztal}-ok pozícióinak halmaza
	 */
	public Set<Pozicio> getPoziciok(){
		return asztalok.keySet();
	}
	
	/**
	 * Visszaadja a {@code Szoba}-ban lévő {@code Asztal}-ok referenciáinak listáját.
	 * 
	 * @return a {@code Szoba}-ban lévő {@code Asztal}-ok referenciáinak listája
	 */
	public List<Asztal> getAsztalLista() {
		return new LinkedList<Asztal>(asztalok.values());
	}

	/**
	 * Visszaadja a {@code Szoba}-ban lévő {@code Asztal}-ok referenciáinak listáját amelyeknél elég ülő hely van.
	 * 
	 * @param minHely korlát, hogy legalább ennyi szabad hellyel rendelkeznie kell az {@code Asztal}-nak
	 * @return a {@code Szoba}-ban lévő {@code Asztal}-ok referenciáinak listáját amelyeknél elég ülő hely van
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
	 * Visszaadja a {@code Szoba}-ban lévő {@code Asztal}-ok {@code Pozicio}-jának
	 *  listáját amelyeknél elég ülő hely van.
	 * 
	 * @param minHely korlát, hogy legalább ennyi szabad hellyel rendelkeznie kell az {@code Asztal}-nak
	 * @return a {@code Szoba}-ban lévő {@code Asztal}-ok {@code Pozicio}-jának listája amelyeknél elég ülő hely van
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
	 * Visszaadja a {@code Szoba}-ban lévő {@code Asztal}-ok egyedi azonosítóinak listáját.
	 * 
	 * @return a {@code Szoba}-ban lévő {@code Asztal}-ok egyedi azonosítóinak listája
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
	 * Visszaadja a {@code Szoba}-ból azt az {@code Asztal}-t, 
	 * amelynek id-ja megyezeik a paraméterként kapott id-val.
	 * 
	 * @param id melyik azonosítójú {@code Asztal}-t keressük
	 * @return {@code Asztal}, amelyiknek id-ja megyezeik a paraméterként kapott id-val, 
	 * ha nincs ilyen asztal, {@code null}-al tér vissza
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
	 * Visszaadja, hogy létezik -e a {@code Szoba}-ban adott id-vel rendelkező {@code Asztal}.
	 * 
	 * @param id a keresett {@code Asztal} id -ja
	 * @return <code>true</code> ha létezik ilyen id-vel rendelkező {@code Asztal} a {@code Szoba}-ban, 
	 * <code>false</code> különben
	 */
	public Boolean letezoId(int id){
		return getIdList().contains(id);
	}
	
	/**
	 * Visszaadja a {@code Szoba}-ban lévő {@code Asztal}-t id -ja alapján, milyen {@code Pozicio}-n van az {@code Asztal}.
	 * 
	 * @param id a keresett {@code Asztal} id -ja
	 * @return a keresett {@code Asztal} {@code Pozicio}-ja, ha létezik ilyen id -jű asztal, null különben
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
	 * Visszaadja a {@code Szoba} egységnyi hosszát.
	 * 
	 * @return a {@code Szoba} egységnyi hossza
	 */
	public int getMeretX() {
		return meretX;
	}

	/**
	 * Beállítja a {@code Szoba} egységnyi hosszát.
	 * 
	 * @param meretX a {@code Szoba} egységnyi hossza
	 */
	public void setMeretX(int meretX) {
		this.meretX = meretX;
	}

	/**
	 * Visszaadja a {@code Szoba} egységnyi magasságát.
	 * 
	 * @return a {@code Szoba} egységnyi magassága
	 */
	public int getMeretY() {
		return meretY;
	}

	/**
	 * Beállítja a {@code Szoba} egységnyi magasságát.
	 * 
	 * @param meretY a {@code Szoba} egységnyi magassága
	 */
	public void setMeretY(int meretY) {
		this.meretY = meretY;
	}

	/**
	 * Visszaadja a {@code Szoba} id-ját.
	 * 
	 * @return a {@code Szoba} ID -ja
	 */
	public int getSzobaID() {
		return szobaID;
	}

	/**
	 * Beállítja a {@code Szoba} id-ját.
	 * 
	 * @param szobaID a {@code Szoba} új ID -ja
	 */
	public void setSzobaID(int szobaID) {
		this.szobaID = szobaID;
	}

	/**
	 * Visszaadja a {@code Szoba} nevét.
	 * 
	 * @return a {@code Szoba} neve
	 */
	public String getSzobaNev() {
		return szobaNev;
	}

	/**
	 * Beállítja a {@code Szoba} nevét.
	 * 
	 * @param szobaNev a {@code Szoba} új neve
	 */
	public void setSzobaNev(String szobaNev) {
		this.szobaNev = szobaNev;
	}

	/**
	 * Visszaadja a {@code Szoba} leírását.
	 * 
	 * @return  a {@code Szoba} leírása
	 */
	public String getLeiras() {
		return leiras;
	}

	/**
	 * Beállítja a {@code Szoba} leírását.
	 * 
	 * @param leiras a {@code Szoba} új leírása
	 */
	public void setLeiras(String leiras) {
		this.leiras = leiras;
	}


	
	/**
	 * Visszaadja a {@code Szoba}  {@code Asztal} és {@code Pozicio}-it.
	 * 
	 * @return  a {@code Szoba}  {@code Asztal} és {@code Pozicio}-i
	 */
	public Map<Pozicio, Asztal> getAsztalok() {
		return asztalok;
	}

	/**
	 * Visszaadja a {@code Szoba} String reprezentációját.
	 * 
	 * @return a {@code Szoba} String reprezentációja
	 * */
	@Override
	public String toString() {
		return "Szoba [szobaID=" + szobaID + ", szobaNev=" + szobaNev
				+ ", leiras=" + leiras + "]";
	}
	
	
}
