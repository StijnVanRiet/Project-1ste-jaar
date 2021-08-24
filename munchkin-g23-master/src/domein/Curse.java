package domein;

/**
 *
 * @author stijn
 */
public class Curse extends Kerkerkaart {

    /**
     *
     * @param kaartID
     * @param naam
     * @param info
     */
    public Curse(int kaartID, String naam, String info) {
        super(kaartID, naam, info);
    }

    /**
     *voert het effect uit op een speler
     * @param speler
     */
    @Override
    public void voerEffectUit(Speler speler) {
        speler.verliesLevel(geefEffect(getInfo()));
    }

}
