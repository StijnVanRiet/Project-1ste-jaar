package domein;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author stijn
 */
public abstract class Kaart {

    private final int kaartID;
    private final String naam;
    private final String info;

    /**
     *
     * @param kaartID
     * @param naam
     * @param info
     */
    public Kaart(int kaartID, String naam, String info) {
        this.kaartID = kaartID;
        this.naam = naam;
        this.info = info;
    }

    /**
     * geeft kaartID
     *
     * @return kaartID
     */
    public int getKaartID() {
        return this.kaartID;
    }

    /**
     * geeft naam van de kaart
     *
     * @return naam
     */
    public String getNaam() {
        return naam;
    }

    /**
     * geeft info over de kaart
     *
     * @return info
     */
    public String getInfo() {
        return info;
    }
    
    /**
     * geeft een lijst van informatie
     *
     * @return list
     */
    public List<String> geefInformatie() {
        List<String> informatie = new ArrayList<>();
        informatie.add(this.getClass().getSimpleName());
        informatie.add(Integer.toString(kaartID));
        informatie.add(naam);
        informatie.add(info);
        return informatie;
    }

    /**
     * voert effect uit op een speler
     *
     * @param speler
     */
    public void voerEffectUit(Speler speler) {

    }

    /**
     * verwijdert effect van een speler
     *
     * @param speler
     */
    public void verwijderEffect(Speler speler) {

    }

    /**
     * geeft het effect uit de info van de kaart
     *
     * @param tekst
     * @return effect
     */
    public int geefEffect(String tekst) {
        int integer = 0;
        if (tekst != null) {
            String number = "0";
            for (char c : tekst.toCharArray()) {
                if (Character.isDigit(c)) {
                    number += c;
                }
            }
            integer = Integer.parseInt(number);
            if (info.contains("-")) {
                integer *= -1;
            }
        }
        return integer;
    }

}
