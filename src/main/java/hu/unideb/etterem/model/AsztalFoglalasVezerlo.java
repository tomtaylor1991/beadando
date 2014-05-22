package hu.unideb.etterem.model;

import hu.unideb.etterem.controller.Log;
import hu.unideb.etterem.model.exceptions.NincsIlyenHelysegException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Az osztály feladata az {@code AsztalFoglalások} vezérlése és elemzése.
 * 
 * @author Szabo Tamas
 *
 */
public class AsztalFoglalasVezerlo {
	

	
	/**
	 * HashMap amely tartalmazza az {@code AsztalFoglalásokat}, azokat pedig egyedi azonosítójú (@code int) kulcsokon keresztül lehet elérni. 
	 */
	private Map< Integer, AsztalFoglalas > foglalasok;
	
	/**
	 * Az Étterem ahol az asztalfoglalás történik, csak a rövidebb jelölés miatt van ez a referencia, ElrendezéVezérlőn keresztül érjük el. 
	 */
	private Etterem etterem;
	
	/**
	 * Az {@code AsztalFoglalas} -hoz társítható ételeket tartalmazó menü.
	 */
	private Menu menu;
	
	/**
	 * Az elrendezéseket kezelő objektum, pl.: étterem, szobák, asztal.
	 */
	private ElrendezesVezerlo elrendezesVezerlo;
	
	/**
	 * Az {@code AsztalFoglalások} végösszegének kiszámításáért felelős objektum.
	 */
	private VegosszegKalkulator kalkulator;
		
	/**
	 * Az adott befejezett {@code AsztalFoglalás}-ok összesített értéke.
	 */
	private int bevetel = 0;
	
	/**
	 * Konstruktor {@code AsztalFoglalasVezerlo} objektum példányosításához.
	 * 
	 * @param elrendezesVezerlo {@code ElrendezesVezerlo} objektum, amely kezeli az asztalfoglalások helységeit
	 * @param menu {@code AsztalFoglalás-hoz} társítható {@code Etel-eket} tartalmazó {@code Menü}
	 */
	public AsztalFoglalasVezerlo( ElrendezesVezerlo elrendezesVezerlo, Menu menu ) {
		this.elrendezesVezerlo = elrendezesVezerlo;
		this.etterem=elrendezesVezerlo.getEtterem();
		this.menu = menu;
		
		this.foglalasok=new HashMap<Integer, AsztalFoglalas>();
		
		this.kalkulator = new VegosszegKalkulator(new EtteremVegosszegSzabalyzat());
	}
	
	/**
	 * Adott rendelés ID -val rendelkező asztalfoglalás végösszegét hozzáadja a bevételekhez és tölri a foglalást a rendszerből.
	 *
	 * @param id a befejezni kívánt {@code AsztalFoglalás} rendelés id-ja
	 */
	public void rendezSzamlat(int id){
		AsztalFoglalas foglalas = getFoglalas(id);
		if(foglalas == null) return;
		
		int vegosszeg = getVegosszeg(id);
		bevetel += vegosszeg;
		
		torolFoglalas(id);
		
		//LOG
		Log.logger.info("Asztalfoglalás kifizetése: {} - {}", foglalas.getFelelos().getNev(), vegosszeg);

	}
	
	/**
	 * Visszaadja az adott rendelés ID -val rendelkező asztalfoglalás végösszegét.
	 * 
	 * @param id a lekérdezni kívánt {@code AsztalFoglalás} rendelés id-ja
	 * @return adott rendelés ID -val rendelkező {@code AsztalFoglalás} végösszege
	 */
	public int getVegosszeg(int id){
		AsztalFoglalas foglalas = getFoglalas(id);
		if(foglalas == null) return 0;
		return kalkulator.getKalkulacio(foglalas);
	}
	
	/**
	 * Visszaadja, hogy egy asztal jelentleg folalt e.
	 * @param asztal az asztal referenciája amelyre keressük, jelenleg foglalt -e
	 * @return true, ha foglalt, false, ha nem foglalt
	 */
	public boolean isFoglaltAsztal(Asztal asztal){
		for( AsztalFoglalas asztalFoglalas: foglalasok.values() ){
			if(asztalFoglalas.getFoglaltAsztal().equals(asztal))
				return true;
		}
		return false;
	}
	
	/**
	 * Hozzáad egy új {@code AsztalFoglalást} a vezérlőhöz, feltéve, hogy épp nem üllnek az adott asztalnál.
	 * 
	 * @param id az új {@code AsztalFoglalás} egyedi rendelés ID -ja
	 * @param asztalFoglalas egy {@code AsztalFoglalás} reprezentáló objektum
	 */
	public void addAsztalFoglalas(int id, AsztalFoglalas asztalFoglalas) {
		if( ! isFoglaltAsztal(asztalFoglalas.getFoglaltAsztal()) )
		{
			this.foglalasok.put( id, asztalFoglalas );
						
			//LOG
			Log.logger.info("Új asztalfoglalás létrehozása: {} - {} - {} számlaszám", 
					id, asztalFoglalas.getFelelos().getNev(), asztalFoglalas.getFelelos().getSzamlaSzam());
		}
	}
	
	/**
	 * Hozzáad egy új {@code AsztalFoglalás} a vezérlőhöz, feltéve, hogy épp nem üllnek az adott asztalnál, az azonosítót automatikusan generálja.
	 * 
	 * @param asztalFoglalas egy {@code AsztalFoglalást} reprezentáló objektum
	 */
	public void addAsztalFoglalas( AsztalFoglalas asztalFoglalas ) {
		if( ! isFoglaltAsztal(asztalFoglalas.getFoglaltAsztal()) ){
			int id = getNextId();
			this.foglalasok.put(  id, asztalFoglalas );
			
			//LOG
			Log.logger.info("Új asztalfoglalás létrehozása: {} - {} - {} számlaszám", 
					id, asztalFoglalas.getFelelos().getNev(), asztalFoglalas.getFelelos().getSzamlaSzam());
		}
	}
	
	
	/**
	 * Visszaad egy egyedi rendelés ID -t ami még nem szerepel mint foglalás ID.
	 * 
	 * @return egy egyedi rendelés ID
	 */
	protected int getNextId(){
		Set<Integer> lista = getFoglalasIdLista();
		if(lista.size() == 0)
			return 1;
		
		int max = Collections.max(lista);

		return max+1;
	}
	
	/**
	 * Visszaadja azt az {@code AsztalFoglalást}, amely rendelkezik az egyedi rendelés id -val.
	 * 
	 * @param id az lekérdezni kívánt {@code AsztalFoglalás} egyedi rendelés ID -ja
	 * @return id az lekérdezni kívánt {@code AsztalFoglalás} egyedi rendelés ID -ja, ha létezik ilyen foglalás, különben null
	 */
	public AsztalFoglalas getFoglalas(int id) {
		if(getFoglalasIdLista().contains(id))
			return 
				foglalasok.get( id );
		else
			return 
				null;
	}

	/**
	 * Visszaadja az {@code AsztalFoglalások} azonosításául szolgáló id -k halmazát.
	 * 
	 * @return az {@code AsztalFoglalás} azonosításául szolgáló id -k halmaza
	 */
	public Set<Integer> getFoglalasIdLista() {
		return foglalasok.keySet();
	}
	
	/**
	 * Törli az adott id -vel rendelkező {@code AsztalFoglalást}.
	 * 
	 * @param id a törölni kívánt {@code AsztalFoglalás} egyedi rendelés ID -ja
	 */
	public void torolFoglalas(int id){
		try{
		//LOG
			String nev = getFoglalas(id).getFelelos().getNev();
			Log.logger.info("Asztalfoglalás törlése: {}", nev);
		//LOG
		
		foglalasok.remove(id);
		} catch(NullPointerException e){
			Log.logger.warn("Nincs ilyen id jű asztalfoglalás: {} - {}", id, e.getMessage());
		}
	}
	
	/**
	 * Visszaadja mely asztalok vannak adott helységben éppen foglalva.
	 * 
	 * @param helysegNev melyik szobában keressen foglalt asztalokat
	 * @return adott szobában lévő foglalt asztalok listája
	 */
	public List<Asztal> getFoglaltAsztalok(String helysegNev){
		List<Asztal> ret = new LinkedList<Asztal>();
		try{
			//System.out.println(getFoglalasIdLista().toString());
			Szoba szoba = elrendezesVezerlo.getEtterem().getSzoba(helysegNev);
			for(int id:getFoglalasIdLista()){
				int asztalId = getFoglalas(id).getFoglaltAsztal().getID();
				if(szoba.letezoId(asztalId)){
					//System.out.println((Asztal)getFoglalas(id).getFoglaltHely());
					ret.add(getFoglalas(id).getFoglaltAsztal());
				}
			}
		}
		catch(NullPointerException e){
			//LOG
			Log.logger.warn("Hibas foglaltsagi adatok lekerdezese, nincs ilyen nevű szoba: {} - {}", 
					helysegNev, e.getMessage() );
			
		} catch (NincsIlyenHelysegException e) {

			//LOG
			Log.logger.warn("Hibas foglaltsagi adatok lekerdezese, nincs ilyen nevű szoba: {} - {}", 
					helysegNev, e.getMessage() );
		}
		return ret;
	}
	
	/**
	 * Visszaadja mely pozíciókon vannak adott helységben éppen foglalva asztalok.
	 * 
	 * @param szobaNev melyik szobában keressen foglalt asztalokat
	 * @return adott szobában lévő foglalt asztalok pozícióinak listája
	 */
	public List<Pozicio> getFoglaltAsztalokPozicioi(String szobaNev){
		List<Asztal> foglaltAsztalok = getFoglaltAsztalok(szobaNev);

		//System.out.println("Foglalt asztalok a szobaban: "+szobaNev+ ": " + foglaltAsztalok.toString() );
		
		List<Pozicio> ret = new LinkedList<Pozicio>();
		for(Asztal asztal:foglaltAsztalok){
			try {
				ret.add( etterem.getSzoba(szobaNev).getPozicioById(asztal.getID()) );
			} catch (NincsIlyenHelysegException e) {
				//LOG
				Log.logger.warn("Hibas foglaltsagi pozicio adatok lekerdezese, nincs ilyen nevű szoba: {} - {}", 
						szobaNev, e.getMessage() );
			}
		}

		return ret;
	}
	
	/**
	 * Visszaadja, hogy adott {@code Szoba}, adott {@code Pozícióján} van -e foglalt {@code Asztal} adott pillanatban.
	 * 
	 * @param szobaNev melyik {@code Szobában} keressen foglalt asztalt
	 * @param pozicio melyik {@code Pozícióján} keressen foglalt asztalt
	 * @return {@code true}, ha van foglalt {@code Asztal} adott {@code Szobában}, adott {@code Pozícióján}, {@code false} különben
	 */
	public boolean isFoglaltPozicionLevoAsztal(String szobaNev, Pozicio pozicio){
		return getFoglaltAsztalokPozicioi(szobaNev).contains(pozicio);
	}
	
	/**
	 * Visszaadja egy asztal ID -ját ha foglalt, szoba neve és asztal pozíciója alapján.
	 * 
	 * @param szobaNev  szobaNev melyik szobában keressen foglalt asztalt
	 * @param pozicio melyik pozíción keressen foglalt asztalt
	 * @return asztal id, ha létezik ilyen asztal, 0 különben
	 */
	public int getIdByPozicio(String szobaNev, Pozicio pozicio){

		try {
			Szoba szoba = etterem.getSzoba(szobaNev);
			
			Asztal asztal = szoba.getAsztalok().get(pozicio);
			
			if(asztal != null){
				for(int id:getFoglalasIdLista()){
					if(getFoglalas(id).getFoglaltAsztal().equals(asztal))
						return id;
				}
			}
			return 0;
		} catch (NincsIlyenHelysegException e) {
			//LOG
			Log.logger.warn("Hibas foglaltsagi pozicio adatok lekerdezese, nincs foglalt asztal adott pozícioón a szobában: {} {} - {}", 
					szobaNev, pozicio, e.getMessage() );
			
			return 0;
		}		
	}
	
	/**
	 * Visszaadja mely {@code Menu-ből} rendelhetnek.
	 * 
	 * @return a rendelhető {@code Etel-eket} tartamazó {@code Meu}
	 */
	public Menu getMenu() {
		return menu;
	}

	/**
	 * Visszaadja az elrendezéseket vezérlő objektumot.
	 * 
	 * @return az elrendezéseket vezérlő objektum
	 */
	public ElrendezesVezerlo getElrendezesVezerlo() {
		return elrendezesVezerlo;
	}

	/**
	 * Visszaadja mennyi a pillanatnyi bevétele a foglalások után.
	 * 
	 * @return a pillanatnyi bevétel ({@code int} Ft)
	 */
	public int getBevetel() {
		return bevetel;
	}
	
	
}
