/**
 * 
 */
package hu.unideb.etterem.model.data.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import hu.unideb.etterem.controller.Log;
import hu.unideb.etterem.model.data.VasarlasDAO;
import hu.unideb.etterem.model.interfaces.SzamlazasiFelelos;

/**
 * Az osztály felelős egy vásárlás dokumentálására adatbázisba mentve.
 * 
 * @author Szabo Tamas
 *
 */
public class VasarlasDB implements VasarlasDAO {

	/**
	 * Az adatbázis kapcsolathoz szükséges felhasználónév.
	 */
	private static final String USERNAME = "H_GU1GYP";
	
	
	/**
	 *Az adatbázis kapcsolathoz szükséges jelszó. 
	 */
	private static final String PASS = "kassai";
	
	/**
	 * SQL beillesztő parancs paraméteres mintája. 
	 */
	private static final String BEILLESZT = "insert into vasarlasok values (?,?,?,?,?,?)";
	
	/**
	 * SQL create parancs 'VASARLASOK' nevű tábla létrehozásához.
	 */
	private static final String CREATE_TABLE_VASARLASOK=  "CREATE TABLE VASARLASOK"+ 
													  " (	NEV VARCHAR2(255 BYTE), "+
																"CIM VARCHAR2(255 BYTE), "+
																"SZAMLASZAM VARCHAR2(255 BYTE), "+
																"DATUM VARCHAR2(255 BYTE), "+
																"VEGOSSZEG NUMBER(*,0), "+
																"FELTOLTVE VARCHAR2(36 BYTE)"+
															  " ) ";
	
	/**
	 * Elment adatbázisba egy vásárlás adatait.
	 * 
	 * @param szamlazasiFelelos ki volt a felelős személy
	 * @param datum mikor történt a vásárlás
	 * @param vegosszeg mekkora értékben történt vásárlás	 
	 * */
	@Override
	public void saveVasarlas(SzamlazasiFelelos szamlazasiFelelos, Date datum, int vegosszeg) {
		
		try {
			Connection conn = ConnectionHelper.getConnection( USERNAME, PASS );
			
			checkAndCreateTable(conn, "VASARLASOK", CREATE_TABLE_VASARLASOK);

			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			String datumKezdet = dateFormat.format(datum);
			String datumVeg = dateFormat.format(new Date());
			
			PreparedStatement pstmt = conn.prepareStatement( BEILLESZT );
			//paraméterezés
			pstmt.setString(1, szamlazasiFelelos.getNev() );
			pstmt.setString(2, szamlazasiFelelos.getCim() );
			pstmt.setString(3, szamlazasiFelelos.getSzamlaSzam() );
			pstmt.setString(4, datumKezdet );
			pstmt.setInt(5, vegosszeg );
			pstmt.setString(6, datumVeg );
			
			int eredmeny = pstmt.executeUpdate();

			pstmt.close();
			
			//LOG
			Log.logger.trace("Vásárlás részletei elmentve az adatbazisba! -> nev: {}, {} sor beszúrása", szamlazasiFelelos.getNev(), eredmeny);
			
		} catch (IOException | SQLException e) {
			
			//LOG
			Log.logger.error("Adatbázis mentéskor IO hiba történt: {}" , e.getMessage());
		}

	}
	
	/**
	 * Ellenőrzi az adott adatbázis kapcsolatban létezik -e adott nevű tábla, ha nem, akkor lefuttat egy create parancsot az adatbázison.
	 * @param conn az adott adatbázis kapcsolat
	 * @param tableName a tábla neve amit keresünk
	 * @param createQuery egy sql query amit le kell futtatni ha nincs az adatbázisban az adott nevű tábla
	 */
	public static void checkAndCreateTable(Connection conn, String tableName, String createQuery){

		try {
			 DatabaseMetaData metadata = conn.getMetaData();
			 ResultSet resultSet;
			 resultSet = metadata.getTables(null, null, tableName, null);

			 if(!resultSet.next()){
				 
				 PreparedStatement pstmt;
					try {
						pstmt = conn.prepareStatement( createQuery );
						int eredmeny = pstmt.executeUpdate();
						conn.commit();
						pstmt.close();
						
						//LOG
						Log.logger.info("Váráslások tábla létrehozva az adatbázisban!");
					} catch (SQLException e1) {
						//LOG
						Log.logger.error("Váráslások táblát jelentleg nem lehet létrehozni! - " + e1.getMessage());
					}				 
			 }

		} catch (SQLException e) {}
					
	}

}
