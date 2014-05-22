package hu.unideb.etterem.model;

/**
 * Az osztály reprezentál egy Asztalt, amelyeknek vannak székei.
 * 
 * @author Szabo Tamas
 * 
 */
public class Asztal {

	/**
	 * Az asztal egyedi szám azonosítója.
	 */
	private int asztalID;

	/**
	 * Megmutatja hány szék tartozik az asztalhoz.
	 */
	private int szekekSzama;

	/**
	 * Konstruktor {@code Asztal} objektum példányosításához.
	 * 
	 * @param asztalID
	 *            az {@code Asztal} egyedi szám ( {@code int} ) azonosítója
	 * @param szekekSzama
	 *            hány szék tartozik az asztalhoz
	 */
	public Asztal(int asztalID, int szekekSzama) {
		this.asztalID = asztalID;
		this.szekekSzama = szekekSzama;
	}

	/**
	 * Konstruktor {@code Asztal} objektum példányosításához.
	 */
	public Asztal() {
	}

	/**
	 * Visszaadja az Asztal egyedi azonosítóját.
	 * 
	 * @return az asztal egyedi szám ( {@code int} ) azonosítója
	 */
	public int getID() {
		return asztalID;
	}

	/**
	 * Visszaadja az Asztalhoz tartozó székek számát.
	 * 
	 * @return asztalhoz tartozó székek száma
	 * 
	 */
	public int getSzekekSzama() {
		return szekekSzama;
	}

	/**
	 * Beállítja az Asztal egyedi azonosítóját.
	 * 
	 * @param asztalID
	 *            az asztal új egyedi szám ( {@code int} )  azonosítója
	 */
	public void setAsztalID(int asztalID) {
		this.asztalID = asztalID;
	}

	/**
	 * Beállítja az Asztalhoz tartozó székek számát.
	 * 
	 * @param szekekSzama
	 *            asztalhoz tartozó székek száma
	 */
	public void setSzekekSzama(int szekekSzama) {
		this.szekekSzama = szekekSzama;
	}

	/**
	 * Visszaadja az {@code Asztal} String reprezentációját.
	 * 
	 * @return az {@code Asztal} String reprezentációja
	 * */
	public String toString() {
		return "Asztal [asztalID=" + asztalID + ", szekekSzama=" + szekekSzama
				+ ", MaxSzekekSzamka:" + szekekSzama;

	}
}
