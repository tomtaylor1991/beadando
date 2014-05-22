package hu.unideb.etterem.model.data.impl;

import hu.unideb.etterem.controller.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;

/**
 * Az osztály felelős xml fájlok betöltéséért illetve lementéséért..
 * 
 * @author Szabo Tamas
 *
 */
public class DOMAlapmuveletek {

	/**
	 * Visszaad egy xml dokumentum kezelésére alkalmas felületet.
	 * 
	 * @return egy xml dokumentum kezelésére alkalmas felület
	 * @throws ParserConfigurationException ha kunfigurációs hiba lépne fel
	 */
	public static Document getDocument() throws ParserConfigurationException{
		DocumentBuilderFactory dbf= DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = dbf.newDocumentBuilder();
		Document doc= builder.newDocument();
		return doc;
	}
	
	/**
	 * Elmenti egy xml dokumentumot egy xml fájlba, feltéve, hogy az lehetséges.
	 * 
	 * @param fileNev az elérési út, hova mentse a dokumentumot
	 * @param doc melyik dokumentumot mentse xml fájlba
	 */
	public static void saveInstance(String fileNev, Document doc) {
		Transformer transformer;
		
		try {
			transformer = TransformerFactory.newInstance().newTransformer();
			//paraméterezés
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			//fájlba mentés
			transformer.transform(new DOMSource(doc), new StreamResult(new FileOutputStream( 
					new File(
							DOMAlapmuveletek.class.getResource( "/" + fileNev ).getPath()
							)
					)));

			//LOG
			Log.logger.trace("XML fájlba történt mentés -> " + fileNev);
			
		} catch (TransformerConfigurationException
				| TransformerFactoryConfigurationError e) {
			
			//LOG
			Log.logger.error(e.getMessage());
			
		} catch (FileNotFoundException e) {

			//LOG
			Log.logger.error("XML elérési út nem található: ({}) - {}" , fileNev, e.getMessage());
			
		} catch (TransformerException e) {
			
			//LOG
			Log.logger.error(e.getMessage());
		} catch (NullPointerException e) {
			
			//LOG
			Log.logger.error("Hiba xml fájl mentése közben: ({}) - {}" , fileNev, e.getMessage());
		}

	}
	
	/**
	 * Beolvas egy xml dokumentumot a megadott elérési úton lévő xml fájlból.
	 * 
	 * @param fileNev beolvasni kívánt xml dokumentum elérési útja
	 * @return XMLEventReader, ha létezett és valid xml fájlt adtak meg, null különben
	 */
	public static XMLEventReader loadXMLReader(String fileNev){
		XMLEventReader eventReader = null;
		try {
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			InputStream in = DOMAlapmuveletek.class.getClassLoader().getResourceAsStream(fileNev);
			eventReader = inputFactory.createXMLEventReader(in);
			
			//LOG
			Log.logger.trace("XML fájlból történt beolvasás <- " + fileNev);
			
		} catch (XMLStreamException e) {
			
			//LOG
			Log.logger.error("Hiba xml fájl beolvasása közben: ({}) - {}" , fileNev, e.getMessage());
		}
		
		return eventReader;
	}
	
}
