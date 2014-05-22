package hu.unideb.etterem.model;

import hu.unideb.etterem.controller.Log;
import hu.unideb.etterem.model.interfaces.LekerdezhetoRendeles;
import hu.unideb.etterem.model.interfaces.VegosszegKalkulatorSzabaly;

/**
 * Az osztály felelős egy rendelés végösszeg szabályok által megfelelt kiszámolásáért.
 * 
 * @author Szabo Tamas
 *
 */
public class VegosszegKalkulator {
	
	/**
	 * A szabályzat amelyekkel ki kell egészíteni a rendelés végösszegét.
	 */
	private VegosszegKalkulatorSzabaly szabaly;
	
	/**
	 * Konstruktor {@code VegosszegKalkulator} objektum példányosításához.
	 * 
	 * @param szabaly szabályzat amelyekkel ki kell egészíteni a rendelés végösszegét
	 */
	public VegosszegKalkulator(VegosszegKalkulatorSzabaly szabaly) {
		this.szabaly = szabaly;
	}

	/**
	 * Visszaadja azt az összeget amelyet számlázáskor kell fizetni a rendelés után.
	 * 
	 * @param rendeles a rendelés amelynek a végösszegént kívánjuk kiszámítani
	 * @return megfelelő, szabályzat mentén kiegészített végösszeg
	 */
	public int getKalkulacio(LekerdezhetoRendeles rendeles){
		
		int eredmeny = (int) (rendeles.getVegOsszeg() * szabaly.getSzorzo() + szabaly.getBonuszOsszeg());
		
		/*
		//LOG
		Log.logger.info("Végösszeg korrekció: szorzó -> {} és búnusz összeg -> {} Ft. Eredeti osszeg {} Ft -> Kalkulált összeg {} Ft",
				szabaly.getSzorzo() , szabaly.getBonuszOsszeg(), rendeles.getVegOsszeg(), eredmeny);
		*/
		
		return eredmeny;
	}
}
