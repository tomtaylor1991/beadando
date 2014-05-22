package hu.unideb.etterem.view;

import hu.unideb.etterem.model.Asztal;
import hu.unideb.etterem.model.AsztalFoglalas;
import hu.unideb.etterem.model.AsztalFoglalasVezerlo;
import hu.unideb.etterem.model.Szemely;
import hu.unideb.etterem.model.exceptions.NincsElegHelyException;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


/**
 * Az osztály reprezentálja a foglalások létrehozásának ablakát.
 * 
 * @author Szabo Tamas
 *
 */
public class UjRendeles extends MyFrame{
	
	/**
	 * A háttér színe. 
	 */
	protected static final Color ALAPSZIN = new Color(255,250,205);
	
	/**
	 * A név tárolásáért felelő beviteli mező. 
	 */
	private JTextField szemelyNev;
	
	/**
	 * A cím tárolásáért felelő beviteli mező. 
	 */
	private JTextField szemelyCim;
	
	/**
	 * A számlaszám tárolásáért felelő beviteli mező. 
	 */
	private JTextField szemelyTelefonszam;
	
	/**
	 * A helyigény tárolásáért felelő beviteli mező. 
	 */
	private JTextField helyIgeny;
	
	/**
	 * Ezen referencián keresztül el tudja érni az model adatait.
	 */
	private AsztalFoglalasVezerlo asztalFoglalasVezerlo;
	
	/**
	 * A szoba neve ahova tervezi a foglalást. 
	 */
	private String szobaNev;
	
	/**
	 * A asztal ahova tervezi a foglalást. 
	 */
	private Asztal asztal;
	
	/**
	 * A asztal székeinek száma. 
	 */
	private int max;
	
	/**
	 * Konstruktor {@code UjRendeles} objektum példányosításához.
	 * 
	 * @param asztalFoglalasVezerlo ev
	 * @param szobaNev szoba neve ahova tervezi a foglalást
	 * @param asztal asztal ahova tervezi a foglalást
	 */
	public UjRendeles(AsztalFoglalasVezerlo asztalFoglalasVezerlo, 
			String szobaNev, Asztal asztal) {
		super();
		this.asztalFoglalasVezerlo = asztalFoglalasVezerlo;
		this.szobaNev = szobaNev;
		this.asztal = asztal;
		this.max = asztal.getSzekekSzama();
	}

	/**
	 * Alapértelmezett inicializáló beállítások.
	 * 
	 * @param x az ablak szélessége
	 * @param y az ablak magasságe
	 */
	public void init(int x, int y){
        ImageIcon mentesKilepesIkon = KepEroforrasKezelo.getIcon("mentesKilepesIkon");
        JButton mentesKilepes = new JButton(mentesKilepesIkon);
        mentesKilepes.setOpaque(false);
        mentesKilepes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                mentesKilepesParancs();
            }
		});
        mentesKilepes.addMouseListener(new StatusbarMouseAdapter(this, "Mentés és kilépés."));
        
 
        ImageIcon kilepesIkon = KepEroforrasKezelo.getIcon("kilepes");
        JButton kilepes = new JButton(kilepesIkon);
        kilepes.setOpaque(false);
        kilepes.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent e)
		            {
		                dispose();
		            }
		});
        kilepes.addMouseListener(new StatusbarMouseAdapter(this, "Kilépés mentés nélkül."));
        

        ImageIcon szovegTorolIkon = KepEroforrasKezelo.getIcon("szovegTorolIkon");
        JButton szovegTorol = new JButton(szovegTorolIkon);
        szovegTorol.setOpaque(false);
        szovegTorol.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				szovegTorolParancs();
			}
        });
        szovegTorol.addMouseListener(new StatusbarMouseAdapter(this, "Beviteli mezők tartalmának törlése."));
        
        
        vertical.add(mentesKilepes);
        vertical.add(kilepes);
        vertical.add(szovegTorol);
		
		center.setLayout(null);
		
		JLabel szobaNevLabel = new JLabel(szobaNev);
		szobaNevLabel.setBounds(400, 10, 100, 25);
		center.add(szobaNevLabel);
		
		JLabel szemelyNevLabel = new JLabel("Szamlazasi szemely neve:");
		szemelyNevLabel.setBounds(10, 10, 160, 25);
		center.add(szemelyNevLabel);

		szemelyNev = new JTextField(20);
		szemelyNev.setBounds(10, 40, 300, 25);
		center.add(szemelyNev);

		JLabel szemelyCimLabel = new JLabel("Cime:");
		szemelyCimLabel.setBounds(10, 80, 160, 25);
		center.add(szemelyCimLabel);

		szemelyCim = new JTextField(20);
		szemelyCim.setBounds(10, 110, 300, 25);
		center.add(szemelyCim);
		
		JLabel szemelyTelefonszamLabel = new JLabel("Szamla szam:");
		szemelyTelefonszamLabel.setBounds(10, 140, 160, 25);
		center.add(szemelyTelefonszamLabel);

		szemelyTelefonszam = new JTextField(20);
		szemelyTelefonszam.setBounds(10, 170, 300, 25);
		center.add(szemelyTelefonszam);
		
		JLabel helyIgenyLabel = new JLabel("Hely igeny:");
		helyIgenyLabel.setBounds(10, 200, 160, 25);
		center.add(helyIgenyLabel);

		helyIgeny = new JTextField(20);
		helyIgeny.setText("1");
		helyIgeny.setBounds(10, 230, 50, 25);
		center.add(helyIgeny);
		
		JLabel maxHelyAzAsztalnalLabel = new JLabel("(max hely: " + max +" )");
		maxHelyAzAsztalnalLabel.setBounds(65, 230, 160, 25);
		center.add(maxHelyAzAsztalnalLabel);
		
		center.setBackground(ALAPSZIN);
		
		/***/
		setTitle("Új rendelés");
		defaultInit(x, y);
	}

	/**
	 * Beviteli mezők szövegeinek kitörlése.
	 */
	private void szovegTorolParancs(){
		szemelyNev.setText("");
		szemelyCim.setText("");
		szemelyTelefonszam.setText("");
		helyIgeny.setText("1");
	}
	
	/**
	 * Beviteli mezők validálása.
	 * 
	 * @return true, ha minden mező helyesen van kitöltve, false különben
	 */
	private boolean validateMe(){
		String hibak = "Hibak: \n";
		boolean vanHiba = false;
		if(szemelyNev.getText().equals("")){
			vanHiba = true;
			hibak+="Nem irt be nevet!\n";
		}
		if(szemelyCim.getText().equals("")){
			vanHiba = true;
			hibak+="Nem irt be cimet!\n";
		}
		if(szemelyTelefonszam.getText().equals("")){
			vanHiba = true;
			hibak+="Nem irt be telefonszamot!\n";
		}
		if(helyIgeny.getText().equals("")){
			vanHiba = true;
			hibak+="Nem irt be helyigenyt!\n";
		}

		try{
			int hely = Integer.parseInt(helyIgeny.getText());
			if(hely > max){
				vanHiba = true;
				hibak+="Tobb embert akar az asztalhoz ultetni mint a max hely!\n";
			}
		}
		catch(NumberFormatException e){
			vanHiba = true;
			hibak+="Nem szamot adott meg helyigenynek!\n";
		}
		

		if(vanHiba){
			JOptionPane.showMessageDialog ( null, hibak ); 	
			return false;
		}
		else{
			return true;
		}
	}
	
	/**
	 * Kilépés előtt elmenti az objektumot és frissíti szülőjét.
	 */
	private void mentesKilepesParancs() {
		boolean isValid = validateMe();
        if(isValid){
			try {
				int foglaltHelySzam = Math.abs( Integer.parseInt(helyIgeny.getText()) );
				asztalFoglalasVezerlo.addAsztalFoglalas(
						new AsztalFoglalas(
								asztal, 
								new Szemely(
										szemelyNev.getText(),
										szemelyCim.getText(), 
										szemelyTelefonszam.getText()
									), 
						foglaltHelySzam
					));
				
				AsztalFoglalasParancsKezelo.frissitSzobat();
		
				dispose();
			} catch (NincsElegHelyException e1) {
				System.out.println(e1.getMessage());
			}
        }
	}
	
	/**
	 * Mit tegyen az ablak bezáráskor.
	 */
	@Override
	protected void setBezaras() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	
	
}
