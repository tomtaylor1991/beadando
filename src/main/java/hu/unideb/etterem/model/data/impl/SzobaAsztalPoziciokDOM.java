package hu.unideb.etterem.model.data.impl;

import hu.unideb.etterem.model.Asztal;
import hu.unideb.etterem.model.Szoba;
import hu.unideb.etterem.model.data.SzobaAsztalPoziciokDAO;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.stream.events.Attribute;
import javax.xml.transform.TransformerFactoryConfigurationError;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Az osztály felelős egy Szobák és bennük lévő Asztalokbl álló xml fájl kezeléséért.
 * 
 * @author Szabo Tamas
 *
 */
public class SzobaAsztalPoziciokDOM implements SzobaAsztalPoziciokDAO{
	
	/**
	 * Az Pozíciókat reprezentáló xml fájl elérési útja.
	 */
	private String fileNev;
	
	/**
	 * XML 'poziciok' tag.
	 */
	private static final String POZICIOK = "poziciok";
	
	/**
	 * XML 'pozicio' tag.
	 */
	private static final String POZICIO = "pozicio";
	
	/**
	 * XML 'asztalID' tag.
	 */
	private static final String ASZTALID = "asztalID";
	
	/**
	 * XML 'szobaID' tag.
	 */
	private static final String SZOBAID = "szobaID";
	
	/**
	 * XML 'x' tag.
	 */
	private static final String X = "x";
	
	/**
	 * XML 'y' tag.
	 */
	private static final String Y = "y";
	
	/**
	 * Konstruktor {@code SzobaAsztalPoziciokDOM} objektum példányosításáért.
	 * 
	 * @param fileNev az xml fájl elérési útja
	 */
	public SzobaAsztalPoziciokDOM(String fileNev) {
		super();
		this.fileNev = fileNev;
	}

	/**
	 * Elmenti xml fájlba Szobák és bennük lévő Asztalok kapcsolatát.
	 * 
	 * @param szobak melyik szobakat kell menteni
	 */
	@Override
	public void saveAsztalPoziciok(List<Szoba> szobak) {
		try {
			Document doc= DOMAlapmuveletek.getDocument();
			Element rootElement= doc.createElement(POZICIOK);
			doc.appendChild(rootElement);
			for (Szoba szoba : szobak)
				for (Asztal asztal : szoba.getAsztalLista()){
					Element szobaElem = doc.createElement(POZICIO);
					szobaElem.setAttribute(ASZTALID, String.valueOf( asztal.getID() ) );
					szobaElem.setAttribute(SZOBAID, String.valueOf( szoba.getSzobaID() ) );
					rootElement.appendChild(szobaElem);
					
					szobaElem.appendChild(doc.createElement(X)).appendChild(doc.createTextNode(String.valueOf( szoba.getPozicioById(asztal.getID()).getX() )));
					szobaElem.appendChild(doc.createElement(Y)).appendChild(doc.createTextNode(String.valueOf( szoba.getPozicioById(asztal.getID()).getY() )));
				}
			
			DOMAlapmuveletek.saveInstance(fileNev, doc);
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

	/**
	 * Visszadja xml fájlból beolvasva Szobák és bennük lévő Asztalok kapcsolatát.
	 * 
	 * @return Szobák és bennük lévő Asztalok kapcsolata
	 */
	public List<int[]> getPoziciok(){		
		List<int[]> positions = new LinkedList<int[]>();
		
		try {
			
			XMLEventReader eventReader = DOMAlapmuveletek.loadXMLReader(fileNev);
			
			if(eventReader == null) return new LinkedList<int[]>();
			
			int[] sor = null ;
			while (eventReader.hasNext()) {
				XMLEvent event = eventReader.nextEvent();
				
				if (event.isStartElement()){
					StartElement startElement = event.asStartElement();
					// If we have an item element, we create a new item
					if (startElement.getName().getLocalPart() == (POZICIO)){
				
						sor = new int[4];
					
						for(int i=0;i<sor.length;i++)
							sor[i]=-1;
						
					
						Iterator<Attribute> attributes = startElement.getAttributes();
			            while (attributes.hasNext()) {
			                Attribute attribute = attributes.next();
			                if (attribute.getName().toString().equals(ASZTALID)) {
			                	sor[0] = Integer.parseInt( attribute.getValue());
			                }
			                if (attribute.getName().toString().equals(SZOBAID)) {
			                	sor[1] = Integer.parseInt( attribute.getValue());
			                }			                
			             }
					}

			     
			        	  switch(event.asStartElement().getName().getLocalPart()){
			        	  case 	X:
			        		  event = eventReader.nextEvent();
			        		  sor[2] = Integer.parseInt( event.asCharacters().getData());
				              break;
			        	  case 	Y:
			        		  event = eventReader.nextEvent();
			        		  sor[3] = Integer.parseInt( event.asCharacters().getData());
				              break;	    
			        	  }

				}
				
		          if (event.isEndElement()) {
		   
		              EndElement endElement = event.asEndElement();
		              if (endElement.getName().getLocalPart() == (POZICIO)) {

		            	  if(sor != null)
		            		positions.add(sor);
		              }
		            }
			}
			 
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      
		return positions;
	}



}
