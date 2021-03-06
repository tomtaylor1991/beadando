package hu.unideb.etterem.model;

import hu.unideb.etterem.controller.Log;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Az osztály reprezentál egy Éttermi menüt.
 * 
 * @author Szabo Tamas
 *
 */
public class Menu {
	
	/**
	 * Hash tábla amely tárol kategóriákat és hozzájuk tartozó {@code Etel}-ek listáját.
	 */
	private Map< String, List<Etel> > menu;

	/**
	 * Konstruktor {@code Menu} objektum példányosításához.
	 */
	public Menu() {
		this.menu=new HashMap< String, List<Etel> >();
	}
	
	/**
	 * Konstruktor {@code Menu} objektum példányosításához.
	 * 
	 * @param menu Hash tábla amely tárol kategóriákat és hozzá tartozó ételek listáját
	 */
	public Menu(Map<String, List<Etel>> menu) {
		this.menu = menu;
	}

	/**
	 * Hozzáad a {@code Menu} adott kategóriájához adott {@code Etel}-t, 
	 * ha még nincs olyan kategória, akkor azt is létrehoz előtte.
	 * 
	 * @param kategoria melyik kategóriához kell {@code Etel}-t hozzáadni
	 * @param etel melyik {@code Etel}-t adja hozzá a {@code Menu}-höz
	 */
	public void addEtel(String kategoria, Etel etel){
		if(menu.containsKey(kategoria)){
			//System.out.println("Letezik ilyen kategoria!");
			List<Etel> l= menu.get(kategoria);
			l.add(etel);
		}
		else{
			//System.out.println("Nem letezik ilyen kategoria!");
			menu.put(kategoria, new LinkedList<Etel>());
			List<Etel> l= menu.get(kategoria);
			l.add(etel);
		}
	}

	/**
	 * Visszaaja a teljes {@code Menu}-t.
	 * 
	 * @return  Hash tábla amely tárol kategóriákat és hozzájuk tartozó {@code Etel}-ek listáját
	 */
	public Map<String, List<Etel>> getMenu() {
		return menu;
	}
	
	/**
	 * Visszaadja egy kategóriához tartozó összes {@code Etelt}-t.
	 * 
	 * @param kategoria melyik kategória {@code Etelt}-eit adja vissza
	 * @return <code>List<Etel></code> ha adott kategória összes {@code Etel} ha létezik ilyen nevű kateória, 
	 * 		<code>null</code> különben
	 */
	public List<Etel> getEtelek(String kategoria){
		if(getKategoriaNevek().contains(kategoria))
			return menu.get(kategoria);
		else{
		
			//LOG
			Log.logger.warn("Egyik kategória sem rendelkezik az alábbi névvel: {} ", kategoria);
			
			return null;
		}
	}
	
	/**
	 * Visszaadja az összes kategória nevét.
	 * 
	 * @return az összes kategória nevének halmaza
	 */
	public Set<String> getKategoriaNevek(){
		return menu.keySet();
	}
	
	/**
	 * Visszaadja az összes kategória nevét.
	 * 
	 * @return az összes kategória nevének listája
	 */
	public List<String> getKategoriaNevekLista(){
		List<String> ret = new LinkedList<String>();
		for(String s:menu.keySet()){
			ret.add(s);
		}
		return ret;
	}
	
	/**
	 * Visszaadja a {@code Menu} String reprezentációját.
	 * 
	 * @return a {@code Menu} String reprezentációja
	 * */
	@Override
	public String toString() {
		String out="";
		int sorrend=1;
		for (String kategoria:this.menu.keySet()){
			
			out+=sorrend+". "+kategoria+": \n";
			int n=1;
			
			for(Etel etel:this.menu.get(kategoria))	{
				out+=sorrend+"."+n+": "+etel+"\n";
				n++;
			}
				
			sorrend++;
		}
		return out;
	}
	
	
}
