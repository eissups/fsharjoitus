package fsharjoitus;

/**
 * Luokka kirjautumistiedoille
 */
public class Kirjautumistiedot {
	
	  private String username;
	  private String password;

	  /**
	   * Palauttaa käyttäjänimen
	   * @return username
	   */
	  public String getUsername() {
	    return username;
	  }

	  
	  /**
	   * Asetetaan käyttäjänimi
	   * @param username käyttäjänimi
	   */
	  public void setUsername(String username) {
	    this.username = username;
	  }

	  
	  /**
	   * Palautetaan salasana
	   * @return salasana
	   */
	  public String getPassWord() {
	    return password;
	  }

	  
	  /**
	   * Asetetaan salasana
	   * @param password salasana
	   */
	  public void setPassword(String password) {
	    this.password = password;
	  }

	}

