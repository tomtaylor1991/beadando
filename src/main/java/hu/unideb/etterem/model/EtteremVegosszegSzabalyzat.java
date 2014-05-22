package hu.unideb.etterem.model;

import hu.unideb.etterem.model.interfaces.VegosszegKalkulatorSzabaly;

/**
 * Az osztály felelős egy Éttermi rendelés végösszeg kiszámolási szabályainak meghatározásáért.
 * 
 * @author Szabo Tamas
 *
 */
public class EtteremVegosszegSzabalyzat implements VegosszegKalkulatorSzabaly {

	/**
	 * Egy szorzószám, amellyel az egész összeget meg kell szorozni.
	 */
	private static final double SZORZO = 1;
	
	/**
	 * Egy egész szám, amelyet az összeghez teljes egészében hozzá kell adni.
	 */
	private static final int BONUSZ = 1000;
	
	/**
	 * Visszaad egy szorzószámot, amellyel az egész összeget meg kell szorozni.
	 * 
	 * @return egy szorzószám
	 */
	@Override
	public double getSzorzo() {
		return SZORZO;
	}

	/**
	 * Visszaad egy egész számot, amelyet az összeghez teljes egészében hozzá kell adni.
	 * 
	 * @return egy egész szám, amelyet az összeghez teljes egészében hozzá kell adni
	 */
	@Override
	public int getBonuszOsszeg() {
		return BONUSZ;
	}

}
