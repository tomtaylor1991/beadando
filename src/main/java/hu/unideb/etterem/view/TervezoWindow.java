package hu.unideb.etterem.view;

import hu.unideb.etterem.controller.EtteremAdatkezelo;
import hu.unideb.etterem.model.Asztal;
import hu.unideb.etterem.model.AsztalFoglalasVezerlo;
import hu.unideb.etterem.model.exceptions.NincsIlyenAsztalException;
import hu.unideb.etterem.model.exceptions.NincsIlyenHelysegException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


/**
 * Az osztály reprezentálja a szobákban lévő asztalok létrehozásának ablakát.
 * 
 * @author Szabo Tamas
 *
 */
public class TervezoWindow extends EtteremView {

	/**
	 * Az asztalok elhelyezkedésénel megjelenítéséért felelős panel.
	 */
	private AsztalElhelyezes asztalElhelyezes;
	
	/**
	 * Konstruktor {@code TervezoWindow} objektum példányosításához.
	 * 
	 * @param asztalFoglalasVezerlo az asztal foglalásokat reprezentáló objektum
	 */
	public TervezoWindow(AsztalFoglalasVezerlo asztalFoglalasVezerlo) {
		super(asztalFoglalasVezerlo);
	}

	/**
	 * Alapértelmezett inicializáló beállítások.	 * 
	 */
	@Override
	public void init() {
		asztalElhelyezes = new AsztalElhelyezes(asztalFoglalasVezerlo);
        /***/
        renderSzobaValaszto();
        /***/
        
        setTitle("Étterem tervező 2014");
        defaultInit(900, 700);  
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
	}


	/**
	 * Frissíti a center komponensben lévő szobaválasztó panelt és a hozzá tartozó gombokat. 
	 */
	@Override
	protected void renderSzobaValaszto() {

		center = asztalElhelyezes;

		//Uj asztal gomb
        ImageIcon asztaltLetrehozIkon = KepEroforrasKezelo.getIcon("asztaltLetrehozIkon");
        JButton asztaltLetrehoz = new JButton(asztaltLetrehozIkon);
        asztaltLetrehoz.setOpaque(false);
        asztaltLetrehoz.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ujAsztaltLetrehoz();
			}
        });
        asztaltLetrehoz.addMouseListener(new StatusbarMouseAdapter(this, "Új asztal létrehozása a kijelölt pozíción."));
        asztaltLetrehoz.setToolTipText("Új asztal létrehozása a kijelölt pozíción.");
        
	
        ImageIcon asztaltTorolIkon = KepEroforrasKezelo.getIcon("asztaltTorolIkon");
        JButton asztaltTorol = new JButton(asztaltTorolIkon);
        asztaltTorol.setOpaque(false);
        asztaltTorol.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				asztaltTorolParancs();
			}
        });
        asztaltTorol.addMouseListener(new StatusbarMouseAdapter(this, "Asztal törlése a kijelölt pozíción."));
        asztaltTorol.setToolTipText( "Asztal törlése a kijelölt pozíción.");
        
        
        ImageIcon mentesKilepesIkon = KepEroforrasKezelo.getIcon("mentesKilepesIkon");
        JButton mentes = new JButton(mentesKilepesIkon);
        mentes.setOpaque(false);
        mentes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                mentesParancs();
            }

		});
        mentes.addMouseListener(new StatusbarMouseAdapter(this, "Mentés."));
        mentes.setToolTipText("Mentés.");
        
        
        ImageIcon szobatTorolIkon = KepEroforrasKezelo.getIcon("szobatTorolIkon");
        JButton szobatTorol = new JButton(szobatTorolIkon);
        szobatTorol.setOpaque(false);
        szobatTorol.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                szobatTorolParancs();
            }

		});
        
        
        ImageIcon kilepesIkon = KepEroforrasKezelo.getIcon("kilepes");
        JButton kilepes = new JButton(kilepesIkon);
        kilepes.setOpaque(false);
        kilepes.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent e)
		            {
		            	AsztalFoglalasParancsKezelo.frissitSzobat();
		            	
		                dispose();
		            }
		});
        kilepes.addMouseListener(new StatusbarMouseAdapter(this, "Kilépés."));
        kilepes.setToolTipText("Kilépés.");
        
        
        vertical.add(asztaltLetrehoz);
        vertical.add(asztaltTorol);
        vertical.add(mentes);
        //vertical.add(szobatTorol);
        vertical.add(kilepes);
        
	}

	/***/
	
	/**
	 * Elmenti az étterem adataid fájlba.
	 */
	private void mentesParancs() {
		EtteremAdatkezelo.ElmentEtterem();
	}
	
	/**
	 * Létrehoz egy új asztalt.
	 */
	private void ujAsztaltLetrehoz() {
		
		if(!AsztalFoglalasParancsKezelo.vanKijelolt) return;

		try {
			Asztal asztalt =asztalFoglalasVezerlo.getElrendezesVezerlo().getAsztal(AsztalFoglalasParancsKezelo.kijeloltSzoba, AsztalFoglalasParancsKezelo.kijeloltPozicio);
		} catch (NincsIlyenHelysegException | NincsIlyenAsztalException e) {
			String str = JOptionPane.showInputDialog(null, "Székek száma : ", 
					"Adja meg, hány szék legyen az asztalnál!", 1);
			
			try{
				int szekSzam = Integer.parseInt(str);
				asztalFoglalasVezerlo.getElrendezesVezerlo().addUjAsztalSzobahoz(
						AsztalFoglalasParancsKezelo.kijeloltSzoba, AsztalFoglalasParancsKezelo.kijeloltPozicio, szekSzam);
				asztalElhelyezes.szobatFrissitParancs();
			}catch(NumberFormatException e1){}
		}
	}	
	
	/***/
	
	/**
	 * Törli egy dialógus ablakból beolvasott nevű szobát.
	 */
	private void szobatTorolParancs() {
		String str = JOptionPane.showInputDialog(null, "Törölni kívánt szoba neve : ", 
				"Adja meg a törölni kívánt szoba nevét", 1);

		List<Asztal> asztalokSzobaban = asztalFoglalasVezerlo.getFoglaltAsztalok(str);
		if(asztalokSzobaban.isEmpty()){
			asztalFoglalasVezerlo.getElrendezesVezerlo().torolSzoba(str);
			asztalElhelyezes.szobatFrissitParancs();
		}
	}

	/**
	 * Töröli a kijelölt asztalt, feltéve, hogy az lehetséges.
	 */
	private void asztaltTorolParancs() {

		if( ! AsztalFoglalasParancsKezelo.vanKijelolt) return;

		try {
			Asztal asztalt =asztalFoglalasVezerlo.getElrendezesVezerlo().getAsztal(AsztalFoglalasParancsKezelo.kijeloltSzoba, AsztalFoglalasParancsKezelo.kijeloltPozicio);
			
			int id = asztalFoglalasVezerlo.getIdByPozicio(
					AsztalFoglalasParancsKezelo.kijeloltSzoba, 
					AsztalFoglalasParancsKezelo.kijeloltPozicio);	
			//System.out.println(id);
			if(id == 0){
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog(this, "Biztos törli a asztalt?", "",dialogButton);
				if(dialogResult==0){
					
					asztalFoglalasVezerlo.getElrendezesVezerlo().torolAsztal(
							AsztalFoglalasParancsKezelo.kijeloltSzoba, 
							AsztalFoglalasParancsKezelo.kijeloltPozicio							
						);
					
					asztalElhelyezes.szobatFrissitParancs();
				}
				  
			}
			
		} 
		catch (NincsIlyenHelysegException e) {
			System.out.println("");
		} catch (NincsIlyenAsztalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}
}
