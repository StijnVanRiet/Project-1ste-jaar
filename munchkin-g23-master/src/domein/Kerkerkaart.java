package domein;

/**
 *
 * @author stijn
 */
public abstract class Kerkerkaart extends Kaart {

    /**
     *
     * @param kaartID
     * @param naam
     * @param info
     */
    public Kerkerkaart(int kaartID, String naam,  String info) {
        super(kaartID, naam, info);
    }

}
