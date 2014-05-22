package hu.unideb.etterem.controller;


import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import hu.unideb.etterem.model.Asztal;
import hu.unideb.etterem.model.Etterem;
import hu.unideb.etterem.model.Pozicio;
import hu.unideb.etterem.model.Szoba;
import hu.unideb.etterem.model.data.AsztalDAO;
import hu.unideb.etterem.model.data.NevjegyDAO;
import hu.unideb.etterem.model.data.SzobaAsztalPoziciokDAO;
import hu.unideb.etterem.model.data.SzobaDAO;
import hu.unideb.etterem.model.data.VasarlasDAO;
import hu.unideb.etterem.model.data.impl.AsztalDOM;
import hu.unideb.etterem.model.data.impl.NevjegyJSON;
import hu.unideb.etterem.model.data.impl.SzobaAsztalPoziciokDOM;
import hu.unideb.etterem.model.data.impl.SzobaKezeloDOM;
import hu.unideb.etterem.model.data.impl.VasarlasDB;
import hu.unideb.etterem.model.exceptions.NemFerBeAHelysegbeException;
import hu.unideb.etterem.model.exceptions.NincsIlyenHelysegException;
import hu.unideb.etterem.model.interfaces.SzamlazasiFelelos;

/**
 * Az osztály feleős egy Étterem elemeinek inicializálásáért.
 * 
 * @author Szabo Tamas
 *
 */
public class EtteremAdatkezelo {
	
	/**
	 * Az asztalok adatait tároló xml fájl elérési útja.
	 */
	private static final String ASZTAL_SRC = "data/xml/asztalok.xml";
	
	/**
	 * A szobák adatait tároló xml fájl elérési útja.
	 */	
	private static final String SZOBA_SRC = "data/xml/szobak.xml";
	
	/**
	 * Az asztalok és szobák közötti kapcsolat adatait tároló xml fájl elérési útja.
	 */	
	private static final String ASZTAL_SZOBA_KAPCSOLAT_SRC = "data/xml/asztal_poziciok.xml";
	
	/**
	 * Az asztalok adatit tároló xml fájl elérési útja.
	 */	
	private static final String NEVJEGY_SRC = "/data/json/nevjegy.json";
	
	/**
	 * Asztal kezelés felületét meghatározó interfész.
	 */
	private static AsztalDAO asztalDAO;
	
	/**
	 * Szoba kezelés felületét meghatározó interfész.
	 */	
	private static SzobaDAO szobaDAO;
	
	/**
	 * Asztal és Szoba kapcsolatainak kezelés felületét meghatározó interfész.
	 */
	private static SzobaAsztalPoziciokDAO szobaAsztalPoziciokDAO;
	
	/**
	 * Névjegy kezelés felületét meghatározó interfész.
	 */
	private static NevjegyDAO nevjegyDAO;
	
	/**
	 * Vásárlások kezelésének felületét meghatározó interfész.
	 */
	private static VasarlasDAO vasarlasDAO;

	/**
	 * Az osztály statikus Étterem objektum kezelésének referenciája.
	 */
	private static Etterem etterem;
	
	static {
		asztalDAO = new AsztalDOM(ASZTAL_SRC);
		szobaDAO = new SzobaKezeloDOM(SZOBA_SRC);
		szobaAsztalPoziciokDAO = new SzobaAsztalPoziciokDOM(ASZTAL_SZOBA_KAPCSOLAT_SRC);
		nevjegyDAO = new NevjegyJSON(NEVJEGY_SRC);
		vasarlasDAO = new VasarlasDB();
		
		etterem=new Etterem();
	}
	
	/**
	 * Privát {@code EtteremAdatkezelo} konstruktor.
	 */
	private EtteremAdatkezelo(){}

	/**
	 * Beolvassa és visszaadja az osztály statikus Étterem referenciáját.
	 * 
	 * @return  az osztály statikus Étterem referenciája
	 */
	public static Etterem getEtterem(){
		
		beolvas();
		
		return etterem;
	}
	
	/**
	 * Elmenti az étterem asztalait, szobáit, azok kapcsolatait illetve a névjegyet.
	 */
	public static void ElmentEtterem(){
		//Összes asztal kigyűjtése
		List<Asztal> osszAsztal = new LinkedList<Asztal>();
		for(Szoba szoba : etterem.getSzobak()){
			for(Asztal asztal : szoba.getAsztalLista() )
				osszAsztal.add(asztal);
		}
		
		//LOG
		Log.logger.info("Étterem adatok: asztalok, szobák mentésének elindítása!");
		
		//Mentések
		asztalDAO.saveAsztalok(osszAsztal);
		szobaDAO.saveSzoba( etterem.getSzobak() );
		szobaAsztalPoziciokDAO.saveAsztalPoziciok( etterem.getSzobak() );
	}
	
	/**
	 * Elmenti egy vásárlás adatait fájlba.
	 * 
	 * @param szamlazasiFelelos ki volt a felelős személy
	 * @param datum mikor történt a vásárlás
	 * @param vegosszeg mekkora értékben történt vásárlás
	 */
	public static void elmentVasarlas(SzamlazasiFelelos szamlazasiFelelos, Date datum, int vegosszeg){
		//LOG
		Log.logger.info("Vásárlás adatok mentésének elindítása: {} - {} Ft", szamlazasiFelelos.getNev(), vegosszeg);
		
		vasarlasDAO.saveVasarlas(szamlazasiFelelos, datum, vegosszeg);
	}

	/**
	 * Beolvas fájlból étterem elemeinek adatait.
	 */
	protected static void beolvas() {
		
		//LOG
		Log.logger.trace("Étterem adatok: asztalok, szobák beolvasásának elindítása!");
		
		etterem.nevjegy = nevjegyDAO.getNevjegy();
		List<Szoba> szobak= szobaDAO.getSzobak();
		List<Asztal> asztalok = asztalDAO.getAsztalok();
		List<int[]> poziciok = szobaAsztalPoziciokDAO.getPoziciok();
		
		//LOG
		Log.logger.trace("Étterem inicializálása!");
		
		init(szobak, asztalok, poziciok);
	}
	
	/**
	 * Inicializál egy éttermet saját inicializáló logika illetve a paraméterként megkapott adatok alapján.
	 * 
	 * @param szobak mely szobák legyenek az étteremben
	 * @param asztalok milyen asztalok legyenek az étteremben
	 * @param poziciok az asztalok és szobák közötti kapcsolatot reprezentálja, 
	 * 		[0] = asztalId, [1] = szobaId, [2] - asztal x koordinátája, [3] - asztal y koordinátája
	 */
	private static void init(List<Szoba> szobak, List<Asztal> asztalok, List<int[]> poziciok){
		//1. init helységek
		for(Szoba sz : szobak){
			etterem.addSzoba(sz);
		}
		
		for(int[] poz: poziciok){
			//getHelysegNevById
			int asztalID = poz[0];
			int szobaID = poz[1];
			int x = poz[2];
			int y = poz[3];
			String helysegNev = etterem.getSzobaNevById(szobaID);
			//System.out.println(helysegNev);
			if(helysegNev != null){
				Asztal asztal = getAsztalById(asztalok, asztalID);
				if(asztal != null){
				
						try {
							etterem.getSzoba(helysegNev).addAsztal(asztal, new Pozicio(x, y));
						} catch (NemFerBeAHelysegbeException e) {

						} catch (NincsIlyenHelysegException e) {

						}
				}
			}
				
		}
		
		//LOG
		Log.logger.trace("Étterm inicializálásának befejezése!");
		
	}
	
	
	/**
	 * Adott asztal listából visszaadja melyik asztal rendelkezik a paraméterül kapott id -val.
	 * 
	 * @param asztalok asztal lista amelyben keresni kell
	 * @param id melyik id -t keressük
	 * @return Asztal, ha létezik ilyen id-val rendelkező, null különben
	 */
	private static Asztal getAsztalById(List<Asztal> asztalok, int id){
		for(Asztal a:asztalok){
			if(a.getID()==id)
				return a;
		}
		return null;
	}
}
