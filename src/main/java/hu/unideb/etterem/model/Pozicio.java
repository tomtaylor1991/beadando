package hu.unideb.etterem.model;

/**
 * Az osztály reprezentálja egy tárgy pozícióját.
 * 
 * @author Szabo Tamas
 *
 */
public class Pozicio {
	
	/**
	 * A {@code Pozicio} X koordinátája.
	 */
	private int x;
	
	/**
	 * A {@code Pozicio} Y koordinátája.
	 */
	private int y;
	
	/**
	 * Konstruktor {@code Pozicio} objektum példányosításához.
	 * 
	 * @param x a {@code Pozicio} X koordinátája
	 * @param y a {@code Pozicio} Y koordinátája
	 */
	public Pozicio(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Visszaadja a {@code Pozicio} X koordinátáját.
	 * @return a {@code Pozicio} X koordinátája
	 */
	public int getX() {
		return x;
	}

	/**
	 * Visszaadja a {@code Pozicio} Y koordinátáját.
	 * @return a {@code Pozicio} Y koordinátája
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
	 * Visszaadja az {@code Pozicio} String reprezentációját.
	 * 
	 * @return az {@code Pozicio} String reprezentációja
	 * */
	@Override
	public String toString() {
		return "[x=" + x + ", y=" + y + "]";
	}
}
