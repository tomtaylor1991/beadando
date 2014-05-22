package hu.unideb.etterem.model.data;

import java.util.Date;

import hu.unideb.etterem.model.interfaces.SzamlazasiFelelos;

/**
 * Az interfész megad egy felületet egy vásárlás végeztével a vásárlás fontosabb adatainak elemtésére.
 * 
 * @author Szabo Tamas
 *
 */
public interface VasarlasDAO {
	
	/**
	 * Elment egy vásárlást.
	 * @param szamlazasiFelelos ki volt a felelős személy
	 * @param datum mikor történt a vásárlás
	 * @param vegosszeg mekkora értékben történt vásárlás
	 */
	public void saveVasarlas(SzamlazasiFelelos szamlazasiFelelos, Date datum, int vegosszeg);
}
