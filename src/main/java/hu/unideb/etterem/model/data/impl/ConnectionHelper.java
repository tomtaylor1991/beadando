package hu.unideb.etterem.model.data.impl;

import hu.unideb.etterem.controller.Log;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Az osztály felelős egy adatbázis kapcsolat kiépítéséért és ezen kapcsolat elérésének biztosításááért.
 * 
 * @author Szabo Tamas
 *
 */
public class ConnectionHelper {
	
	/**
	 * A kapcsolat megfelelő elérési url -je.
	 */
	private static final String url = "jdbc:oracle:thin:@db.inf.unideb.hu:1521:ora11g";
	
	/**
	 * Az adatbázis kapcsolat referenciája.
	 */
	private static Connection conn;
	
	/**
	 * Privát konstruktora a ConnectionHelper osztálynak.  
	 */
	private ConnectionHelper(){
		//csak osztályon belülről lehet példányosítani
	}
	
	/**
	 * Beállít egy adatbázis kapcsolatot és visszaadja annak a kapcsolatnak a referenciáját.
	 * 
	 * @param username a felhasználónév ami szükséges a kapcsolódáskor a felhasználó azonosításáért
	 * @param pass a jelszó ami szükséges a kapcsolódáskor a felhasználó azonosításáért
	 * @return visszaad egy megfelelően kiépített adatbázis kapcsolatot 
	 * @throws IOException ha I/O hiba történt adatbázis kapcsolat kiépítésekor, pl.: nincs internet kapcsolat
	 * @throws SQLException ha el lehet érni az adatbázist, viszont nem jól vannak megadva az elérési paraméterek
	 */
	public static Connection getConnection(String username, String pass) throws IOException, SQLException {
		if( conn == null){
			conn = DriverManager.getConnection(url, username, pass);
			/***/
			//LOG
			Log.logger.trace("Adatbázis kapcsolat létrehozva: {}", url);
		}
		return conn;	
	}
	
	/**
	 * Bezárja az adatbázis kapcsolatot, ha már fel van építeni.
	 */
	public static void closeConnection(){
		try {
			if(conn != null)
			{
				conn.close();
				//LOG
				Log.logger.trace("Adatbázis kapcsolat sikeres bezárása: {}", url);
			}
		} catch (SQLException e) {
			//LOG
			Log.logger.trace("Adatbázis kapcsolat sikertelen bezárása: {}", url);			
		}
	}
}
