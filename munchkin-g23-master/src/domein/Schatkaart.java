package domein;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author stijn
 */
public abstract class Schatkaart extends Kaart {

    private final int value;

    /**
     *
     * @param kaartID
     * @param naam
     * @param info
     * @param value
     */
    public Schatkaart(int kaartID, String naam, String info, int value) {
        super(kaartID, naam, info);
        this.value = value;
    }

    /**
     * geeft de gold waarde
     *
     * @return gold waarde
     */
    public int getValue() {
        return value;
    }

    /**
     * geeft een lijst van informatie
     *
     * @return list
     */
    @Override
    public List<String> geefInformatie() {
        List<String> informatie = new ArrayList<>();
        informatie.add(this.getClass().getSimpleName());
        informatie.add(Integer.toString(getKaartID()));
        informatie.add(getNaam());
        informatie.add(getInfo());

        if (getValue() == 0) {
            informatie.add("No Value");
        } else {
            informatie.add(Integer.toString(getValue()) + " Gold Pieces");
        }

        return informatie;
    }

}
