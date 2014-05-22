package hu.unideb.etterem.model;

import hu.unideb.etterem.controller.Log;
import hu.unideb.etterem.model.exceptions.NincsElegHelyException;
import hu.unideb.etterem.model.interfaces.LekerdezhetoRendeles;
import hu.unideb.etterem.model.interfaces.SzamlazasiFelelos;
import hu.unideb.etterem.model.interfaces.Vasarolhato;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * Az osztály reprezentál egy Asztal foglalást.
 * 
 * @author Szabo Tamas
 *
 */
public class AsztalFoglalas implements LekerdezhetoRendeles{
	
	/**
	 *Melyik {@code Asztal}nál van a foglalás. 
	 */
	private Asztal foglaltAsztal;
	
	/**
	 *Az időpont amikor leadták az asztalfoglalást. 
	 */
	private Date idopont;
	
	/**
	 * Hány széken üllnek a foglalásnál.
	 */
	private int foglaltSzekekSzama;
	
	/**
	 * A számlázáskor a felelős {@code Szemely}. 
	 */
	private SzamlazasiFelelos felelos;
	
	/**
	 * Miket rendeltek a foglalás ideje alatt: kulcs = {@link hu.unideb.etterem.model.Etel}, érték = darabszám ({@code int}). 
	 */
	private Map<Etel, Integer> rendeltek;
	
	/**
	 * Konstruktor {@code AsztalFoglalas} objektum példányosításához.
	 * 
	 * @param asztal melyik asztalnál van foglalás
	 * @param felelos ki a számlázási felelős
	 * @param uloHelyIgeny hány helyre igénylik az asztal foglalást
	 * @throws NincsElegHelyException ha nincs az adott asztalnál elég szék
	 */
	public AsztalFoglalas(Asztal asztal, SzamlazasiFelelos felelos, int uloHelyIgeny) 
						throws NincsElegHelyException { 
		this.felelos = felelos;
		this.foglaltAsztal = asztal;
		this.foglalSzeket(uloHelyIgeny);
		
		rendeltek= new HashMap<Etel, Integer>();
		
		//DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		idopont = new Date();
		//System.out.println(dateFormat.format(date));
	}
	
	/**
	 * Visszaadja a rendelés végösszegét.
	 * 
	 * @return a rendelés végösszege ({@code int})
	 */
	public int getVegOsszeg(){
		int osszeg = 0;
		for(Etel etel : rendeltek.keySet()){
			osszeg += rendeltek.get(etel) * etel.getAr();
		}
		return osszeg;
	}
	
	/**
	 * Hozzáad a rendeltek listájához új Ételt.
	 * @param vasarolhato az {@code Etel} amit a rendeltek listájához ad
	 * @param darabSzam hány darab {@code Etel}-t adjon a rendeltek listájához
	 */
	public void addRendeles( Etel vasarolhato, int darabSzam ){
		if(rendeltek.containsKey(vasarolhato)){
			
			Integer i= rendeltek.get(vasarolhato);
			i+=darabSzam;
			rendeltek.put(vasarolhato, i);
		}
		else{
			rendeltek.put( vasarolhato, darabSzam );
		}
		
		//LOG
		Log.logger.trace("Étel rendelések {} asztalfoglalásnál: {} - {} Ft/db", 
				felelos.getNev(), vasarolhato.getNev(), vasarolhato.getAr());
	}
	
	/**
	 * Eltávolít a rendeltek listájából az adott nevű {@code Etel}-t.
	 * 
	 * @param nev az étel neve amelyiket el kell távolítani a rendeltek listájából
	 */
	public void torolRendeles(String nev){
		for(Vasarolhato v: rendeltek.keySet()){
			if(v.getNev().equals(nev)){
				rendeltek.remove(v);
				
				//LOG
				Log.logger.trace("Étel törlése {} asztalfoglalásából: {} - {}", 
						felelos.getNev(), nev);
				
				return;
			}
		}
	}
	
	/**
	 * Törli a teljes rendelés listát.
	 */
	public void torolTeljesRendeles(){
		rendeltek.clear();		
	}
	


	/**
	 * Visszaadja azt az {@code Asztalt} ahol van a foglalás.
	 * 
	 * @return az {@code Asztal} ahol a foglalás van
	 */
	public Asztal getFoglaltAsztal() {
		return foglaltAsztal;
	}

	/**
	 * Visszaadja számlázási felelős {@code Szemely}.
	 * 
	 * @return a számlázási felelős
	 */
	public SzamlazasiFelelos getFelelos() {
		return felelos;
	}

	/**
	 * Visszaadja a teljes rendelés HashMap-et.
	 * 
	 * @return a teljes rendelés HashMap
	 */
	public Map<Etel, Integer> getRendeltek() {
		return rendeltek;
	}

	
	
	/**
	 * Visszaadja mikor kezdődött az {@code AsztalFoglalas}.
	 * 
	 * @return az időpont amikor kezdődött az {@code AsztalFoglalas}
	 */
	public Date getIdopont() {
		return idopont;
	}

	/**
	 * Adott {@code Asztal} elfoglal megadot mennyitségű széket.
	 * 
	 * @param hanyat hány széket tegyen a metódus foglalttá
	 * @throws NincsElegHelyException ha nincs elég szék az asztalnál
	 */
	public void foglalSzeket(int hanyat) throws NincsElegHelyException{
		if (foglaltSzekekSzama+hanyat>foglaltAsztal.getSzekekSzama())
			throw new NincsElegHelyException(Math.abs(foglaltAsztal.getSzekekSzama()-foglaltSzekekSzama-hanyat));
		else
			this.foglaltSzekekSzama += hanyat;
	}
	
	/**
	 * Visszaadja az {@code AsztalFoglalas} String reprezentációját.
	 * 
	 * @return az {@code AsztalFoglalas} String reprezentációja
	 */
	@Override
	public String toString() {
		return "AsztalFoglalas [foglaltAsztal="
				+ foglaltAsztal + ", foglaltSzekekSzama=" + foglaltSzekekSzama
				+ ", felelos=" + felelos + ", rendeltek=" + rendeltek + "]";
	}
	
	
	
	
}
