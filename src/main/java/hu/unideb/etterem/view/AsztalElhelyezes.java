package hu.unideb.etterem.view;

import hu.unideb.etterem.model.AsztalFoglalasVezerlo;
import hu.unideb.etterem.model.Pozicio;
import hu.unideb.etterem.model.Szoba;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Az osztály reprezentálja a szobákban lévő asztalok panelét, tervező ablakhoz készített modul.
 * 
 * @author Szabo Tamas
 *
 */
public class AsztalElhelyezes extends SzobaValaszto{

	/**
	 * Konstruktor {@code TervezoWindow} objektum példányosításához.
	 * 
	 * @param asztalFoglalasVezerlo az asztal foglalásokat reprezentáló objektum
	 */
	public AsztalElhelyezes(AsztalFoglalasVezerlo asztalFoglalasVezerlo) {
		super(asztalFoglalasVezerlo);
	}

	/**
	 * Vezérli a felépítését a szobakezelő panelnek.
	 * 
	 * @param panel hova kell beilleszteni a szoba nézetet
	 * @param szoba melyik szobát kell felépíteni
	 */
	@Override
	protected void buildPanel(JPanel panel, Szoba szoba) {
		Set<Pozicio> poziciok;
		List<Pozicio> foglaltak = asztalFoglalasVezerlo.getFoglaltAsztalokPozicioi(AsztalFoglalasParancsKezelo.kijeloltSzoba);

		poziciok = szoba.getPoziciok();

        for(int i=1;i<=szoba.getMeretX();i++){
        	for(int j=1;j<=szoba.getMeretY();j++){

        		if(poziciok.contains(new Pozicio(i, j)))
        		{

        			String felirat = String.format("%d,%d, (%d)", i, j, 
        					szoba.getAsztalok().get(new Pozicio(i, j)).getSzekekSzama() );
        			
        			final JButton gomb = new JButton(felirat);
     
    	        	gomb.setHorizontalAlignment(SwingConstants.LEFT);
    	        	//gomb.setVerticalAlignment(SwingConstants.TOP);
    	        	gomb.setMargin(new Insets(0,0,0,0));
    	        	
    	        	if(foglaltak.contains(getPozicioFromText(felirat)))
    	        		gomb.setBackground(FOGLALTASZTALSZIN);
    	        	else
    	        		gomb.setBackground(ASZTALSZIN);
    	        	
    	        	gomb.setPreferredSize(new Dimension( 55, 55 ));
    	        	
    	        	gomb.addActionListener(new ActionListener() {
    	    			@Override
    	    			public void actionPerformed(ActionEvent arg0) {
    	    			
    	    				kijeloltteTesz(gomb.getText(), gomb);
    	    				
    	    			}
    	            });
    	       
    	        	panel.add(gomb);
        		}
        		else
        		{
        			//ures hely
        			String felirat = String.format("%d,%d", i, j);
        			
        			final JButton gomb = new JButton(felirat);
        		
    	        	gomb.setHorizontalAlignment(SwingConstants.LEFT);
    	        	//gomb.setVerticalAlignment(SwingConstants.TOP);
    	        	gomb.setMargin(new Insets(0,0,0,0));

    	        	gomb.setBackground(Color.WHITE);

    	        	gomb.setPreferredSize(new Dimension( 55, 55 ));
    	        	
    	        	gomb.addActionListener(new ActionListener() {
    	    			@Override
    	    			public void actionPerformed(ActionEvent arg0) {
    	    			
    	    				kijeloltteTesz(gomb.getText(), gomb);
    	    				
    	    			}
    	            });
        			
        			panel.add(gomb);
        		}
	        	
        	}
        }
	}


	/**
	 * Beállít egy üres szűrőgombokat tartalmazó nézetet.
	 * */
	@Override
	protected void szuroGombokRender() {
		
	}
	
	

}
