package hu.unideb.etterem.view;

import hu.unideb.etterem.controller.EtteremAdatkezelo;
import hu.unideb.etterem.model.Asztal;
import hu.unideb.etterem.model.AsztalFoglalas;
import hu.unideb.etterem.model.AsztalFoglalasVezerlo;
import hu.unideb.etterem.model.Etel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Az osztály reprezentálja a rendelések kifizetésének ablakát.
 * 
 * @author Szabo Tamas
 *
 */
public class RendelesFizetes extends MyFrame{
	
	/**
	 * A háttér színe. 
	 */
	protected static final Color ALAPSZIN = new Color(255,250,205);

	/**
	 * Ezen referencián keresztül el tudja érni az model adatait.
	 */
	private AsztalFoglalasVezerlo asztalFoglalasVezerlo;
	
	/**
	 * A szoba neve ahova tervezi a foglalást. 
	 */
	private String szobaNev;
	
	/**
	 * Adott rendelést reprezentáló asztalfoglalás.
	 * */
	private AsztalFoglalas asztalFoglalas;

	/**
	 * Konstruktor {@code UjRendeles} objektum példányosításához.
	 * 
	 * @param asztalFoglalasVezerlo ev
	 * @param szobaNev szoba neve ahova tervezi a foglalást
	 * @param asztal asztal ahova tervezi a foglalást
	 */
	public RendelesFizetes(AsztalFoglalasVezerlo asztalFoglalasVezerlo, 
			String szobaNev, Asztal asztal) {
		super();
		this.asztalFoglalasVezerlo = asztalFoglalasVezerlo;
		this.szobaNev = szobaNev;

	
		int id = asztalFoglalasVezerlo.getIdByPozicio(
				szobaNev, 
				AsztalFoglalasParancsKezelo.kijeloltPozicio);
		asztalFoglalas = asztalFoglalasVezerlo.getFoglalas(id);
	}

	/**
	 * Alapértelmezett inicializáló beállítások.
	 * 
	 * @param x az ablak szélessége
	 * @param y az ablak magasságe
	 */
	public void init(int x, int y){
		center.setLayout(null);
		

		JLabel szobaLabel = new JLabel(szobaNev);
		szobaLabel.setBounds(300, 10, 100, 20);
		
		JLabel nevLabel = new JLabel(asztalFoglalas.getFelelos().getNev());
		nevLabel.setBounds(300, 30, 100, 20);
		
		JLabel cimLabel = new JLabel(asztalFoglalas.getFelelos().getCim());
		cimLabel.setBounds(300, 50, 100, 20);
		
		JLabel telefonszamLabel = new JLabel(asztalFoglalas.getFelelos().getSzamlaSzam());
		telefonszamLabel.setBounds(300, 70, 100, 20);
		
		JLabel vonal2 = new JLabel("-------------");
		vonal2.setBounds(300, 18, 100, 20);
		
		JLabel vonal = new JLabel("-------------");
		vonal.setBounds(300, 90, 100, 20);
		
		kiirKosar();
		
		center.add(szobaLabel);
		center.add(nevLabel);
		center.add(cimLabel);
		center.add(telefonszamLabel);
		center.add(vonal);
		center.add(vonal2);
		/***/
		
		center.setBackground(ALAPSZIN);
		
		setTitle("Számla fizetés.");
		defaultInit(x, y);
	}

	
	/**
	 * A kosár panelen való megjelenítése. 
	 */
	public void kiirKosar(){		
		int startTop=20;
		int szamlalo = 1;
		for(Etel etel : asztalFoglalas.getRendeltek().keySet() ){
			for(int i=0; i< asztalFoglalas.getRendeltek().get(etel) ; i++){
				JLabel etelLabel = new JLabel( szamlalo +". "+ etel.getNev() +": "+ etel.getAr() +" Ft");
				etelLabel.setBounds(10, startTop, 300, 20);
				
				center.add(etelLabel);
				
				startTop += 20;
				szamlalo++;
			}
		}
		
		szamlalo--;
		JLabel felirat = new JLabel("Rendelt ételek: ( " + szamlalo + " db )" );
		felirat.setBounds(10, 0, 300, 20);
		center.add(felirat);
		
		JLabel vegosszeg = new JLabel("Osszesen: " + asztalFoglalas.getVegOsszeg() +" Ft");
		vegosszeg.setBounds(300, 120, 300, 20);
		center.add(vegosszeg);
		
		int id = asztalFoglalasVezerlo.getIdByPozicio(szobaNev, AsztalFoglalasParancsKezelo.kijeloltPozicio);
		JLabel szamlaOsszeg = new JLabel("Fizetendő számla összeg: " + asztalFoglalasVezerlo.getVegosszeg(id) +" Ft");
		szamlaOsszeg.setBounds(300, 150, 300, 20);
		center.add(szamlaOsszeg);
		
		//Fizet�s gomb, sz�mla rendez�se
		JButton fizetGomb = new JButton("Fizetés");
		fizetGomb.setBounds(300, 180, 100, 30);
		center.add(fizetGomb);
		fizetGomb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				rendezSzamlatParancs();
			}
        });
		fizetGomb.addMouseListener(new StatusbarMouseAdapter(this, "Számla kifizetése."));
	}
	
	/**
	 * Mit tegyen az ablak bezáráskor.
	 */
	@Override
	protected void setBezaras() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	/**
	 * Előállítja a végösszeget, törli utána a rendelést.
	 * */
	protected void rendezSzamlatParancs() {
		int id = asztalFoglalasVezerlo.getIdByPozicio(szobaNev, AsztalFoglalasParancsKezelo.kijeloltPozicio);
		
		EtteremAdatkezelo.elmentVasarlas(asztalFoglalas.getFelelos(), asztalFoglalas.getIdopont(), asztalFoglalasVezerlo.getVegosszeg(id));
		
		asztalFoglalasVezerlo.rendezSzamlat(id);
		
		AsztalFoglalasParancsKezelo.frissitSzobat();
		
		dispose();
	}
	
}
