package fsharjoitus;

import java.sql.*;
import java.util.ArrayList;

/**
 * Haetaan tietoja tietokannasta
 */
public class Tietokanta {
	
	private static String connectionString = "jdbc:mysql://us-cdbr-east-04.cleardb.com/heroku_5c452f53fef7214";
	private static String username = "bf4ac269d86701";
	private static String password = "b55ad006";
	private static Connection connection;
	private static Statement command;
	private static ResultSet data;

	
	/**
	 * Voidaan lisätä käyttäjiä
	 * @param kayttajanimi käyttäjän käyttäjänimi
	 * @param salasana käyttäjän salasana
	 */
	public static void lisaaKayttaja(String kayttajanimi, String salasana) {
		try {
			connection = DriverManager.getConnection(connectionString, username, password);
			command = connection.createStatement();
			command.execute("INSERT INTO kayttaja (kayttajanimi, salasana ) VALUES ('" + kayttajanimi + "'" + ", '" + salasana + "')");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			
		} finally {
			System.out.println("Käyttäjä lisätty");
		}
	}
	
	
	/**
	 * Haetaan tuotteen tiedot eli tuotenimi, tuotepaino ja energiamäärä
	 * @param tuotekoodi halutun tuotteen koodi
	 * @return Lista, joka sisältää tuotteen tiedot
	 */
	public static ArrayList<String> haeTiedot(String tuotekoodi) {
		ArrayList<String> tiedot = new ArrayList<>();
		try {
			connection = DriverManager.getConnection(connectionString, username, password);
			command = connection.createStatement();
			System.out.println("SELECT tuotenimi, tuotepaino, energiamaara FROM tuote WHERE tuotekoodi = '" + tuotekoodi + "'" );
			data = command.executeQuery("SELECT tuotenimi, tuotepaino, energiamaaraC, energiamaaraJ FROM tuote WHERE tuotekoodi = '" + tuotekoodi + "';" );
			
		while (data.next()){	
			tiedot.add("Tuotenimi: " + data.getString("tuotenimi") + "\n");
			tiedot.add("Tuotepaino: " + data.getString("tuotepaino") + "g \n");
			tiedot.add("Energiamäärä: " + data.getString("energiamaaraC") + "kcal/");
			tiedot.add(data.getString("energiamaaraJ") + "kJ ");
		}
		
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return tiedot; 
	}
	
		
	/**
	 * Kirjautumistietojen haku ja niiden tarkistaminen
	 * @param kirjautumistiedot käyttäjän kirjautumistiedot
	 * @return true tai false riippuen siitä löytyikö täsmäävät kirjautumistiedot
	 */
	public static Boolean tarkistaKirjautumistiedot(Kirjautumistiedot kirjautumistiedot) {
		String user = kirjautumistiedot.getUsername();
		String passWord = kirjautumistiedot.getPassWord();
		
		try {
			connection = DriverManager.getConnection(connectionString, username, password);
			command = connection.createStatement();
			data = command.executeQuery("SELECT id FROM kayttaja WHERE kayttajanimi = '" + user + "' AND salasana = '" + passWord + "';");
			if (data.next() == false) return false;
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	
	
	/**
	 * Kuvan haku. Ongelmia jossain kohtaa
	 * @param tuotekoodi
	 * @return
	 */
	/**
	public static byte[] haeKuva(String tuotekoodi) {
		try {
			connection = DriverManager.getConnection(connectionString, username, password);
			command = connection.createStatement();
			data = command.executeQuery("SELECT kuva FROM kuva WHERE idtuote = '" + tuotekoodi + "';");
			if (data.next()) {
	            byte[] kuva = data.getBytes("kuva");
	            System.out.println(kuva);;

	            return kuva;
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	*/

	public static void main(String[] args) {
		
		//lisaaKayttaja("elisa", "voijuku");
		//haeTiedot("6420256001547");
		//haeKuva();
		//tarkistaKirjautumistiedot();
	}
}

