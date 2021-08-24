package domein;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author stijn
 */
public final class Gevecht {

    private Stapel support;
    private Stapel oppose;
    private List<Speler> ally;
    private List<Monster> enemy;

    /**
     *
     */
    public Gevecht() {
        setSupport();
        setOppose();
        setAlly();
        setEnemy();
    }

    private void setSupport() {
        List support = new ArrayList<Kaart>();
        this.support = new Stapel(support);
    }

    /**
     * geeft de helpende stapel kaarten
     *
     * @return stapel kaarten
     */
    public Stapel getSupport() {
        return support;
    }

    private void setOppose() {
        List oppose = new ArrayList<Kaart>();
        this.oppose = new Stapel(oppose);
    }

    /**
     * geeft tegenwerkende stapel kaarten
     *
     * @return stapel kaarten
     */
    public Stapel getOppose() {
        return oppose;
    }

    private void setAlly() {
        ally = new ArrayList<>();
    }

    /**
     * geeft helpende spelers
     *
     * @return helpende spelers
     */
    public List<Speler> getAlly() {
        return ally;
    }

    private void setEnemy() {
        enemy = new ArrayList<>();
    }

    /**
     * geeft monsters
     *
     * @return monsters
     */
    public List<Monster> getEnemy() {
        return enemy;
    }

    /**
     * voegt een helpende speler toe
     *
     * @param ally
     */
    public void voegAllyToe(Speler ally) {
        this.ally.add(ally);
    }

    /**
     * geeft totale gevechtsniveau van helpende spelers
     *
     * @return totale gevechtsniveau van helpende spelers
     */
    public int geefTotaleNiveauAlly() {
        int uitkomst = 0;
        //helpende spelers
        for (Speler speler : ally) {
            uitkomst += speler.getNiveau();
            uitkomst += speler.getCombatStrength();
        }
        //positieve kaarten
        for (Kaart kaart : support.getKaarten()) {
            uitkomst += kaart.geefEffect(kaart.getInfo());
        }
        return uitkomst;
    }

    /**
     * geeft totale gevechtsniveau van monsters
     *
     * @return totale gevechtsniveau van monsters
     */
    public int geefTotaleNiveauEnemy() {
        int uitkomst = 0;
        //monsters
        for (Monster monster : enemy) {
            uitkomst += monster.getLevel();
            if (!monster.getInfo().contains("Run Away")) {
                //extra effect op speler aan beurt
                uitkomst += monster.selecteerEffect(ally.get(0));
            }
        }
        //negatieve kaarten
        for (Kaart kaart : oppose.getKaarten()) {
            uitkomst += kaart.geefEffect(kaart.getInfo());
        }
        return uitkomst;
    }

    /**
     * bepaalt ofdat de spelers winnen of verliezen
     *
     * @return true or false
     */
    public boolean evalueer() {
        return geefTotaleNiveauAlly() - geefTotaleNiveauEnemy() > 0;
    }

    /**
     * geeft de totale beloning
     *
     * @return totale beloning
     */
    public int geefBeloning() {
        int beloning = 0;
        for (Monster monster : enemy) {
            beloning += monster.getReward();
        }
        return beloning;
    }

    /**
     * voert de negatieve effecten van de monsters uit op de speler
     *
     * @param speler
     */
    public void voerNegatieveEffectenVanMonstersOpSpelerUit(Speler speler) {
        speler.verliesLevel(geefNegatieveEffectenVanMonsters());
    }

    /**
     * bepaalt ofdat de speler het gevecht overleeft
     *
     * @param speler
     * @return true or false
     */
    public boolean controleerSpelerOverleeft(Speler speler) {
        int resultaat = speler.getNiveau() - geefNegatieveEffectenVanMonsters();
        return resultaat >= 1;
    }

    /**
     * geeft het totaal van de bad stuff van de monsters
     *
     * @return totaal van de bad stuff
     */
    public int geefNegatieveEffectenVanMonsters() {
        int badStuff = 0;
        for (Monster monster : enemy) {
            badStuff += monster.geefBadStuff();
        }
        return badStuff;
    }

    /**
     * voegt een kaart toe aan de juiste kant van he gevecht
     *
     * @param kaart
     * @param status
     */
    public void kaartBeinvloedt(Kaart kaart, String status) {
        if (kaart instanceof Monster) {
            enemy.add((Monster) kaart);
        } else if (kaart instanceof TreasureConsumeable) {
            if (!(kaart.getInfo().contains("Go Up A Level"))) {
                if (status.equals("help")) {
                    support.voegKaartToe(kaart);
                } else if (status.equals("counter")) {
                    oppose.voegKaartToe(kaart);
                }
            }
        } else if (kaart instanceof DungeonConsumeable) {
            oppose.voegKaartToe(kaart);
        }
    }

}
