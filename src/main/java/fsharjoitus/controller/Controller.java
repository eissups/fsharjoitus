package fsharjoitus.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fsharjoitus.Kirjautumistiedot;
import fsharjoitus.Tietokanta;


/**
 * Vastaanottaa käyttäjältä tulevat pyynnöt ja vastaa niihin
 */
@org.springframework.stereotype.Controller
public class Controller {
	
	/**
	 * Näyttää kirjautumissivun
	 * @return kirjautumissivun
	 */
	@RequestMapping("/")
	public String start() {
		return "index";
	}
	
	/**
	 * 
	 * @param kirjautumistiedot käyttäjän lähettämät kirjautumistiedot
	 * @return response, vastaus käyttäjälle. Sisältää tiedon siitä onnistuiko sekä viestin
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/Login", method=RequestMethod.POST)
    public @ResponseBody Map tarkistaTiedot(Kirjautumistiedot kirjautumistiedot) {
		Boolean tiedotOikein = Tietokanta.tarkistaKirjautumistiedot(kirjautumistiedot);
		
        Map response = new HashMap();
        if (tiedotOikein) {
            response.put("success", true);;
        }
        else {
        	response.put("success", false);
            response.put("msg", "Väärä käyttäjänimi tai salasana");
        }
        System.out.println(response);
        return response;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/Tuotehaku", method=RequestMethod.POST)
    public @ResponseBody Map haeTuotetiedot(String tuotekoodi) {
		ArrayList<String> tuoteTiedot = Tietokanta.haeTiedot(tuotekoodi); 
		System.out.print(tuoteTiedot.size());
		
        Map response = new HashMap();
        if (tuoteTiedot.size() > 0) {
            response.put("success", true);
            response.put("msg", tuoteTiedot);
            response.put("tuotekoodi", tuotekoodi);
            response.put("tuotenimi", tuoteTiedot.get(0));
            response.put("tuotepaino", tuoteTiedot.get(1));
            response.put("energiamaara", tuoteTiedot.get(2));
            response.put("energiamaaraJ", tuoteTiedot.get(3));
        }
        else {
        	response.put("success", false);
            response.put("msg", "Ei tuotteita tällä koodilla");
        }
        
        return response;
	}
	/** Tuotekuvan haku, joka ei toimi:
	
	@RequestMapping(value="/Tuotekuva", method=RequestMethod.POST)
    public @ResponseBody byte[] haeTuotekuva(String tuotekoodi) {
		byte[] image = Tietokanta.haeKuva(tuotekoodi);
        return image;
	}
	*/
}