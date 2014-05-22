package hu.unideb.etterem.model.data.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.google.gson.Gson;

import hu.unideb.etterem.model.Nevjegy;
import hu.unideb.etterem.model.data.NevjegyDAO;

/**
 * Az osztály felelős egy Éttermi Névejgy elemeivől álló json fájl kezeléséért.
 * 
 * @author Szabo Tamas
 *
 */
public class NevjegyJSON implements NevjegyDAO {
	
	/**
	 * Az Menüt reprezentáló json fájl elérési útja.
	 */
	private final String fileNev;
	
	/**
	 * Konstruktor {@code NevjegyJSON} objektum példányosításáért.
	 * 
	 * @param fileNev a json fájl elérési útja
	 */
	public NevjegyJSON(String fileNev) {
		this.fileNev = fileNev;
	}

	
	/**
	 * Elmenti egy névjegy adatait a megfelelő json fájlba.
	 * 
	 * @param nevjegy a nevjegy amelynek adatait kell elmenteni
	 */
	@Override
	public void saveNevjegy(Nevjegy nevjegy) {
		JsonKezeles.saveToJSON(fileNev, nevjegy);
	}
	
	/**
	 * Visszaad egy json fájlból beolvasott névjegy objektumot.
	 * 
	 * @return beolvasott névjegy objektum
	 */
	@Override
	public Nevjegy getNevjegy() {
		Gson gson = new Gson();
		Nevjegy  obj = null;	
		try {
	 
			 
	          BufferedReader br =  new BufferedReader(new InputStreamReader(
                      this.getClass().getResourceAsStream( fileNev )));
	 
			//convert the json string back to object
			obj = gson.fromJson(br, Nevjegy.class);
	 
			//System.out.println(obj);
	 
		} 
		catch (NullPointerException e) {
			return null;
		}
		return (Nevjegy)obj;
	}

}
