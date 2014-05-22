package hu.unideb.etterem.model.exceptions;

/**
 * A kivétel reprezentálja azt a helyzetet, amikor nem létező helységre hivatkoznak.
 * 
 * @author Szabo Tamas
 *
 */
public class NincsIlyenHelysegException extends Exception {
	
	/**
	 *Milyen nevű szobára történt hivatkozás. 
	 */
	String szobaNev="";

	/**
	 * Konstruktor {@code NincsIlyenHelysegException} kivétel példányosításához.
	 * 
	 * @param szobaNev milyen nevű szobára történt hivatkozás
	 */
	public NincsIlyenHelysegException(String szobaNev) {
		super();
		this.szobaNev = szobaNev;
	}

	/**
	 * Visszaadja a kivétel hibaüzenetét.
	 * 
	 * @return a kivétel hibaüzenete
	 * */
	@Override
	public String getMessage() {
		return "Nincs ilyen nevu szoba az etteremben: "+szobaNev+"!";
	}
	
}
