package hu.unideb.etterem.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.border.LineBorder;

/**
 * Az osztály reprezentálja a megjeleníteni kívánt swing felületek alapját és alapkomponenseit.
 * 
 * @author Szabo Tamas
 *
 */
public class MyFrame extends JFrame implements IStatusbar{
	
	/**
	 * A mmenü sáv színe. 
	 */
	protected static final Color MENUSZIN = new Color(255,228,196);
	
	/**
	 * Az alap sáv színe. 
	 */	
	protected static final Color ALAPSZIN = new Color(255,255,102);
	
	/**
	 * A toolbar sáv színe. 
	 */
	protected static final Color TOOLBARSZIN = new Color(255,248,220);
	
	
	/**
	 *Az ablakhoz tartozó toolbar komponens. 
	 */
	protected JToolBar toolbar;
	
	/**
	 *Az ablakhoz tartozó vertical komponens. 
	 */	
	protected JToolBar vertical;
	
	/**
	 *Az ablakhoz tartozó menubar komponens. 
	 */	
	protected JMenuBar menubar;
	
	/**
	 *Az ablakhoz tartozó center jpanel komponens. 
	 */
	protected JPanel center;
	
	/**
	 *Az ablakhoz tartozó statusbar komponens. 
	 */	
	protected JLabel statusbar;
	
	/**
	 * A statusbar alapértelmezésként megjelenített szövege.
	 */
	protected String statusbarAlapertelmezettSzoveg = "Válasszon egy menüpontot!";
	
	/**
	 * Konstruktor {@code MyFrame} objektum példányosításához.
	 */
	public MyFrame(){
		toolbar = new JToolBar();
		vertical = new JToolBar(JToolBar.VERTICAL);
		menubar = new JMenuBar();
		center = new JPanel();
		statusbar = new JLabel(statusbarAlapertelmezettSzoveg);
		szinez();
		
        vertical.setFloatable(false);
        vertical.setMargin(new Insets(10, 5, 5, 5));
        toolbar.setFloatable(false);
        statusbar.setPreferredSize(new Dimension(-1, 22));
        statusbar.setBorder(LineBorder.createGrayLineBorder());
	}
	
	/**
	 *Beállítja az alpkomponenseket alapértelmezett színeit. 
	 */
	private void szinez() {
		setBackground(ALAPSZIN);
		menubar.setBackground(MENUSZIN);
		vertical.setBackground(MENUSZIN);
		toolbar.setBackground(TOOLBARSZIN);
		statusbar.setOpaque(false);
	}
	
	/**
	 * Beállítja az alpkomponensek hol helyezkedjenek el a Frame -n belül.
	 */
	protected void setElrendezes(){
        setJMenuBar(menubar);
        add(toolbar, BorderLayout.NORTH);
        add(vertical, BorderLayout.WEST);
        add( center , BorderLayout.CENTER );
        add(statusbar, BorderLayout.SOUTH);
	}
	
	/**
	 * Alapértelmezett inicializáló beállítások.
	 * 
	 * @param x az ablak szélessége
	 * @param y az ablak magasságe
	 */
	public void defaultInit(int x, int y){

		setElrendezes();
		setBezaras();
		
        setSize(x, y);
        
        setLocationRelativeTo(null);
	}
	
	/**
	 * Mit tegyen az ablak bezáráskor.
	 */
	protected void setBezaras(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**Visszaad egy JLabel statuszbart amelyet ezen a felületen keresztül módosítani lehet.
	 * 
	 * @return egy JLabel statuszbar
	 */
	@Override
	public JLabel getStatusbar() {
		return this.statusbar;
	}
	
	/***/
		
}
