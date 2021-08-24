package domein;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author stijn
 */
public class DomeinController {

    private Spel spel;
    private final SpelRepository spelRepository;
    private final SpelerRepository spelerRepository;
    private final KaartRepository kaartRepository;
    private final StapelRepository stapelRepository;

    /**
     * constructor maakt repositories aan die final zijn
     */
    public DomeinController() {
        spelRepository = new SpelRepository();
        spelerRepository = new SpelerRepository();
        kaartRepository = new KaartRepository();
        stapelRepository = new StapelRepository();
    }

    /**
     * haal de kaarten uit de repository en maakt een spel met die kaarten
     *
     * @param aantalSpelers
     */
    public void vulAantalSpelersIn(int aantalSpelers) {
        List<Kaart> kerkerkaarten = new ArrayList<>();
        List<Kaart> schatkaarten = new ArrayList<>();
        for (Kaart kaart : kaartRepository.geefKaarten()) {
            if (kaart instanceof Kerkerkaart) {
                kerkerkaarten.add((Kerkerkaart) kaart);
            } else if (kaart instanceof Schatkaart) {
                schatkaarten.add((Schatkaart) kaart);
            }
        }
        spel = new Spel(aantalSpelers, kerkerkaarten, schatkaarten);
    }

    /**
     * voegt een speler toe aan het spel met een naam en geslacht
     *
     * @param naam
     * @param geslacht
     */
    public void vulGegevensIn(String naam, char geslacht) {
        spel.voegSpelerToe(naam, geslacht);

    }

    /**
     * geeft een overzicht van alle spelers
     *
     * @return spelers
     */
    public List<List<String>> geefOverzichtSpelers() {
        return spel.geefSpelers();
    }

    /**
     * geeft overzicht van de kaarten in de hand van een speler
     *
     * @param naam
     * @return kaarten
     */
    public List<List<String>> geefOverzichtKaartenInHand(String naam) {
        return spel.geefKaartenInHand(naam);
    }

    /**
     * geeft overzicht van de afgelegde kaarten van een speler
     *
     * @param naam
     * @return kaarten
     */
    public List<List<String>> geefOverzichtAfgelegdeKaarten(String naam) {
        return spel.geefAfgelegdeKaarten(naam);
    }

    /**
     * geeft overzicht van de kaarten die helpen in het gevecht
     *
     * @return kaarten
     */
    public List<List<String>> geefOverzichtSupport() {
        List<List<String>> kaartLijst = new ArrayList<>();
        for (Kaart kaart : spel.getGevecht().getSupport().getKaarten()) {
            kaartLijst.add(kaart.geefInformatie());
        }
        return kaartLijst;
    }

    /**
     * geeft overzicht van de kaarten die tegenwerken in het gevecht
     *
     * @return kaarten
     */
    public List<List<String>> geefOverzichtOppose() {
        List<List<String>> kaartLijst = new ArrayList<>();
        for (Kaart kaart : spel.getGevecht().getOppose().getKaarten()) {
            kaartLijst.add(kaart.geefInformatie());
        }
        return kaartLijst;
    }

    /**
     * geeft overzicht van helpende spelers in gevecht
     *
     * @return spelers
     */
    public List<List<String>> geefOverzichtAlly() {
        return spel.geefHelpendeSpelers();
    }

    /**
     * geeft overzicht van monsters in gevecht
     *
     * @return monsters
     */
    public List<List<String>> geefOverzichtEnemy() {
        List<List<String>> kaartLijst = new ArrayList<>();
        for (Kaart kaart : spel.getGevecht().getEnemy()) {
            kaartLijst.add(kaart.geefInformatie());
        }
        return kaartLijst;
    }

    /**
     * geeft het totale gevechtsniveau van de helpende spelers in het gevecht
     *
     * @return totale gevechtsniveau helpende spelers
     */
    public int geefTotaleNiveauAlly() {
        int totaleNiveauAlly = spel.getGevecht().geefTotaleNiveauAlly();
        return totaleNiveauAlly;
    }

    /**
     * geeft het totale gevechtsniveau van de monsters in het gevecht
     *
     * @return totale gevechtsniveau monsters
     */
    public int geefTotaleNiveauEnemy() {
        int totaleNiveauEnemy = spel.getGevecht().geefTotaleNiveauEnemy();
        return totaleNiveauEnemy;
    }

    /**
     * bepaald ofdat er een winnaar is
     *
     * @return true or false
     */
    public boolean isWinnaar() {
        return spel.isWinnaar();
    }

    /**
     * geeft de naam van de winnaar
     *
     * @return naam
     */
    public String geefWinnaar() {
        return spel.geefNaamWinnaar();
    }

    /**
     * stopt het spel
     */
    public void stopSpel() {
        spel.stop();
    }

    /**
     * rolt de dobbelsteen voor een speler
     *
     * @param naam
     */
    public void rolDobbelsteen(String naam) {
        spel.rolDobbelsteen(naam);
    }

    /**
     * geeft het aantal ogen van de worp van de dobbelsteen
     *
     * @return aantal ogen van de worp
     */
    public int geefAantalOgenWorp() {
        return spel.geefAantalOgenWorp();
    }

    /**
     * geeft het totaal van bad stuff van de monsters in het gevecht
     *
     * @return totaal van bad stuff van de monsters
     */
    public int geefNegatieveEffectenVanMonsters() {
        return spel.geefNegatieveEffectenVanMonsters();
    }

    /**
     * bepaalt de volgorde van de beurten van de spelers
     */
    public void bepaalVolgordeBeurten() {
        spel.bepaalVolgordeBeurten();
    }

    /**
     * bepaalt de volgende speler die aan de beurt is
     */
    public void bepaalVolgendeSpeler() {
        spel.bepaalVolgendeSpeler();
    }

    /**
     * geeft de naam van de speler die aan de beurt is
     *
     * @return naam
     */
    public String geefNaamSpelerAanBeurt() {
        return spel.getSpelerAanBeurt().getNaam();
    }

    /**
     * bepaalt ofdat de speler te veel kaarten in zijn hand heeft
     *
     * @return true or false
     */
    public boolean controleerMaxKaartenInHand() {
        return spel.controleerMaxKaartenInHand();
    }

    /**
     * start het gevecht
     */
    public void startGevecht() {
        spel.startGevecht();
    }

    /**
     * laat een speler het gevecht beinvloeden
     *
     * @param invloed
     * @param naam
     */
    public void spelerBeinvloedtGevecht(String invloed, String naam) {
        spel.spelerBeinvloedtGevecht(invloed, naam);
    }

    /**
     * reset statussen van alle spelers
     */
    public void resetStatussenVanSpelers() {
        spel.resetStatussenVanSpelers();
    }

    /**
     * geeft het effect van een kaart
     *
     * @param kaartID
     * @param naam
     * @return
     */
    public String geefKaartEffect(int kaartID, String naam) {
        return spel.geefKaartEffect(kaartID, naam);
    }

    /**
     * geeft informatie van een kaart
     *
     * @param kaartID
     * @param naam
     * @return kaart
     */
    public List<String> geefKaart(int kaartID, String naam) {
        return spel.geefKaart(kaartID, naam).geefInformatie();
    }

    /**
     * geeft de ID van de getrokken kaart
     *
     * @return kaartID
     */
    public int geefGetrokkenKaartID() {
        return spel.geefGetrokkenKaartID();
    }

    /**
     * geeft de naam van de klasse van een kaart
     *
     * @param kaartID
     * @param naam
     * @return naam klasse
     */
    public String geefKlasseNaamVanKaart(int kaartID, String naam) {
        return spel.geefKlasseNaamVanKaart(kaartID, naam);
    }

    /**
     * geeft de namen van alle spelers behalve de speler die aan de beurt is
     *
     * @return array van namen
     */
    public List<String> geefNamenSpelersZonderSpelerAanBeurt() {
        List<String> namen = new ArrayList<>();
        for (Speler speler : spel.getSpelers()) {
            namen.add(speler.getNaam());
        }
        namen.remove(0);
        return namen;
    }

    /**
     * geeft de namen van de spelers die helpen in het gevecht
     *
     * @return array van namen
     */
    public List<String> geefNamenVanHelpendeSpelers() {
        List<String> namen = new ArrayList<>();
        for (List<String> speler : spel.geefHelpendeSpelers()) {
            namen.add(speler.get(0));
        }
        return namen;
    }

    /**
     * geeft een speler een kerkerkaart
     *
     * @param naam
     */
    public void geefSpelerEenKerkerkaart(String naam) {
        spel.geefSpelerEenKerkerkaart(naam);
    }

    /**
     * controleert ofdat de gespeelde kaart mag gespeeld worden
     *
     * @param kaartID
     * @param naam
     */
    public void controleerGespeeldeKaart(int kaartID, String naam) {
        spel.controleerGespeeldeKaart(kaartID, naam);
    }

    /**
     * bepaalt ofdat de speler het gevecht overleeft
     *
     * @param naam
     * @return true or false
     */
    public boolean controleerSpelerOverleeftGevecht(String naam) {
        return spel.controleerSpelerOverleeftGevecht(naam);
    }

    /**
     * laat de speler sterven
     *
     * @param naam
     */
    public void spelerSterft(String naam) {
        spel.spelerSterft(naam);
    }

    /**
     * verplaatst een kaart van de ene speler naar de andere speler
     *
     * @param kaartID
     * @param naam
     * @param target
     */
    public void verplaatsKaartVanSpelerNaarSpeler(int kaartID, String naam, String target) {
        spel.verplaatsKaartVanSpelerNaarSpeler(kaartID, naam, target);
    }

    /**
     * voert het effect van een kaart uit
     *
     * @param kaartID
     * @param naam
     */
    public void voerGespeeldeKaartEffectUit(int kaartID, String naam) {
        spel.voerGespeeldeKaartEffectUit(kaartID, naam);
    }

    /**
     * speelt een kaart weg
     *
     * @param kaartID
     * @param naam
     */
    public void speelKaart(int kaartID, String naam) {
        spel.speelKaart(kaartID, naam);
    }

    /**
     * verkoopt een kaart
     *
     * @param kaartID
     */
    public void verkoopKaart(int kaartID) {
        spel.verkoopKaart(kaartID);
    }

    /**
     * gooit een kaart weg
     *
     * @param kaartID
     */
    public void gooiKaartWeg(int kaartID) {
        spel.gooiKaartWeg(kaartID);
    }

    /**
     * bepaalt ofdat het gevecht gewonnen of verloren is
     *
     * @return true or false
     */
    public boolean evalueerGevecht() {
        return spel.evalueerGevecht();
    }

    /**
     * deelt beloningen uit aan de helpende spelers in gevecht
     */
    public void deelBeloningenUit() {
        spel.deelBeloningenUit();
    }

    /**
     * controleert ofdat de naam al bestaat
     *
     * @param naam
     * @return true of false
     */
    public boolean checkIfNameExists(String naam) {
        boolean nameExists = spelRepository.checkIfNameExists(naam);
        return nameExists;
    }

    /**
     * verwijdert een spel
     *
     * @param naam
     */
    public void verwijderVorigeSpel(String naam) {
        int spelID = spelRepository.getSpelID(naam);
        spelRepository.verwijderVorigeSpel(naam);
        spelerRepository.verwijderSpelers(spelID);
        List<Integer> stapelIDs = stapelRepository.verwijderStapels(spelID);
        kaartRepository.verwijderRelaties(stapelIDs);
    }

    /**
     * slaat een spel op
     *
     * @param naam
     */
    public void slaOp(String naam) {
        int spelID = spelRepository.spelOpslaan(naam);

        List<Speler> spelers = spel.getSpelers();
        String naamSpelerAanBeurt = spel.getSpelerAanBeurt().getNaam();

        for (int i = 0; i < spelers.size(); i++) {
            Speler speler = spelers.get(i);

            boolean aanBeurt = false;
            if (naamSpelerAanBeurt.equals(speler.getNaam())) {
                aanBeurt = true;
            }

            int spelerID = spelerRepository.spelerOpslaan(speler.getNaam(), speler.getGeslacht(), speler.getNiveau(), speler.getGold(), spelID, aanBeurt);

            Stapel hand = speler.getHand();
            Stapel afgelegd = speler.getAfgelegd();

            int handID = stapelRepository.stapelOpslaan("hand", spelerID, spelID);
            int afgelegdID = stapelRepository.stapelOpslaan("afgelegd", spelerID, spelID);

            List<Kaart> handKaarten = hand.getKaarten();
            for (int j = 0; j < handKaarten.size(); j++) {
                Kaart kaart = handKaarten.get(j);
                kaartRepository.kaartOpslaan(kaart.getKaartID(), handID);
            }

            List<Kaart> afgelegdKaarten = afgelegd.getKaarten();
            for (int j = 0; j < afgelegdKaarten.size(); j++) {
                Kaart kaart = afgelegdKaarten.get(j);
                kaartRepository.kaartOpslaan(kaart.getKaartID(), afgelegdID);
            }
        }

        Stapel kerkerStapel = spel.getKerkerStapel();
        Stapel schatStapel = spel.getSchatStapel();

        int kerkerStapelID = stapelRepository.stapelOpslaan("kerkerstapel", 0, spelID);
        int schatStapelID = stapelRepository.stapelOpslaan("schatstapel", 0, spelID);

        List<Kaart> kerkerKaarten = kerkerStapel.getKaarten();
        for (int i = 0; i < kerkerKaarten.size(); i++) {
            Kaart kaart = kerkerKaarten.get(i);
            kaartRepository.kaartOpslaan(kaart.getKaartID(), kerkerStapelID);
        }

        List<Kaart> schatKaarten = schatStapel.getKaarten();
        for (int i = 0; i < schatKaarten.size(); i++) {
            Kaart kaart = schatKaarten.get(i);
            kaartRepository.kaartOpslaan(kaart.getKaartID(), schatStapelID);
        }
    }

    /**
     * laadt een spel en verwijdert het uit de databank
     *
     * @param antwoord
     */
    public void voorbereidenLaden(String antwoord) {
        boolean nameExists = spelRepository.checkIfNameExists(antwoord);
        if (nameExists) {
            spelLaden(antwoord);
            verwijderVorigeSpel(antwoord);
        } else {
            throw new IllegalArgumentException("Name not found. Try again.");
        }
    }

    /**
     * geeft de namen van de spellen in de databank
     *
     * @return namen
     */
    public List<String> getBeschikbareSpelnamen() {
        List<String> namen = spelRepository.getBeschikbareSpelNamen();
        return namen;
    }

    /**
     * geeft het aantal spelers van een spel
     *
     * @param naam
     * @return aantalSpelers
     */
    public int getAantalSpelers(String naam) {
        int spelID = spelRepository.getSpelID(naam);
        return spelerRepository.getAantalSpelers(spelID);
    }

    /**
     * laadt een spel
     *
     * @param naam
     */
    public void spelLaden(String naam) {
        int spelID = spelRepository.getSpelID(naam);
        int aantalSpelers = spelerRepository.getAantalSpelers(spelID);

        int kerkerStapelID = stapelRepository.getStapelID("kerkerstapel", spelID);
        int schatStapelID = stapelRepository.getStapelID("schatstapel", spelID);
        List<Kaart> kerkerkaarten = kaartRepository.getKaarten(kerkerStapelID);
        List<Kaart> schatkaarten = kaartRepository.getKaarten(schatStapelID);

        spel = new Spel(aantalSpelers, kerkerkaarten, schatkaarten);

        List<Speler> spelers = spelerRepository.geefSpelers(spelID);
        for (int i = 0; i < spelers.size(); i++) {
            Speler speler = spelers.get(i);
            String spelerNaam = speler.getNaam();
            spel.loadSpeler(speler);

            int spelerID = spelerRepository.getSpelerID(spelID, spelerNaam);
            int handStapelID = stapelRepository.getStapelIDBySpelerID("hand", spelerID);
            int afgelegdStapelID = stapelRepository.getStapelIDBySpelerID("afgelegd", spelerID);
            List<Kaart> handKaarten = kaartRepository.getKaarten(handStapelID);
            List<Kaart> afgelegdKaarten = kaartRepository.getKaarten(afgelegdStapelID);
            for (Kaart kaart : handKaarten) {
                speler.voegKaartToeAanHand(kaart);
            }
            for (Kaart kaart : afgelegdKaarten) {
                speler.voegKaartToeAanAfgelegdeRuimte(kaart);
            }
            spel.herberekenSpelerStats(speler);
        }
        bepaalVolgordeBeurten();
        boolean aanBeurt = spelerRepository.checkAanBeurt(geefNaamSpelerAanBeurt(), spelID);
        while (aanBeurt == false) {
            spel.bepaalVolgendeSpeler();
            aanBeurt = spelerRepository.checkAanBeurt(geefNaamSpelerAanBeurt(), spelID);
        }

    }
}
