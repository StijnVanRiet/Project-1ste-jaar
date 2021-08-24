package domein;

/**
 *
 * @author stijn
 */
public class TreasureConsumeable extends Schatkaart {

    /**
     *
     * @param kaartID
     * @param naam
     * @param info
     * @param value
     */
    public TreasureConsumeable(int kaartID, String naam, String info, int value) {
        super(kaartID, naam, info, value);
    }

    /**
     * voert effect uit op een bepaalde speler
     *
     * @param speler
     */
    @Override
    public void voerEffectUit(Speler speler) {
        if (getInfo().contains("Go Up A Level")) {
            speler.gaEenLevelOmhoog();
        }
    }

}
