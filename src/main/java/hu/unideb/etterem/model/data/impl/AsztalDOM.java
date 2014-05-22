package hu.unideb.etterem.model.data.impl;

import hu.unideb.etterem.model.Asztal;
import hu.unideb.etterem.model.data.AsztalDAO;

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
 * Az osztály felelős egy Asztalokból álló xml fájl kezeléséért.
 * 
 * @author Szabo Tamas
 *
 */
public class AsztalDOM implements AsztalDAO{
	
	/**
	 * Az Asztalokat reprezentáló xml fájl elérési útja.
	 */
	private final String fileNev;
	
	/**
	 * XML 'asztalok' tag.
	 */
	private static final String ASZTALOK = "asztalok";
	
	/**
	 * XML 'asztal' tag.
	 */	
	private static final String ASZTAL = "asztal";
	
	/**
	 * XML 'id' tag.
	 */
	private static final String ID = "id";
	
	/**
	 * XML 'szekek_szama' tag.
	 */
	private static final String SZEKEKSZAMA = "szekek_szama";	
	
	/**
	 * Konstruktor {@code AsztalDOM} objektum példányosításáért.
	 * 
	 * @param fileNev az xml fájl elérési útja
	 */
	public AsztalDOM(String fileNev) {
		this.fileNev = fileNev;
	}

	/**
	 * Elment egy Asztalokból álló listát.
	 * 
	 * @param asztalok egy Asztalokból álló lista
	 */
	public void saveAsztalok(List<Asztal> asztalok){
		
		try {
			
			Document doc= DOMAlapmuveletek.getDocument();
			Element rootElement= doc.createElement(ASZTALOK);
			doc.appendChild(rootElement);
			
			for (Asztal asztal : asztalok){
				Element szobaElem = doc.createElement(ASZTAL);
				szobaElem.setAttribute(ID, String.valueOf( asztal.getID() ) );
				rootElement.appendChild(szobaElem);
				
				szobaElem.appendChild(doc.createElement(SZEKEKSZAMA)).appendChild(doc.createTextNode(String.valueOf( asztal.getSzekekSzama() ) ));
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
	 * Visszaad egy Asztalokból álló listát.
	 * 
	 * @return egy Asztalokból álló lista
	 */
	public List<Asztal> getAsztalok(){		
		List<Asztal> asztalok = new LinkedList<Asztal>();
		
		try {

			XMLEventReader eventReader = DOMAlapmuveletek.loadXMLReader(fileNev);
			
			if(eventReader == null){

				return new LinkedList<>();
			}
			
			Asztal asztal = null;
			while (eventReader.hasNext()) {
				XMLEvent event = eventReader.nextEvent();
				
				if (event.isStartElement()){
					StartElement startElement = event.asStartElement();
					// If we have an item element, we create a new item
					if (startElement.getName().getLocalPart() == (ASZTAL)){
					
						asztal = new Asztal();

						Iterator<Attribute> attributes = startElement.getAttributes();
			            while (attributes.hasNext()) {
			                Attribute attribute = attributes.next();
			                if (attribute.getName().toString().equals(ID)) {
			                	asztal.setAsztalID( Integer.parseInt( attribute.getValue()) );
			                }
			             }
					}

			       
			        	  switch(event.asStartElement().getName().getLocalPart()){
			        	  case SZEKEKSZAMA:
			        		  event = eventReader.nextEvent();
				              asztal.setSzekekSzama(Integer.parseInt( event.asCharacters().getData()) );
				              break;			        		  					              
			        	  }

				}
				
		          if (event.isEndElement()) {
	
		              EndElement endElement = event.asEndElement();
		              if (endElement.getName().getLocalPart() == (ASZTAL)) {

		                asztalok.add(asztal);
		              }
		            }
			}
			 
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      
		return asztalok;
	}
}
