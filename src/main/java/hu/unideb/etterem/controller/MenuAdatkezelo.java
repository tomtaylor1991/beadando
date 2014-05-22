package hu.unideb.etterem.controller;

import hu.unideb.etterem.model.Menu;
import hu.unideb.etterem.model.data.MenuDAO;
import hu.unideb.etterem.model.data.impl.MenuDom;

/**
 * Az osztály feleős egy Étteremi Menü inicializálásáért.
 * 
 * @author Szabo Tamas
 *
 */
public class MenuAdatkezelo {
	
	/**
	 * A Menü adatait tároló xml fájl elérési útja.
	 */
	private static final String MENU_SRC = "data/xml/menu.xml";

	/**
	 * Menü kezelés felületét meghatározó interfész.
	 */
	private static MenuDAO menuDAO;
	
	/**
	 * Az osztály statikus Menü objektum kezelésének referenciája.
	 */
	private static Menu menu;
	
	static {
		menuDAO = new MenuDom(MENU_SRC);
		menu = new Menu();
	}
	
	/**
	 * Privát {@code MenuAdatkezelo} konstruktor.
	 */
	private MenuAdatkezelo(){}
	
	/**
	 * Beolvassa és visszaadja az osztály statikus Menü referenciáját.
	 * 
	 * @return  az osztály statikus Menü referenciája
	 */
	public static Menu getMenu(){
		
		beolvas();
		
		return menu;
	}
	
	/**
	 * Beolvas fájlból Menü adatait.
	 */
	private static void beolvas(){
		
		//LOG
		Log.logger.trace("Éttermi menü beolvasásának indítása!");
		
		menu = menuDAO.getMenu();
	}
}
