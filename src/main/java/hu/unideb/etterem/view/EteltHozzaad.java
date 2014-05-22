package hu.unideb.etterem.view;

import hu.unideb.etterem.model.Asztal;
import hu.unideb.etterem.model.AsztalFoglalas;
import hu.unideb.etterem.model.AsztalFoglalasVezerlo;
import hu.unideb.etterem.model.Etel;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;

/**
 * Az osztály reprezentálja a foglalások étel hozzá rendelés ablakát.
 * 
 * @author Szabo Tamas
 *
 */
public class EteltHozzaad extends MyFrame{
	
	/**
	 * A háttér színe. 
	 */
	protected static final Color ALAPSZIN = new Color(255,250,205);
	
	/**
	 * Az étel kategóriákat tartalmazó combo box.
	 */
	private JComboBox<String> menuKategoriakComboBox;
	
	/**
	 * Ezen referencián keresztül el tudja érni az model adatait.
	 */
	private AsztalFoglalasVezerlo asztalFoglalasVezerlo;
	
	/**
	 * Adott szoba neve.
	 */
	private String szobaNev;
	
	/**
	 * Adott asztal neve.
	 */
	private Asztal asztal;
	
	/**
	 * Ezen referencián keresztül el tudja érni az model adatait.
	 */
	private AsztalFoglalas asztalFoglalas;

	//LIST BOXOK
	
	/**
	 * Étel objektumok listája. 
	 */
	private JList<Etel> etelLista;
	
	/**
	 * Étel objektumok 1. megjelenített listája. 
	 */
	private JList<Etel> list = new JList<Etel>();
	
	/**
	 * Étel objektumok 2 véglelges. megjelenített listája. 
	 */
	private JScrollPane lista = new JScrollPane();
	
	/**
	 * Kosárban lévő Étel objektumok listája. 
	 */
	private List<Etel> kosarEtelLista = new LinkedList<Etel>();
	
	/**
	 * Kosárban lévő Étel objektumok 1. megjelenített listája. 
	 */
	private JList<Etel> kosarList = new JList<Etel>();
	
	/**
	 * Kosárban lévő Étel objektumok 2 véglelges. megjelenített listája. 
	 */
	private JScrollPane kosarLista = new JScrollPane();
	
	/**
	 * Aktuálisan kiválasztott kategória neve.
	 */
	private String aktKategoria = "";
	
	/**
	 * Konstruktor {@code EteltHozzaad} objektum példányosításához.
	 * 
	 * @param asztalFoglalasVezerlo foglalás vezérlő
	 * @param szobaNev melyik szobában van a foglalás
	 * @param asztal melyik asztalnál van a foglalás
	 */
	public EteltHozzaad(AsztalFoglalasVezerlo asztalFoglalasVezerlo, 
			String szobaNev, Asztal asztal) {
		super();
		this.asztalFoglalasVezerlo = asztalFoglalasVezerlo;
		this.szobaNev = szobaNev;
		this.asztal = asztal;
		
		
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
		
		JLabel nevLabel = new JLabel(asztalFoglalas.getFelelos().getNev());
		nevLabel.setBounds(10, 240, 100, 20);
		JLabel szobaLabel = new JLabel(szobaNev);
		szobaLabel.setBounds(10, 260, 100, 20);
		center.add(nevLabel);
		center.add(szobaLabel);
		
		
		List<String> menuKategoriak = asztalFoglalasVezerlo.getMenu().getKategoriaNevekLista();
		String[] menuKategoriaTomb = menuKategoriak.toArray(new String[menuKategoriak.size()]);
		menuKategoriakComboBox = new JComboBox<String>(menuKategoriaTomb);
		menuKategoriakComboBox.setBounds(10, 10, 300, 25);
		menuKategoriakComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
            	listatFrissit();
            }
		});
			
		
		initKosar();
		addKosar(null);
		
		center.add(menuKategoriakComboBox);
		center.add(kosarLista);
	    center.add(lista);
	    
     
        ImageIcon eteltHozzaadIkon = KepEroforrasKezelo.getIcon("eteltHozzaadIkon");
        JButton eteltHozzaad = new JButton(eteltHozzaadIkon);
        eteltHozzaad.setOpaque(true);
        eteltHozzaad.setBounds(150, 240, 40, 40);
        eteltHozzaad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
            	addKosar(list.getSelectedValue());
            }
		});
        eteltHozzaad.addMouseListener(new StatusbarMouseAdapter(this, "Kijelölt Étel hozzáadása a kosárhoz."));
        center.add(eteltHozzaad);
        
       
        ImageIcon eteltTorolIkon = KepEroforrasKezelo.getIcon("eteltTorolIkon");
        JButton eteltTorol = new JButton(eteltTorolIkon);
        eteltTorol.setOpaque(true);
        eteltTorol.setBounds(200, 240, 40, 40);
        eteltTorol.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
            	System.out.println(kosarList.getSelectedIndex());
            	kosarEtelLista.remove(kosarList.getSelectedValue());
            	addKosar(null);
            }
		});
        eteltTorol.addMouseListener(new StatusbarMouseAdapter(this, "Kijelölt  Étel törlése a kosárból."));
        center.add(eteltTorol);
        
        
        ImageIcon mentesKilepesIkon = KepEroforrasKezelo.getIcon("mentesKilepesIkon");
        JButton mentesKilepes = new JButton(mentesKilepesIkon);
        mentesKilepes.setOpaque(false);
        mentesKilepes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
        		
        		asztalFoglalas.torolTeljesRendeles();
        		for(Etel etel: kosarEtelLista){
        			asztalFoglalas.addRendeles(etel, 1);
        		}
            }
		});
        mentesKilepes.addMouseListener(new StatusbarMouseAdapter(this, "Mentés."));
        

        ImageIcon kilepesIkon = KepEroforrasKezelo.getIcon("kilepes");
        JButton kilepes = new JButton(kilepesIkon);
        kilepes.setOpaque(false);
        kilepes.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent e)
		            {
		                dispose();
		            }
		});
        kilepes.addMouseListener(new StatusbarMouseAdapter(this, "Kilépés"));
        
      
        ImageIcon szovegTorolIkon = KepEroforrasKezelo.getIcon("szovegTorolIkon");
        JButton szovegTorol = new JButton(szovegTorolIkon);
        szovegTorol.setOpaque(false);
        szovegTorol.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
            	kosarEtelLista = new LinkedList<Etel>();
            	addKosar(null);
			}
        });
        szovegTorol.addMouseListener(new StatusbarMouseAdapter(this, "Töröl rendeléseket."));
        
        
        vertical.add(mentesKilepes);
        vertical.add(kilepes);
        vertical.add(szovegTorol);
		
		center.setBackground(ALAPSZIN);
		
		setTitle("Étel hozzáadása");
		defaultInit(x, y);
	}

	/**
	 * A kosár JList inicializálása, betölteni a korábban betett ételeket.
	 */
	public void initKosar(){
		for(Etel etel : asztalFoglalas.getRendeltek().keySet() ){
			
			for(int i=0; i< asztalFoglalas.getRendeltek().get(etel) ; i++){
				kosarEtelLista.add(etel);
			}
		}
	}
	
	/**
	 * Frissíti a megjelenített étel listát.
	 */
	public void listatFrissit(){

		aktKategoria = (String)menuKategoriakComboBox.getSelectedItem();
		List<Etel> etelLista = asztalFoglalasVezerlo.getMenu().getEtelek(aktKategoria);

		center.remove(lista);
		
        list = new JList(new Vector<Etel>(etelLista));
        
        list.setVisibleRowCount(10);
        list.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (renderer instanceof JLabel && value instanceof Etel) {
         
                    ((JLabel) renderer).setText( 
                    		((Etel) value).getNev()+" ( " + ((Etel) value).getAr() + " Ft )");
                }
                return renderer;
            }
        });
        
	    lista = new JScrollPane(list);
	    lista.setBounds(10, 40, 200, 200);
	    
	    center.add(lista);
	    center.repaint();
	    center.revalidate();
	}
	
	/**
	 * Hozzáad egy ételt a kosárhoz.
	 * 
	 * @param etel egy étel amit a kosárhoz kíván adni
	 */
	public void addKosar(Etel etel){

		if(etel != null)
			kosarEtelLista.add(etel);

		center.remove(kosarLista);
		
		kosarList = new JList(new Vector<Etel>(kosarEtelLista));
        
        kosarList.setVisibleRowCount(10);
        kosarList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (renderer instanceof JLabel && value instanceof Etel) {
               
                    ((JLabel) renderer).setText( 
                    		((Etel) value).getNev()+" ( " + ((Etel) value).getAr() + " Ft )");
                }
                return renderer;
            }
        });
        
	    kosarLista = new JScrollPane(kosarList);
	    kosarLista.setBounds(210, 40, 200, 200);
	    
	    center.add(kosarLista);
	    center.repaint();
	    center.revalidate();
	}
	
	/**
	 * Mit tegyen az ablak bezáráskor.
	 */
	@Override
	protected void setBezaras() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	
	
}
