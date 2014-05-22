package hu.unideb.etterem.model.exceptions;

/**
 * A kivétel reprezentálja azt a helyzetet, amikor nincs elég hely egy befoglaló tárgynál.
 * 
 * @author Szabo Tamas
 *
 */
public class NincsElegHelyException extends Exception {
	
	/**
	 * Amennyi helyet igényeltek.
	 */
	int hanyHely=0;

	/**
	 * Konstruktor {@code NincsElegHelyException} kivétel példányosításához.
	 * 
	 * @param hanyHely amennyi helyet igényeltek
	 */
	public NincsElegHelyException(int hanyHely) {
		super();
		this.hanyHely = hanyHely;
	}

	/**
	 * Visszaadja a kivétel hibaüzenetét.
	 * 
	 * @return a kivétel hibaüzenete
	 * */
	@Override
	public String getMessage() {
		return "Nincs eleg hely az asztalnal! Hianyzo helyek szama: "+hanyHely+" db.";
	}
	
}
