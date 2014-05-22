package hu.unideb.etterem.model.data.impl;

import hu.unideb.etterem.controller.Log;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Az osztály felelős JSON fájlok fájlműveleteinek kezeléséért.
 * 
 * @author Szabo Tamas
 *
 */
public class JsonKezeles {

	/**
	 * Elment egy objektumot JSON fájlba.
	 * @param fileNev a mentés elérési útja
	 * @param o az objektum referenciája, amelyet el kell menteni
	 */
	public static void saveToJSON(String fileNev, Object o){
        try(Writer writer = new OutputStreamWriter(new FileOutputStream(
        		JsonKezeles.class.getResource( "/" + fileNev ).getPath()
        		) , "UTF-8")){
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(o, writer);
            
			//LOG
			Log.logger.trace("JSON fájlba történt mentés -> " + fileNev);
			
        } catch (UnsupportedEncodingException e) {
        	
			//LOG
			Log.logger.error("JSON kódolási hiba: ({}) - {}" , fileNev, e.getMessage());
			
		} catch (FileNotFoundException e) {
			
			//LOG
			Log.logger.error("JSON elérési út nem található: ({}) - {}" , fileNev, e.getMessage());
			
		} catch (IOException e) {
			
			//LOG
			Log.logger.error("JSON mentéskor IO hiba történt: ({}) - {}" , fileNev, e.getMessage());
		}
	}
	

}
