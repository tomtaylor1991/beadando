package hu.unideb.etterem.model;

/**
 * Az osztály reprezentálja egy tárgy pozícióját.
 * 
 * @author Szabo Tamas
 *
 */
public class Pozicio {
	
	/**
	 * A Pozíció X koordinátája.
	 */
	private int x;
	
	/**
	 * A Pozíció Y koordinátája.
	 */
	private int y;
	
	/**
	 * Konstruktor {@code Pozicio} objektum példányosításához.
	 * 
	 * @param x a Pozíció X koordinátája
	 * @param y a Pozíció Y koordinátája
	 */
	public Pozicio(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Visszaadja a Pozíció X koordinátáját.
	 * @return a Pozíció X koordinátája
	 */
	public int getX() {
		return x;
	}

	/**
	 * Visszaadja a Pozíció Y koordinátáját.
	 * @return a Pozíció Y koordinátája
	 */
	public int getY() {
		return y;
	}

	/**
	 * Visszaadja az objektum hash kódját.
	 *
	 * @return az objektum hash kivonata
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}
	
	/**
	 * Összehasonlít két Pozíciót, hogy egyenlőek -e. Két {@code Pozíció} objektumot egyenlőnek tekintünk,
	 * ha megegyezik az x és y koordinátájuk.
	 *
	 * @param obj az objektum amellyel össze szeretnénk hasonlítani
	 * @return <code>true</code> ha az objektumok egyenlőek, <code>false</code> különben
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pozicio other = (Pozicio) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
	/**
	 * Visszaadja az Pozíció String reprezentációját.
	 * 
	 * @return az Pozíció String reprezentációja
	 * */
	@Override
	public String toString() {
		return "[x=" + x + ", y=" + y + "]";
	}
}
