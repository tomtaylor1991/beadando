package hu.unideb.etterem.view;

import hu.unideb.etterem.model.AsztalFoglalasVezerlo;
import hu.unideb.etterem.model.Etterem;
import hu.unideb.etterem.model.Pozicio;
import hu.unideb.etterem.model.Szoba;
import hu.unideb.etterem.model.exceptions.NincsIlyenHelysegException;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

/**
 * Az osztály reprezentálja a szobákban való böngésző panel megjelenését és logikáját.
 * 
 * @author Szabo Tamas
 *
 */
public class SzobaValaszto extends JPanel implements IFrissithetoSzobaNezet{
	
	/**
	 * Az asztal színe. 
	 */
	protected static final Color ASZTALSZIN = new Color(240,230,140);
	
	/**
	 * A foglalt asztal színe. 
	 */
	protected static final Color FOGLALTASZTALSZIN = new Color(255,0,0);
	
	/**
	 * A padló színe. 
	 */
	protected static final Color PADLOSZIN = new Color(250,250,210);
	
	/**
	 * A háttér színe. 
	 */
	protected static final Color ALAPSZIN = new Color(255,255,255);
	
	/**
	 * A kijelölt asztal színe. 
	 */
	protected static final Color KIJELOLTSZIN = new Color(255,255,0);
	
	/**
	 * A panel háttér színe. 
	 */
	protected static final Color PANELSZIN = new Color(255,255,240);
	
	/**
	 * Az asztal keretének alap színe. 
	 */
	protected static final Color KERETSZIN = new Color(0,0,0);
	
	/**
	 * Az étterem amelynek szobáit asztalait szeretnénk reprezentálni.
	 */
	private Etterem etterem;
	
	/**
	 * Ezen referencián keresztül el tudja érni az model adatait.
	 */
	public AsztalFoglalasVezerlo asztalFoglalasVezerlo;
	
	/**
	 * A szoba asztalainak panelje.
	 */
	protected JPanel asztalok;
	
	/**
	 * A szobák között váltható combo box.
	 */
	protected JComboBox<String> helysegComboBox;

	/**
	 * Melyik asztal volt kijelöve aktuálisan.
	 */
	protected JButton elozoKijeloltGomb = new JButton(); 
	//kereseshez
	
	/**
	 * A keresés szűkítésének határértékét beállító beviteli mező. 
	 */
	protected JTextArea keresesKorlatozo;
	
	/**
	 * A keresés szűrését elindító gomb. 
	 */
	protected JButton keresesSzures;
	
	/**
	 * A szobában minden objektumot megmutató gomb. 
	 */
	protected JButton keresesAlapHelyzetbeAllitas;
	
	/**
	 * Mennyi a min hely amennyi kel la szárésez. 
	 */
	protected int maxHely;
	
	/**
	 * Rácsozást reprezentáló logikai változó. 
	 */
	protected boolean keretezesAzUresHelyekhez = false;
	
	/**
	 * Konstruktor {@code EtteremView} objektum példányosításához.
	 * 
	 * @param asztalFoglalasVezerlo az asztal foglalásokat reprezentáló objektum
	 */
	public SzobaValaszto(AsztalFoglalasVezerlo asztalFoglalasVezerlo) {
		maxHely = 0;
		this.etterem = asztalFoglalasVezerlo.getElrendezesVezerlo().getEtterem();
		this.asztalFoglalasVezerlo = asztalFoglalasVezerlo;
		
		init();
	}

	/**
	 * Alapértelmezett inicializáló beállítások.	 * 
	 */
	public void init(){
		
		setBackground(PANELSZIN);
		
		szuroGombokRender();
		
		AsztalFoglalasParancsKezelo.vanKijelolt = false;
		
		
		List<String> helysegNevek = etterem.getSzobaNevek();
		String[] helysegTomb = helysegNevek.toArray(new String[helysegNevek.size()] );
		helysegComboBox = new JComboBox<String>(helysegTomb);
		add(helysegComboBox);
		
		asztalok = new JPanel();
		add( asztalok );
		
		
		helysegComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AsztalFoglalasParancsKezelo.vanKijelolt = false;
				szobatFrissitParancs();
			}
        });
		
		szobatFrissitParancs();
	}

	/**
	 * Létrehozza a megfelelő gombokat, amelyek a szoba asztalainak férőhely szűréséért felelősek.
	 */
	protected void szuroGombokRender() {

		keresesKorlatozo = new JTextArea(2, 4);
		keresesKorlatozo.setBorder(BorderFactory.createLineBorder(Color.black));
		
		keresesSzures = new JButton("Hely szűrés");
		keresesAlapHelyzetbeAllitas = new JButton("Mindent mutat!");
		
		keresesSzures.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				szuresKorlatozasaParancs();
			}
        });
		keresesAlapHelyzetbeAllitas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				szuresDefaultraAllitasaParancs();
			}
        });
		
		add(keresesKorlatozo);
		add(keresesSzures);
		add(keresesAlapHelyzetbeAllitas);
	}
	
	/**
	 * Szűrés korlátozása parancs.
	 */
	protected void szuresKorlatozasaParancs(){
		try
		{
			int ertek = Integer.parseInt( keresesKorlatozo.getText() );
			maxHely=ertek;
			szobatFrissitParancs();
		}
		catch(java.lang.NumberFormatException e){
			//System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Alaphelyzetbe állítja a szűrést korlátozó objektumokat.
	 */
	protected void szuresDefaultraAllitasaParancs(){
		maxHely=0;
		keresesKorlatozo.setText("");
		szobatFrissitParancs();
	}
	
	/**
	 * Az egész szoba megjelenítésért felelős panelt újragenerálja.
	 */
	public void szobatFrissitParancs(){		
		String kivalasztottMenuPont = (String)helysegComboBox.getSelectedItem();
		try {
			Szoba szoba = etterem.getSzoba(kivalasztottMenuPont);
			AsztalFoglalasParancsKezelo.kijeloltSzoba = kivalasztottMenuPont;
			
			remove(asztalok);
			asztalok = getScrollPane ( getSzoba(szoba) );
			add(asztalok);
			validate();
			repaint();
			
			AsztalFoglalasParancsKezelo.vanKijelolt = false;
			AsztalFoglalasParancsKezelo.aktSzoba = (Szoba) szoba;
		} catch (NincsIlyenHelysegException e) {
			System.out.println(e.getMessage());	
		}
	}
	
	/**
	 * Becsomagol egy adott panelt scroll panellé és annak referenciáját visszaadja.
	 * @param panel melyik panelt kell becsomagolni
	 * @return  egy adott panelt scroll panellbe téve
	 */
	public JPanel getScrollPane(JPanel panel){
		JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(00, 00, 600, 400);
        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(600, 400));
        contentPane.add(scrollPane);
        
        return contentPane;
	}
	
	/**
	 * Kapott szoba objektumhoz kinézetet generál és az visszaadja.
	 * 
	 * @param szoba melyik szoba kinézetet generalja le
	 * @return kapott szoba objektum swing panel kinézete
	 */
	public JPanel getSzoba( Szoba szoba ){
		JPanel panel = new JPanel();
		panel.setBackground(PADLOSZIN);
		
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel.setLayout(new GridLayout(szoba.getMeretX(), szoba.getMeretY(), 0, 0));
        buildPanel(panel, szoba);
        	
        return panel;
	}

	/**
	 * Vezérli a felépítését a szobakezelő panelnek.
	 * 
	 * @param panel hova kell beilleszteni a szoba nézetet
	 * @param szoba melyik szobát kell felépíteni
	 */
	protected void buildPanel(JPanel panel, Szoba szoba) {
		
		Set<Pozicio> poziciok;
		List<Pozicio> foglaltak = asztalFoglalasVezerlo.getFoglaltAsztalokPozicioi(AsztalFoglalasParancsKezelo.kijeloltSzoba);
		
		if(maxHely==0)
			poziciok = szoba.getPoziciok();
		else
			poziciok = szoba.getLehtesegesJoAsztalPoziciok(maxHely);
        
        for(int i=1;i<=szoba.getMeretX();i++){
        	for(int j=1;j<=szoba.getMeretY();j++){

        		if(poziciok.contains(new Pozicio(i, j)))
        		{
        		
        			String felirat = String.format("%d,%d, (%d)", i, j, 
        					szoba.getAsztalok().get(new Pozicio(i, j)).getSzekekSzama() );
        			
        			final JButton gomb = new JButton(felirat);
        			
    	        	gomb.setHorizontalAlignment(SwingConstants.LEFT);
    	        	
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
        			
        			JLabel toltelek = new JLabel("");
        			
        			if(keretezesAzUresHelyekhez){
        				String felirat = String.format("%d,%d", i, j);
        				toltelek.setText(felirat);
        				toltelek.setBorder(BorderFactory.createLineBorder(KERETSZIN));
        			}
        			
        			toltelek.setPreferredSize(new Dimension( 50, 50 ));
        			toltelek.setOpaque(false);
        			
        			panel.add(toltelek);
        		}
	        	
        	}
        }
	}
	
	/**
	 * Egy bemeneti szöveget felbont és visszaadja a szöveg által tárolt pozíciót.
	 * 
	 * @param text bemeneti szöveg, pl: 1,2
	 * @return pozíció amelyet a szöveg reprezentált
	 */
	public static Pozicio getPozicioFromText(String text){
		String[] poz = text.split(",");
		return new Pozicio(
				Integer.parseInt(poz[0]), 
				Integer.parseInt(poz[1])
				);
	}
	
	/**
	 * Egy asztalt reprezentál JButtont kijelöltté tesz és az előző kijelöltet alaphelyzetbe állítja.
	 * 
	 * @param text bemeneti szöveg ami a gombon volt, alakja: x,y,férőhely
	 * @param aktualisGomb melyik gombra kattintottak a szoba panelen
	 */
	public void kijeloltteTesz(String text, JButton aktualisGomb){
		String[] poz = text.split(",");
		AsztalFoglalasParancsKezelo.kijeloltPozicio = getPozicioFromText(text);
		AsztalFoglalasParancsKezelo.kijeloltSzoba = (String)helysegComboBox.getSelectedItem();
		AsztalFoglalasParancsKezelo.vanKijelolt = true;
		//aktualisGomb.setBackground(KIJELOLTSZIN);
		aktualisGomb.setBorder(BorderFactory.createLineBorder(KIJELOLTSZIN));
		//elozoKijeloltGomb.setBackground(ASZTALSZIN);
		elozoKijeloltGomb.setBorder(BorderFactory.createLineBorder(KERETSZIN));
		elozoKijeloltGomb = aktualisGomb;
	}
	
	/**
	 * Bekeretezést reprezentáló logikai változó inverzzé tétele.
	 */
	public void toltelekKeretezese(){
		keretezesAzUresHelyekhez = !keretezesAzUresHelyekhez;
	}
}
