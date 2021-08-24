package domein;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author stijn
 */
public final class Spel {

    private Speler spelerAanBeurt;
    private final Dobbelsteen dobbelsteen;
    private Gevecht gevecht;
    private Stapel kerkerStapel;
    private Stapel schatStapel;
    private List<Speler> spelers;

    /**
     * constructor maakt een dobbelsteen die final is
     *
     * @param aantalSpelers
     * @param kerkerkaarten
     * @param schatkaarten
     */
    public Spel(int aantalSpelers, List<Kaart> kerkerkaarten, List<Kaart> schatkaarten) {
        dobbelsteen = new Dobbelsteen();
        setGevecht();
        setKerkerStapel(kerkerkaarten);
        setSchatStapel(schatkaarten);
        setSpelers(aantalSpelers);
    }

    private void setSpelerAanBeurt(Speler spelerAanBeurt) {
        this.spelerAanBeurt = spelerAanBeurt;
    }

    /**
     * geef de speler aan de beurt
     *
     * @return speler aan de beurt
     */
    public Speler getSpelerAanBeurt() {
        return this.spelerAanBeurt;
    }

    private void setGevecht() {
        gevecht = new Gevecht();
    }

    /**
     * geeft het gevecht
     *
     * @return gevecht
     */
    public Gevecht getGevecht() {
        return gevecht;
    }

    private void setKerkerStapel(List<Kaart> kerkerkaarten) {
        kerkerStapel = new Stapel(kerkerkaarten);
        kerkerStapel.schud();
    }

    /**
     * geeft de kerkerstapel
     *
     * @return kerkerstapel
     */
    public Stapel getKerkerStapel() {
        return kerkerStapel;
    }

    private void setSchatStapel(List<Kaart> schatkaarten) {
        schatStapel = new Stapel(schatkaarten);
        schatStapel.schud();
    }

    /**
     * geeft de schatstapel
     *
     * @return schatstapel
     */
    public Stapel getSchatStapel() {
        return schatStapel;
    }

    /**
     *
     * @param aantalSpelers
     */
    private void setSpelers(int aantalSpelers) {
        if (aantalSpelers >= 3 && aantalSpelers <= 6) {
            spelers = new ArrayList<>();;
        } else {
            throw new IllegalArgumentException("A game is played with at least three players and at most with six players. Try again.");
        }
    }

    /**
     * geeft de spelers
     *
     * @return spelers
     */
    public List<Speler> getSpelers() {
        return spelers;
    }

    /**
     * voegt een speler toe
     *
     * @param naam
     * @param geslacht
     */
    public void voegSpelerToe(String naam, char geslacht) {
        Speler spelerToegevoegd = new Speler(naam, geslacht);

        for (Speler speler : spelers) {
            if (naam.toLowerCase().equals(speler.getNaam().toLowerCase())) {
                throw new IllegalArgumentException("Another player already has this name. Try again.");
            }
        }
        spelers.add(spelerToegevoegd);
        geefSpelerEenBeginHand(naam);
    }

    /**
     *
     * @param speler
     */
    public void loadSpeler(Speler speler) {
        spelers.add(speler);
    }

    /**
     * bepaalt ofdat er een winnaar is
     *
     * @return true or false
     */
    public boolean isWinnaar() {
        return spelerAanBeurt.getNiveau() >= 10 && spelerAanBeurt.getStatus().equals("has defeated a monster");
    }

    /**
     * geeft de naam van de winnaar
     *
     * @return naam
     */
    public String geefNaamWinnaar() {
        if (isWinnaar()) {
            return spelerAanBeurt.getNaam();
        } else {
            return "";
        }
    }

    /**
     * geeft de speler een begin hand
     *
     * @param naam
     */
    public void geefSpelerEenBeginHand(String naam) {
        for (int i = 0; i < 2; i++) {
            geefSpelerEenKerkerkaart(naam);
            geefSpelerEenSchatkaart(naam);
        }
    }

    //het spel stopt
    /**
     * stopt het spel
     */
    public void stop() {
        System.exit(0);
    }

    /**
     * rolt de dobbelsteen
     *
     * @param naam
     */
    public void rolDobbelsteen(String naam) {
        dobbelsteen.rol();
        Speler speler = selecteerSpeler(naam);
        voegRunAwayBonusToeAanDobbelSteen(speler);
        if (geefAantalOgenWorp() < 5) {
            gevecht.voerNegatieveEffectenVanMonstersOpSpelerUit(speler);
        }
    }

    /**
     * voegt extra waarde toe aan de dobbelsteen
     *
     * @param speler
     */
    public void voegRunAwayBonusToeAanDobbelSteen(Speler speler) {
        int runAwayBonus = speler.getRunAwayBonus();
        int aantalOgen = dobbelsteen.getAantalOgen();
        for (Monster monster : gevecht.getEnemy()) {
            if (monster.selecteerEffect(speler) != 0 && monster.getInfo().contains("Run Away")) {
                runAwayBonus += monster.selecteerEffect(speler);
            } else if (monster.getInfo().contains("Escape is automatic")) {
                dobbelsteen.voegRunAwayBonusToe(aantalOgen * -1);
                runAwayBonus = 999;
            }
        }
        dobbelsteen.voegRunAwayBonusToe(runAwayBonus);
    }

    /**
     * geeft een lijst van spelers waarbij spelers een lijst zijn
     *
     * @return list
     */
    public List<List<String>> geefSpelers() {
        List<List<String>> spelerLijst = new ArrayList<>();
        for (Speler speler : spelers) {
            spelerLijst.add(speler.geefEigenschappen());
        }
        return spelerLijst;
    }

    /**
     * geeft het aantal ogen van de worp
     *
     * @return aantal ogen
     */
    public int geefAantalOgenWorp() {
        return dobbelsteen.getAantalOgen();
    }

    /**
     * geeft totaal van de bad stuff van de monsters in het gevecht
     *
     * @return totaal van de bad stuff
     */
    public int geefNegatieveEffectenVanMonsters() {
        return gevecht.geefNegatieveEffectenVanMonsters();
    }

    /**
     * bepaalt volgorde van de beurten
     */
    public void bepaalVolgordeBeurten() {
        //sorteer list spelers volgens DR_BEURT_SPELER 
        List<Speler> opNaamLengte = new ArrayList<>(spelers);
        Collections.sort(opNaamLengte, Comparator.comparing(speler -> speler.getNaam().length()));
        int kleinsteNaam = opNaamLengte.get(0).getNaam().length();

        List<Speler> opAlfabet = new ArrayList<>();
        for (Speler speler : opNaamLengte) {
            if (speler.getNaam().length() == kleinsteNaam) {
                opAlfabet.add(speler);
            }
        }
        Collections.sort(opAlfabet, Comparator.comparing(speler -> speler.getNaam().toLowerCase()));
        Collections.reverse(opAlfabet);
        Speler eersteSpeler = opAlfabet.get(0);

        spelers.remove(eersteSpeler);
        spelers.add(0, eersteSpeler);
        setSpelerAanBeurt(spelers.get(0));
    }

    /**
     * bepaalt volgende speler aan de beurt
     */
    public void bepaalVolgendeSpeler() {
        spelers.remove(spelerAanBeurt);
        spelers.add(spelerAanBeurt);
        setSpelerAanBeurt(spelers.get(0));
    }

    /**
     * bepaalt ofdat de speler teveel kaarten in zijn hand heeft
     *
     * @return true or false
     */
    public boolean controleerMaxKaartenInHand() {
        return spelerAanBeurt.controleerMaxKaartenInHand();
    }

    /**
     * selecteert een speler
     *
     * @param naam
     * @return speler
     */
    public Speler selecteerSpeler(String naam) {
        Speler speler = null;
        for (Speler eenSpeler : spelers) {
            if (eenSpeler.getNaam().equals(naam)) {
                speler = eenSpeler;
            }
        }
        if (speler == null) {
            throw new IllegalArgumentException("Player not found. Try again.");
        } else {
            return speler;
        }
    }

    /**
     * laat een speler het gevecht beinvloeden
     *
     * @param invloed
     * @param naam
     */
    public void spelerBeinvloedtGevecht(String invloed, String naam) {
        Speler speler = selecteerSpeler(naam);
        speler.veranderStatus(invloed);
        if (invloed.equals("help")) {
            gevecht.voegAllyToe(speler);
        }
    }

    /**
     * reset de statussen van alle spelers
     */
    public void resetStatussenVanSpelers() {
        for (Speler speler : spelers) {
            speler.veranderStatus("do nothing");
        }
    }

    /**
     * geeft de namen van spelers die helpen in het gevecht
     *
     * @return namen
     */
    public List<List<String>> geefHelpendeSpelers() {
        List<List<String>> spelerLijst = new ArrayList<>();
        for (Speler speler : gevecht.getAlly()) {
            spelerLijst.add(speler.geefEigenschappen());
        }
        return spelerLijst;
    }

    /**
     * geeft een speler een kerkerkaart
     *
     * @param naam
     */
    public void geefSpelerEenKerkerkaart(String naam) {
        Speler speler = selecteerSpeler(naam);
        Kaart kaart = kerkerStapel.getKaarten().get(0);
        kerkerStapel.verwijderKaart(kaart);
        speler.voegKaartToeAanHand(kaart);
    }

    /**
     * geeft speler een schatkaart
     *
     * @param naam
     */
    public void geefSpelerEenSchatkaart(String naam) {
        Speler speler = selecteerSpeler(naam);
        Kaart kaart = schatStapel.getKaarten().get(0);
        schatStapel.verwijderKaart(kaart);
        speler.voegKaartToeAanHand(kaart);
    }

    /**
     * start een gevecht
     */
    public void startGevecht() {
        setGevecht();
    }

    /**
     * geeft de kaartID van de getrokken kaart
     *
     * @return
     */
    public int geefGetrokkenKaartID() {
        return spelerAanBeurt.geefGetrokkenKaartID();
    }

    /**
     * geeft de naam van de klasse van een kaart
     *
     * @param kaartID
     * @param naam
     * @return naam klasse
     */
    public String geefKlasseNaamVanKaart(int kaartID, String naam) {
        Speler speler = selecteerSpeler(naam);
        Kaart kaart = speler.selecteerKaartOmTeSpelen(kaartID);
        return kaart.getClass().getSimpleName();
    }

    /**
     * geeft het effect van een kaart
     *
     * @param kaartID
     * @param naam
     * @return effect
     */
    public String geefKaartEffect(int kaartID, String naam) {
        Speler speler = selecteerSpeler(naam);
        Kaart kaart = speler.selecteerKaartOmTeSpelen(kaartID);
        return kaart.getInfo();
    }

    /**
     * geeft een kaart
     *
     * @param kaartID
     * @param naam
     * @return kaart
     */
    public Kaart geefKaart(int kaartID, String naam) {
        Speler speler = selecteerSpeler(naam);
        return speler.selecteerKaartOmTeSpelen(kaartID);
    }

    /**
     * controleert ofdat een kaart gespeeld mag worden
     *
     * @param kaartID
     * @param naam
     */
    public void controleerGespeeldeKaart(int kaartID, String naam) {
        Speler speler = selecteerSpeler(naam);
        String spelerAanBeurtNaam = spelerAanBeurt.getNaam();
        speler.controleerGespeeldeKaart(kaartID, spelerAanBeurtNaam);
    }

    /**
     * verplaatst kaart van de ene speler naar de andere
     *
     * @param kaartID
     * @param naam
     * @param target
     */
    public void verplaatsKaartVanSpelerNaarSpeler(int kaartID, String naam, String target) {
        Speler speler = selecteerSpeler(naam);
        Kaart kaart = speler.selecteerKaartOmTeSpelen(kaartID);
        Speler targetSpeler = selecteerSpeler(target);
        if (kaart instanceof Curse && !targetSpeler.getStatus().equals("help")) {
            throw new IllegalArgumentException("You cannot curse this player. Try Again.");
        }
        targetSpeler.voegKaartToeAanHand(kaart);
        speler.verwijderKaartUitBezit(kaart);
    }

    /**
     * voert het effect van een kaart uit op een speler
     *
     * @param kaartID
     * @param naam
     */
    public void voerGespeeldeKaartEffectUit(int kaartID, String naam) {
        Speler speler = selecteerSpeler(naam);
        Kaart kaart = speler.selecteerKaartOmTeSpelen(kaartID);
        kaart.voerEffectUit(speler);
    }

    /**
     * plaatst kaart onderaan juiste spelstapel
     *
     * @param kaart
     */
    public void voegKaartToeAanStapels(Kaart kaart) {
        if (kaart instanceof Kerkerkaart) {
            kerkerStapel.voegKaartToe(kaart);
        } else if (kaart instanceof Schatkaart) {
            schatStapel.voegKaartToe(kaart);
        }
    }

    /**
     * speelt kaart weg
     *
     * @param kaartID
     * @param naam
     */
    public void speelKaart(int kaartID, String naam) {
        Speler speler = selecteerSpeler(naam);
        Kaart kaart = speler.selecteerKaartOmTeSpelen(kaartID);
        speler.verwijderKaartUitBezit(kaart);

        if (kaart instanceof Equipment || kaart instanceof Race) {
            speler.voegKaartToeAanAfgelegdeRuimte(kaart);
            controleerSpelerBeperking(speler);
            herberekenSpelerStats(speler);
        } else {
            gevecht.kaartBeinvloedt(kaart, speler.getStatus());
            voegKaartToeAanStapels(kaart);
        }
    }

    /**
     * verkoopt kaart
     *
     * @param kaartID
     */
    public void verkoopKaart(int kaartID) {
        Kaart teVerkopenKaart = spelerAanBeurt.selecteerKaartOmTeBeheren(kaartID);

        //enkel equipment of schatconumeable verkopen
        if (!(teVerkopenKaart instanceof Equipment) && !(teVerkopenKaart instanceof TreasureConsumeable)) {
            throw new IllegalArgumentException("You cannot sell this card. Try again.");
        }

        //verkoop kaart
        int gold = ((Schatkaart) teVerkopenKaart).getValue();
        spelerAanBeurt.voegGoldToe(gold);

        //als equipment wordt verkocht van afgelegde ruimte --> bonussen aanpassen
        if (spelerAanBeurt.getAfgelegd().getKaarten().contains(teVerkopenKaart)) {
            teVerkopenKaart.verwijderEffect(spelerAanBeurt);
        }

        //speel kaart weg
        voegKaartToeAanStapels(teVerkopenKaart);
        spelerAanBeurt.verwijderKaartUitBezit(teVerkopenKaart);
    }

    /**
     * gooit kaart weg
     *
     * @param kaartID
     */
    public void gooiKaartWeg(int kaartID) {
        Kaart wegTeGooienKaart = spelerAanBeurt.selecteerKaartOmTeBeheren(kaartID);

        //als equipment of ras wordt weggegooid van afgelegde ruimte --> bonussen aanpassen
        if (spelerAanBeurt.getAfgelegd().getKaarten().contains(wegTeGooienKaart)) {
            wegTeGooienKaart.verwijderEffect(spelerAanBeurt);
        }

        //speel kaart weg
        voegKaartToeAanStapels(wegTeGooienKaart);
        spelerAanBeurt.verwijderKaartUitBezit(wegTeGooienKaart);
        //herbereken speler stats
        controleerSpelerBeperking(spelerAanBeurt);
        herberekenSpelerStats(spelerAanBeurt);
    }

    /**
     * herberekent de stats van een speler
     *
     * @param speler
     */
    public void herberekenSpelerStats(Speler speler) {
        speler.resetCombatEnRunAway();
        for (Kaart kaart : speler.getAfgelegd().getKaarten()) {
            kaart.voerEffectUit(speler);
        }
    }

    /**
     * bepaalt ofdat een speler het gevecht overleeft
     *
     * @param naam
     * @return true or false
     */
    public boolean controleerSpelerOverleeftGevecht(String naam) {
        Speler speler = selecteerSpeler(naam);
        return gevecht.controleerSpelerOverleeft(speler);
    }

    /**
     * controleert de beperkingen van de een speler
     *
     * @param speler
     */
    public void controleerSpelerBeperking(Speler speler) {
        //als ras verandert --> sommige uitrusting niet meer dragen
        List<Kaart> teVerwijderenKaarten = new ArrayList<>();
        for (Kaart kaart : speler.getAfgelegd().getKaarten()) {
            if (kaart instanceof Equipment) {
                if (((Equipment) kaart).selecteerEffect(speler) == 0) {
                    teVerwijderenKaarten.add(kaart);
                }
            }
        }

        List<Equipment> helm = new ArrayList<>();
        List<Equipment> vest = new ArrayList<>();
        List<Equipment> schoenen = new ArrayList<>();
        List<Equipment> wapens = new ArrayList<>();
        List<Race> ras = new ArrayList<>();
        for (Kaart kaart : speler.getAfgelegd().getKaarten()) {
            if (kaart instanceof Equipment) {
                switch (((Equipment) kaart).getEquipmentType()) {
                    case 'H':
                        helm.add((Equipment) kaart);
                        break;
                    case 'A':
                        vest.add((Equipment) kaart);
                        break;
                    case 'F':
                        schoenen.add((Equipment) kaart);
                        break;
                    case 'W':
                        wapens.add((Equipment) kaart);
                        break;
                }
            }
            if (kaart instanceof Race) {
                ras.add((Race) kaart);
            }
        }
        //max 1 helm, 1 vest, 1 paar schoenen
        if (helm.size() > 1) {
            teVerwijderenKaarten.add(helm.get(0));
        }
        if (vest.size() > 1) {
            teVerwijderenKaarten.add(vest.get(0));
        }
        if (schoenen.size() > 1) {
            teVerwijderenKaarten.add(schoenen.get(0));
        }
        //max 2 wapens, max 3 als dwarf
        if (speler.getRas().equals("dwarf")) {
            if (wapens.size() > 3) {
                teVerwijderenKaarten.add(wapens.get(0));
            }
        } else {
            if (wapens.size() > 2) {
                teVerwijderenKaarten.add(wapens.get(0));
            }
        }
        //max 1 ras
        if (ras.size() > 1) {
            teVerwijderenKaarten.add(ras.get(0));
        }

        for (Kaart kaart : teVerwijderenKaarten) {
            voegKaartToeAanStapels(kaart);
            speler.verwijderKaartUitBezit(kaart);
        }
    }

    /**
     * geeft de kaarten van de hand van een speler
     *
     * @param naam
     * @return kaarten
     */
    public List<List<String>> geefKaartenInHand(String naam) {
        Speler speler = selecteerSpeler(naam);
        Stapel hand = speler.getHand();
        List<List<String>> handLijst = new ArrayList<>();
        for (Kaart kaart : hand.getKaarten()) {
            handLijst.add(kaart.geefInformatie());
        }
        return handLijst;
    }

    /**
     * geeft de kaarten die een speler heeft afgelegd
     *
     * @param naam
     * @return kaarten
     */
    public List<List<String>> geefAfgelegdeKaarten(String naam) {
        Speler speler = selecteerSpeler(naam);
        Stapel afgelegd = speler.getAfgelegd();
        List<List<String>> afgelegdLijst = new ArrayList<>();
        for (Kaart kaart : afgelegd.getKaarten()) {
            afgelegdLijst.add(kaart.geefInformatie());
        }
        return afgelegdLijst;
    }

    /**
     * evalueert het gevecht op winst of verlies
     *
     * @return true or false
     */
    public boolean evalueerGevecht() {
        return gevecht.evalueer();
    }

    /**
     * laat een speler sterven
     *
     * @param naam
     */
    public void spelerSterft(String naam) {
        Speler speler = selecteerSpeler(naam);
        for (Kaart kaart : speler.getHand().getKaarten()) {
            voegKaartToeAanStapels(kaart);
        }
        for (Kaart kaart : speler.getAfgelegd().getKaarten()) {
            voegKaartToeAanStapels(kaart);
        }
        speler.sterf();
        geefSpelerEenBeginHand(naam);
    }

    /**
     * deelt de beloningen uit aan de helpende spelers in gevecht
     */
    public void deelBeloningenUit() {
        for (Speler speler : gevecht.getAlly()) {
            speler.gaEenLevelOmhoog();
        }
        int beloning = gevecht.geefBeloning();
        int counter = 0;
        Speler teBelonenSpeler = gevecht.getAlly().get(counter);
        for (int i = 0; i != beloning; i++) {
            Kaart kaart = schatStapel.getKaarten().get(0);
            schatStapel.verwijderKaart(kaart);
            teBelonenSpeler.voegKaartToeAanHand(kaart);
            if (counter == (gevecht.getAlly().size() - 1)) {
                counter = 0;
            } else {
                counter++;
            }
            teBelonenSpeler = gevecht.getAlly().get(counter);
        }
        spelerAanBeurt.veranderStatus("has defeated a monster");
    }

}
