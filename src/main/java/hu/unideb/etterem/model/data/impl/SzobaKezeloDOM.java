package hu.unideb.etterem.model.data.impl;

import hu.unideb.etterem.model.Szoba;
import hu.unideb.etterem.model.data.SzobaDAO;
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
 * Az osztály felelős egy Szobák adatiból álló xml fájl kezeléséért.
 * 
 * @author Szabo Tamas
 *
 */
public class SzobaKezeloDOM implements SzobaDAO{
	
	/**
	 * Az Asztalokat reprezentáló xml fájl elérési útja.
	 */
	private final String fileNev;
	
	/**
	 * XML 'helysegek' tag.
	 */
	private static final String HELYSEGEK = "helysegek";
	
	/**
	 * XML 'helyseg' tag.
	 */
	private static final String HELYSEG = "helyseg";
	
	/**
	 * XML 'id' tag.
	 */
	private static final String ID = "id";
	
	/**
	 * XML 'nev' tag.
	 */
	private static final String NEV = "nev";
	
	/**
	 * XML 'meret_x' tag.
	 */
	private static final String X = "meret_x";
	
	/**
	 * XML 'meret_y' tag.
	 */
	private static final String Y = "meret_y";
	
	/**
	 * XML 'leiras' tag.
	 */
	private static final String LEIRAS = "leiras";
	
	
	/**
	 * Konstruktor {@code SzobaKezeloDOM} objektum példányosításáért.
	 * 
	 * @param fileNev az xml fájl elérési útja
	 */
	public SzobaKezeloDOM(String fileNev) {
		this.fileNev = fileNev;
	}

	
	/**
	 * Elment xml fájlba egy szobákkal teli listát .
	 * 
	 * @param szobak a szobákkal teli lista amelyet el kell menteni
	 */
	public void saveSzoba(List<Szoba> szobak){
		
		try {
			Document doc= DOMAlapmuveletek.getDocument();
			Element rootElement= doc.createElement(HELYSEGEK);
			doc.appendChild(rootElement);
			
			for (Szoba szoba : szobak){
				Element szobaElem = doc.createElement(HELYSEG);
				szobaElem.setAttribute(ID, String.valueOf( szoba.getSzobaID() ) );
				rootElement.appendChild(szobaElem);
				
				szobaElem.appendChild(doc.createElement(NEV)).appendChild(doc.createTextNode(szoba.getSzobaNev()));
				szobaElem.appendChild(doc.createElement(X)).appendChild(doc.createTextNode(String.valueOf( szoba.getMeretX() )));
				szobaElem.appendChild(doc.createElement(Y)).appendChild(doc.createTextNode(String.valueOf( szoba.getMeretY() )));
				szobaElem.appendChild(doc.createElement(LEIRAS)).appendChild(doc.createTextNode(szoba.getLeiras()));
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
	 * Visszaad egy xml fájlból beolvasott szobákból álló listát.
	 * 
	 * @return egy szobákból álló lista
	 */
	public List<Szoba> getSzobak(){
		  List<Szoba> szobak = new LinkedList<Szoba>();
		
	      try {

			XMLEventReader eventReader = DOMAlapmuveletek.loadXMLReader(fileNev);
			
			if(eventReader == null) return new LinkedList<>();
			
			Szoba szoba = null;
			while (eventReader.hasNext()) {
				XMLEvent event = eventReader.nextEvent();
				
				if(eventReader == null) return new LinkedList<>();
				
				if (event.isStartElement()){
					StartElement startElement = event.asStartElement();
					// If we have an item element, we create a new item
					if (startElement.getName().getLocalPart() == (HELYSEG)){
						
						szoba = new Szoba();

					
						Iterator<Attribute> attributes = startElement.getAttributes();
			            while (attributes.hasNext()) {
			                Attribute attribute = attributes.next();
			                if (attribute.getName().toString().equals(ID)) {
			                  szoba.setSzobaID( Integer.parseInt( attribute.getValue()) );
			                }
			             }
					}

			        	
			        	  switch(event.asStartElement().getName().getLocalPart()){
			        	  case NEV:
			        		  event = eventReader.nextEvent();
				              szoba.setSzobaNev(event.asCharacters().getData());
				              break;			        		  
			        	  case X:
			        		  event = eventReader.nextEvent();
			        		  szoba.setMeretX(Integer.parseInt( event.asCharacters().getData()) );
				              break;
			        	  case Y:
			        		  event = eventReader.nextEvent();
			        		  szoba.setMeretY(Integer.parseInt( event.asCharacters().getData()) );
				              break;	
			        	  case LEIRAS:
			        		  event = eventReader.nextEvent();
			        		  szoba.setLeiras(event.asCharacters().getData());
				              break;					              
			        	  }
				
				
				}
				
		          if (event.isEndElement()) {
		       
		              EndElement endElement = event.asEndElement();
		              if (endElement.getName().getLocalPart() == (HELYSEG)) {

		                szobak.add(szoba);
		              }
		            }
			}
			 
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      
		return szobak;
	}
	
}
