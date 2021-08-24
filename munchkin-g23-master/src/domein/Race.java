package domein;

/**
 *
 * @author stijn
 */
public class Race extends Kerkerkaart {

    /**
     *
     * @param kaartID
     * @param naam
     * @param info
     */
    public Race(int kaartID, String naam, String info) {
        super(kaartID, naam, info);
    }

    /**
     * verwijdert effect van een bepaalde speler
     *
     * @param speler
     */
    @Override
    public void verwijderEffect(Speler speler) {
        speler.veranderRas("human");
        if (getNaam().equals("elf")) {
            speler.voegRunAwayBonusToe(-1);
        }
    }

    /**
     * voert effect uit op bepaalde speler
     *
     * @param speler
     */
    @Override
    public void voerEffectUit(Speler speler) {
        speler.veranderRas(getNaam().toLowerCase());
        if (speler.getRas().equals("elf")) {
            speler.voegRunAwayBonusToe(geefEffect(getInfo()));
        }
    }

}
