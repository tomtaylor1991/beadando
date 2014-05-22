package hu.unideb.etterem.view;

import java.io.IOException;
import java.util.Properties;

import javax.swing.ImageIcon;

/**
 * Az osztály felelős az ikon képek erőforrásainak előállításáért.
 * 
 * @author TomTaylor
 *
 */
public class KepEroforrasKezelo {
	
	/**
	 * A kép erőforrások elérési útjait tartalmazó file. 
	 */
	private static final String URL = "/properties/kep_eroforrasok.properties";
	
	/**
	 * Alapértelmezett kép elérési útja.
	 */
	private static final String DEFAULT_IMAGE_URL = "/icons/default.png";
	/**
	 * A kép erőforrások elérési útjait tartalmazó properties objektum.
	 */
	private static Properties eroforrasok;

	static {
		eroforrasok = new Properties();
		try {
			eroforrasok.load( KepEroforrasKezelo.class.getResourceAsStream( URL ));
		} 
		catch (IOException e) {
			// nincs megfelelő propertie állomány az url -en
			//System.out.println("megfelelő propertie állomány az url -en");
		}
		catch(NullPointerException e){
			//System.out.println("null pointer állomány betöltésekor");
		}
	}
	
	/**
	 * Visszaad a kép erőforrás properties objektum alapján a megfelelő ImageIcon objektumot.
	 * 
	 * @param key a kép azonosítója a properties fájlban
	 * @return a megfelelő képerőforrás, feltéve hogy létezik ilyen kulccsal rendelkező kép, különben egy alapértelmezett kép fájl
	 */
	public static ImageIcon getIcon(String key) {
		String eleresiUt = eroforrasok.getProperty( key );
		
		ImageIcon kep;
			
			try{
				kep = new ImageIcon( KepEroforrasKezelo.class.getResource ( eleresiUt )  );
			}catch(NullPointerException e){
				try{
					kep = new ImageIcon( KepEroforrasKezelo.class.getResource ( DEFAULT_IMAGE_URL )  );
				}
				catch(NullPointerException e2){
					return new ImageIcon();
				}
			}

			
		return kep;
	}
	
	
}
