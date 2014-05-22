package hu.unideb.etterem.model.exceptions;

import hu.unideb.etterem.model.Pozicio;

/**
 * A kivétel reprezentálja azt a helyzetet, amikor valami nem fér be egy helységbe.
 * 
 * @author Szabo Tamas
 *
 */
public class NemFerBeAHelysegbeException extends Exception {
	
	/**
	 * Melyik pozíció nem fért be.
	 */
	private Pozicio pozicio;

	/**
	 * Konstruktor {@code NemFerBeAHelysegbeException} kivétel példányosításához.
	 * 
	 * @param pozicio melyik pozíció nem fért be
	 */
	public NemFerBeAHelysegbeException(Pozicio pozicio) {
		super();
		this.pozicio = pozicio;
	}

	/**
	 * Visszaadja a kivétel hibaüzenetét.
	 * 
	 * @return a kivétel hibaüzenete
	 * */
	@Override
	public String getMessage() {
		return "Ez a koordinata nem fer be a szobaba: "+pozicio+"!";
	}
	
}
