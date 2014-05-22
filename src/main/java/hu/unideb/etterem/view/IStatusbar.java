package hu.unideb.etterem.view;

import javax.swing.JLabel;

/**
 * Az interfész ad egy hozzáférési felületet egy JLabel statusbarhoz.
 * 
 * @author Szabo Tamas
 *
 */
public interface IStatusbar {
	
	/**Visszaad egy JLabel statuszbart amelyet ezen a felületen keresztül módosítani lehet.
	 * 
	 * @return egy JLabel statuszbar
	 */
	JLabel getStatusbar();
}
