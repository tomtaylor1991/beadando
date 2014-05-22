package hu.unideb.etterem.model.data.impl;

import hu.unideb.etterem.model.Etel;
import hu.unideb.etterem.model.Menu;
import hu.unideb.etterem.model.data.MenuDAO;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.TransformerFactoryConfigurationError;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Az osztály felelős egy Éttermi Menü elemeivől álló xml fájl kezeléséért.
 * 
 * @author Szabo Tamas
 *
 */
public class MenuDom implements MenuDAO{
	
	/**
	 * Az Menüt reprezentáló xml fájl elérési útja.
	 */
	private final String fileNev;
	
	/**
	 * XML 'menu' tag.
	 */
	private static final String MENU = "menu";
	
	/**
	 * XML 'etel' tag.
	 */
	private static final String ETEL = "etel";
	
	/**
	 * XML 'kategoria' tag.
	 */
	private static final String KATEGORIA = "kategoria";
	
	/**
	 * XML 'nev' tag.
	 */
	private static final String NEV = "nev";
	
	/**
	 * XML 'ar' tag.
	 */
	private static final String AR = "ar";
	
	/**
	 * XML 'tag' tag.
	 */
	private static final String LEIRAS = "leiras";
	
	/**
	 * Konstruktor {@code MenuDom} objektum példányosításáért.
	 * 
	 * @param fileNev az xml fájl elérési útja
	 */
	public MenuDom(String fileNev) {
		this.fileNev = fileNev;
	}

	/**
	 * Elment fájlba egy Menüt.
	 * 
	 * @param menuObj az elementeni kívánt Menu referenciája
	 */
	public void saveMenu(Menu menuObj ){
		Map< String, List<Etel> >menu = menuObj.getMenu(); 
		try {
			Document doc= DOMAlapmuveletek.getDocument();
			Element rootElement= doc.createElement(MENU);
			doc.appendChild(rootElement);
			
			for(String kategoria:menu.keySet())
				for (Etel etel : menu.get(kategoria)){
					Element elem = doc.createElement(ETEL);
					elem.setAttribute( KATEGORIA, kategoria );
					rootElement.appendChild(elem);
					
					elem.appendChild(doc.createElement(NEV)).appendChild(doc.createTextNode( etel.getNev() ));
					elem.appendChild(doc.createElement(LEIRAS)).appendChild(doc.createTextNode( etel.getLeiras() ));
					elem.appendChild(doc.createElement(AR)).appendChild(doc.createTextNode(String.valueOf( etel.getAr() )));

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
	 * Visszaad egy fájlból beovasott Menüt.
	 * 
	 * @return a betöltött Menüt
	 */
	public Menu getMenu(){
		Menu menu = new Menu();
	      try {

				XMLEventReader eventReader = DOMAlapmuveletek.loadXMLReader(fileNev);
				
				if(eventReader == null) return new Menu();
				
				Etel etel = null;
				String kategoria="";
				
				while (eventReader.hasNext()) {
					XMLEvent event = eventReader.nextEvent();
					
					if (event.isStartElement()){
						StartElement startElement = event.asStartElement();
						
						if (startElement.getName().getLocalPart() == (ETEL)){
							
							etel = new Etel();
							kategoria = "";
							
							Iterator<Attribute> attributes = startElement.getAttributes();
				            while (attributes.hasNext()) {
				                Attribute attribute = attributes.next();
				                if (attribute.getName().toString().equals(KATEGORIA)) {
				                	kategoria = attribute.getValue();
				                }
				             }
						}

				        	  
				        	  switch(event.asStartElement().getName().getLocalPart()){
				        	  case NEV:
				        		  event = eventReader.nextEvent();
					              etel.setNev(event.asCharacters().getData());
					              break;			        		  
				        	  case AR:
				        		  event = eventReader.nextEvent();
				        		  etel.setAr( Integer.parseInt( event.asCharacters().getData()) );
					              break;
				        	  case LEIRAS:
				        		  event = eventReader.nextEvent();
				        		  etel.setLeiras( event.asCharacters().getData() );
					              break;					              
				        	  }
					
					
					}
					
			          if (event.isEndElement()) {
			        	 
			              EndElement endElement = event.asEndElement();
			              if (endElement.getName().getLocalPart() == (ETEL)) {
			            	  menu.addEtel(kategoria, etel);
			              }
			            }
				}
				 
			} catch (XMLStreamException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		      
			return menu;

	}
}
