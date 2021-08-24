package domein;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author stijn
 */
public class Stapel {

    private List<Kaart> kaarten;

    /**
     *
     * @param kaarten
     */
    public Stapel(List<Kaart> kaarten) {
        setKaarten(kaarten);
    }

    private void setKaarten(List<Kaart> kaarten) {
        this.kaarten = kaarten;
    }

    /**
     * geeft de kaarten
     *
     * @return kaarten
     */
    public List<Kaart> getKaarten() {
        return this.kaarten;
    }

    /**
     * schudt de kaarten
     */
    public void schud() {
        Collections.shuffle(kaarten);
    }

    /**
     * voegt een kaart toe
     *
     * @param kaart
     */
    public void voegKaartToe(Kaart kaart) {
        kaarten.add(kaart);
    }

    /**
     * verwijdert een kaart
     *
     * @param kaart
     */
    public void verwijderKaart(Kaart kaart) {
        kaarten.remove(kaart);
    }

    /**
     * verwijdert alle kaarten
     */
    public void maakLeeg() {
        setKaarten(new ArrayList<>());
    }

}
