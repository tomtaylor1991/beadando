package hu.unideb.etterem.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Az osztály reprezentál egy egéresemély kezelőt amely egy JLabel statusbar szövegét 
 * állíthatja át bizonyos események lefutása esetén.
 * 
 * @author Szabo Tamas
 *
 */
public class StatusbarMouseAdapter extends MouseAdapter {

	/**
	 * A statusbar felületét ezen a referencián keresztül lehet elérni.
	 */
	private IStatusbar statusbar;
	
	/**
	 * A szöveg amelyre be kell állítani a statusbart, amikor az egér felé kerül a szülő objektumnak. 
	 */
	private String text;
	
	/**
	 * Az alapértelmezett szöveg amelyre be kell állítani a statusbart, amikor az egér elhagyja a szülő objektumot.
	 */
	private static String DEFAULTTEXT = "Válasszon egy menüpontot!";
	
	/**
	 * Konstruktor StatusbarMouseAdapter objektum példányosításához.
	 * 
	 * @param statusbar a statusbar felületét ezen a referencián keresztül lehet elérni
	 * @param text a szöveg amelyre be kell állítani a statusbart, amikor az egér felé kerül a szülő objektumnak
	 */
	public StatusbarMouseAdapter(IStatusbar statusbar, String text) {
		super();
		this.statusbar = statusbar;
		this.text = text;
	}

	/**
	 * Esemény kiváltódásakor beállítódik a statusbar szövege a megkapott szövegre.
	 * 
	 * @param e a kiváltó esemény
	 * */
	@Override
	public void mouseEntered(MouseEvent e) {
		statusbar.getStatusbar().setText(text);
	}

	/** 
	 *  Esemény kiváltódásakor beállítódik a statusbar szövege az alapértelmezett szövegre.
	 *  
	 * @param e a kiváltó esemény	 
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		statusbar.getStatusbar().setText(DEFAULTTEXT);
	}
	
	/**
	 * Beálíltja az alapértelmezett szöveget.
	 * 
	 * @param text az új alapértelmezett szöveg
	 */
	public static void setDefaultText(String text){
		DEFAULTTEXT = text;
	}
	
}
