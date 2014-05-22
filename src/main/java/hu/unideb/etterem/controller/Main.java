package hu.unideb.etterem.controller;

import hu.unideb.etterem.model.AsztalFoglalasVezerlo;
import hu.unideb.etterem.model.ElrendezesVezerlo;
import hu.unideb.etterem.model.Etterem;
import hu.unideb.etterem.model.Menu;
import hu.unideb.etterem.view.EtteremView;
import javax.swing.SwingUtilities;


/**
 * Az osztály létrehoz egy kezelőfelületet az étterem menedzseléséhez.
 * 
 * @author Szabo Tamas
 *
 */
public class Main {
	
	/**
	 * Létrehoz egy kezelőfelületet az étterem menedzseléséhez.
	 * 
	 * @param args Parancssori argumentumok
	 */
	public static void main(String[] args) {
		
		//1.
		
		//LOG
		Log.logger.trace("Étterm inicializálásának kezdete!");
		
		final Etterem etterem=EtteremAdatkezelo.getEtterem();
		final Menu menu = MenuAdatkezelo.getMenu();
		
		//LOG
		Log.logger.trace("Étterm inicializálásának vége!");
		
		//2. 
		ElrendezesVezerlo elrendezesVezerlo=new ElrendezesVezerlo( etterem );

		//3. 
		final AsztalFoglalasVezerlo foglalasVezerlo= new AsztalFoglalasVezerlo( elrendezesVezerlo, menu );
		//4. 
		
		Log.logger.trace("Grafikus felület inicializálása!");
		
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	EtteremView view = new EtteremView( foglalasVezerlo );
            	view.init();
            	view.setVisible(true);
            }
        });

		Log.logger.info("Elindult a program grafikus felülettel.");
		
	}
}
