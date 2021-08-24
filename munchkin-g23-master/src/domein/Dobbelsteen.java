package domein;

import java.util.Random;

/**
 *
 * @author stijn
 */
public class Dobbelsteen {

    private int aantalOgen = 1;
     private final Random random;

    /**
     * default constructor
     */
    public Dobbelsteen() {
        random = new Random();
    }

    private void setAantalOgen(int aantalOgen) {
        this.aantalOgen = aantalOgen;
    }

    /**
     * geeft aantal ogen
     *
     * @return aantal ogen
     */
    public int getAantalOgen() {
        return this.aantalOgen;
    }

    /**
     * rolt een waarde
     */
    public void rol() {
        setAantalOgen(1 + random.nextInt(6));
    }

    /**
     * voegt extra waarde toe aan aantal ogen
     *
     * @param runAwayBonus
     */
    public void voegRunAwayBonusToe(int runAwayBonus) {
        setAantalOgen(aantalOgen + runAwayBonus);
    }
}
