package hu.unideb.etterem.view;

import hu.unideb.etterem.controller.Log;
import hu.unideb.etterem.model.AsztalFoglalasVezerlo;
import hu.unideb.etterem.model.Nevjegy;
import hu.unideb.etterem.model.data.impl.ConnectionHelper;
import hu.unideb.etterem.model.exceptions.NincsIlyenAsztalException;
import hu.unideb.etterem.model.exceptions.NincsIlyenHelysegException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 * Az osztály reprezentálja az alapértelmezett Étterem megjelenést.
 * 
 * @author Szabo Tamas
 *
 */
public class EtteremView extends MyFrame{

	/**
	 * Ezen referencián keresztül el tudja érni az model adatait.
	 */
	public AsztalFoglalasVezerlo asztalFoglalasVezerlo;
	
	/**
	 * Beépülő JFrame center panelen. 
	 */
	private SzobaValaszto szobaValaszto;


	/**
	 * Konstruktor {@code EtteremView} objektum példányosításához.
	 * 
	 * @param asztalFoglalasVezerlo az asztal foglalásokat reprezentáló objektum
	 */
	public EtteremView(AsztalFoglalasVezerlo asztalFoglalasVezerlo) {
		super();
		this.asztalFoglalasVezerlo = asztalFoglalasVezerlo;
		
	}


	/**
	 * Alapértelmezett inicializáló beállítások.	 * 
	 */
	public void init(){    
		
		szobaValaszto = new SzobaValaszto(asztalFoglalasVezerlo);
        
        /***/
        renderSzobaValaszto();
        /***/
        
        //beállítunk egy referenciát a szobaválasztóra
        AsztalFoglalasParancsKezelo.frissithetoSzobaNezet = szobaValaszto;
        
        defaultInit(800, 600);
        
        if(asztalFoglalasVezerlo.getElrendezesVezerlo().getEtterem().nevjegy != null){
	        Nevjegy etteremNevjegy = asztalFoglalasVezerlo.getElrendezesVezerlo().getEtterem().nevjegy;
	        setTitle("Étterem 2014 - " + etteremNevjegy.nev);
	        statusbarAlapertelmezettSzoveg = " <> " + etteremNevjegy.nev+ " <> " + etteremNevjegy.nyitvaTartas + " <> " + etteremNevjegy.elerhetoseg.cim ;
	        statusbar.setText(statusbarAlapertelmezettSzoveg);
	        
	        StatusbarMouseAdapter.setDefaultText(statusbarAlapertelmezettSzoveg);
        }
	}

	/**
	 * Frissíti a center komponensben lévő szobaválasztó panelt és a hozzá tartozó gombokat. 
	 */
	protected void renderSzobaValaszto() {
		
        ImageIcon asztaltFelszabaditIkon = KepEroforrasKezelo.getIcon("asztaltFelszabadit");
        JButton asztaltFelszabadit = new JButton(asztaltFelszabaditIkon);
        asztaltFelszabadit.setOpaque(false);
        asztaltFelszabadit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				megjelenitLogikaPanel();
			}	
        });
        asztaltFelszabadit.addMouseListener(new StatusbarMouseAdapter(this, "Asztal foglalás törlése."));
        asztaltFelszabadit.setToolTipText("Asztal foglalás törlése.");
        
        
        ImageIcon asztaltFoglalIkon = KepEroforrasKezelo.getIcon("asztaltFoglal");
        JButton asztaltFoglal = new JButton(asztaltFoglalIkon);
        asztaltFoglal.setOpaque(false);
        asztaltFoglal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				asztaltFoglalParancs();
  			}
        });
        asztaltFoglal.addMouseListener(new StatusbarMouseAdapter(this, "Asztal foglalás létrehozása a kijelölt asztalnál."));
        asztaltFoglal.setToolTipText("Asztal foglalás létrehozása a kijelölt asztalnál.");
        

        ImageIcon racsozasIkon = KepEroforrasKezelo.getIcon("racsozas");
        JButton racsozasGomb = new JButton(racsozasIkon);
        racsozasGomb.setOpaque(false);
        racsozasGomb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				szobaRacsozasaParancs();
			}
        });
        racsozasGomb.addMouseListener(new StatusbarMouseAdapter(this, "Szoba rácsozása be/ki."));
        racsozasGomb.setToolTipText("Szoba rácsozása be/ki.");
        
        
        ImageIcon asztaltMenuRendelesIkon = KepEroforrasKezelo.getIcon("asztaltMenuRendeles");
        JButton asztaltMenuRendeles = new JButton(asztaltMenuRendelesIkon);
        asztaltMenuRendeles.setOpaque(false);
        asztaltMenuRendeles.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				menuRendelesParancs();
			}
        });
        asztaltMenuRendeles.addMouseListener(new StatusbarMouseAdapter(this, "Menü rendelés hozzáadása/módosítása a kijelölt asztalnál."));
        asztaltMenuRendeles.setToolTipText("Menü rendelés hozzáadása/módosítása a kijelölt asztalnál.");
        
 
        ImageIcon fizetesIkon = KepEroforrasKezelo.getIcon("fizetes");
        JButton fizetesGomb = new JButton(fizetesIkon);
        fizetesGomb.setOpaque(false);
        fizetesGomb.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent arg0) {
				fizetesParancs();
			}
        });
        fizetesGomb.addMouseListener(new StatusbarMouseAdapter(this, "Fizetés panel megjeleítése."));
        fizetesGomb.setToolTipText("Fizetés panel megjeleítése.");        
     
        
        ImageIcon kilepesIkon = KepEroforrasKezelo.getIcon("kilepes");
        JButton kilepesGomb = new JButton(kilepesIkon);
        kilepesGomb.setOpaque(false);
        kilepesGomb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				kilepesParancs();
			}
        });
        kilepesGomb.addMouseListener(new StatusbarMouseAdapter(this, "Kilépés a programból."));
        kilepesGomb.setToolTipText("Kilépés a programból.");
        
      
        ImageIcon tervezoIkon = KepEroforrasKezelo.getIcon("tervezoIkon");
        JButton tervezoGomb = new JButton(tervezoIkon);
        tervezoGomb.setOpaque(false);
        tervezoGomb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				tervezesParancs();
			}

        });
        tervezoGomb.addMouseListener(new StatusbarMouseAdapter(this, "Szoba berendezés tervező panel megnyitása."));
        tervezoGomb.setToolTipText("Szoba berendezés tervező panel megnyitása.");
        
      
        ImageIcon bevetelkon = KepEroforrasKezelo.getIcon("bevetelkon");
        JButton bevetelGomb = new JButton(bevetelkon);
        bevetelGomb.setOpaque(false);
        bevetelGomb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog ( null, "Napi bevétel: " + asztalFoglalasVezerlo.getBevetel()+" Ft" );
			}

        });
        bevetelGomb.addMouseListener(new StatusbarMouseAdapter(this, "Aktuális összesített bevétel megtekintése."));
        bevetelGomb.setToolTipText("Aktuális összesített bevétel megtekintése.");
        
        
        toolbar.add(tervezoGomb);
        toolbar.add(bevetelGomb);
        toolbar.add(kilepesGomb);
        
        vertical.add(asztaltFoglal);
        vertical.add(asztaltMenuRendeles);
        vertical.add(fizetesGomb);
        vertical.add(asztaltFelszabadit);
        vertical.add(racsozasGomb);
        
        center = szobaValaszto;
       
	}

	/**
	 * Törli a kijelölt asztal foglalást.
	 */
	private void megjelenitLogikaPanel() {
	
		if(!AsztalFoglalasParancsKezelo.vanKijelolt){
			JOptionPane.showMessageDialog ( null, "Jelöljön ki egy foglalt asztalt!" ); 	   
			return;
		}
		
		int id = asztalFoglalasVezerlo.getIdByPozicio(
				AsztalFoglalasParancsKezelo.kijeloltSzoba, 
				AsztalFoglalasParancsKezelo.kijeloltPozicio);	
		//System.out.println(id);
		if(id != 0){
			asztalFoglalasVezerlo.torolFoglalas(id);
			szobaValaszto.szobatFrissitParancs();
		}
		else{
			JOptionPane.showMessageDialog ( null, "Ez az asztal ures, jelöljön ki egy foglalt asztalt!" ); 	   
		}
	}
	
	
	/***/
	
	/**
	 * Ha üres és létező asztal, új asztalfoglaláshoz nyit meg egy új foglalás kezelő ablakot.
	 */
	private void asztaltFoglalParancs() {
		
		if(!AsztalFoglalasParancsKezelo.vanKijelolt) return;

		if(!asztalFoglalasVezerlo.isFoglaltPozicionLevoAsztal(
				AsztalFoglalasParancsKezelo.kijeloltSzoba, 
				AsztalFoglalasParancsKezelo.kijeloltPozicio)){
			
			
	        SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
					
	            	UjRendeles rendelesAblak;
					try {
						rendelesAblak = new UjRendeles(
								asztalFoglalasVezerlo,
								AsztalFoglalasParancsKezelo.kijeloltSzoba,
								asztalFoglalasVezerlo.getElrendezesVezerlo().getAsztal(AsztalFoglalasParancsKezelo.kijeloltSzoba, AsztalFoglalasParancsKezelo.kijeloltPozicio)
								);
		            	rendelesAblak.init(600, 400);
		            	rendelesAblak.setVisible(true);
					} catch (NincsIlyenHelysegException e) {
						System.out.println(e.getMessage());
					} catch (NincsIlyenAsztalException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

	            }
	        });
			
		}
		else{
			JOptionPane.showMessageDialog ( null, "Jelöljön ki egy nem foglalt asztalt!" ); 		   
		}
	}
	
	/***/
	
	/**
	 * Rácsozottan jeleníti meg a szobát ha nincs rácsozva, ha már rácsozva van akkor pedig eltörli a rácsozást.
	 */
	private void szobaRacsozasaParancs() {
		szobaValaszto.toltelekKeretezese();
		szobaValaszto.szobatFrissitParancs();
	}
	
	/***/
	
	/**
	 * Megnyitja egy kijelölt foglal asztalhoz társítva a menü összerakó ablakot.
	 */
	private void menuRendelesParancs() {
		
		if(!AsztalFoglalasParancsKezelo.vanKijelolt) return;
		
		if(asztalFoglalasVezerlo.isFoglaltPozicionLevoAsztal(
				AsztalFoglalasParancsKezelo.kijeloltSzoba, 
				AsztalFoglalasParancsKezelo.kijeloltPozicio)){
			
	        SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
					
	            	EteltHozzaad rendelesAblak;
					try {
						rendelesAblak = new EteltHozzaad(
								asztalFoglalasVezerlo,
								AsztalFoglalasParancsKezelo.kijeloltSzoba,
								asztalFoglalasVezerlo.getElrendezesVezerlo().getAsztal(AsztalFoglalasParancsKezelo.kijeloltSzoba, AsztalFoglalasParancsKezelo.kijeloltPozicio)
								);
		            	rendelesAblak.init(600, 400);
		            	rendelesAblak.setVisible(true);
					} catch (NincsIlyenHelysegException e) {
						System.out.println(e.getMessage());
					} catch (NincsIlyenAsztalException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

	            }
	        });
			
		}
		else{
			JOptionPane.showMessageDialog ( null, "Jelöljön ki egy foglalt asztalt!" ); 		   
		}
	}
	
	/***/
	
	/**
	 * Menyitja a rendelés fizetés ablakot ha az lehetséges.
	 */
	private void fizetesParancs() {
		
		if(!AsztalFoglalasParancsKezelo.vanKijelolt) return;
		
		if(asztalFoglalasVezerlo.isFoglaltPozicionLevoAsztal(
				AsztalFoglalasParancsKezelo.kijeloltSzoba, 
				AsztalFoglalasParancsKezelo.kijeloltPozicio)){
			
		
	        SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
					int id = asztalFoglalasVezerlo.getIdByPozicio(
							AsztalFoglalasParancsKezelo.kijeloltSzoba, 
							AsztalFoglalasParancsKezelo.kijeloltPozicio);
					
					RendelesFizetes rendelesAblak;
					try {
						rendelesAblak = new RendelesFizetes(
								asztalFoglalasVezerlo,
								AsztalFoglalasParancsKezelo.kijeloltSzoba,
								asztalFoglalasVezerlo.getElrendezesVezerlo().getAsztal(AsztalFoglalasParancsKezelo.kijeloltSzoba, AsztalFoglalasParancsKezelo.kijeloltPozicio)
								);
		            	rendelesAblak.init(600, 400);
		            	rendelesAblak.setVisible(true);
					} catch (NincsIlyenHelysegException e) {
						System.out.println(e.getMessage());
					} catch (NincsIlyenAsztalException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

	            }
	        });
			
		}
		else{
			JOptionPane.showMessageDialog ( null, "Jelöljön ki egy foglalt asztalt!" ); 		   
		}
	}
	
	/***/
	
	/**
	 * Megnyitja az étterem tervező ablakot.
	 */
	private void tervezesParancs() {
		
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	TervezoWindow tervezoWindow = new TervezoWindow(asztalFoglalasVezerlo);
            	tervezoWindow.init();
            	tervezoWindow.setVisible(true);
            }
        });
	}
	

	/**
	 * Beállítja mit tegyen kilépéskor az ablak.
	 */
	protected void kilepesParancs() {
		
		ConnectionHelper.closeConnection();
		
		//LOG
		Log.logger.info("Grafikus felület bezárása, Program leállítása.");
		
		System.exit(0);
	}
	
}
