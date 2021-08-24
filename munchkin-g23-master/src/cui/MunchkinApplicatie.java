package cui;

import domein.DomeinController;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 *
 * @author stijn
 */
public class MunchkinApplicatie {

    private Locale taal;
    private final DomeinController dc;

    /**
     * constructor maakt domeincontroller aan die final is
     */
    public MunchkinApplicatie() {
        dc = new DomeinController();
    }

    private void setTaal(String taal) {
        this.taal = new Locale(taal);
    }

    /**
     * use case 1 een nieuw spel starten of laden
     */
    public void startNieuwSpel() {
        Scanner scanner = new Scanner(System.in);

        String taal = "";
        int action = 0;
        boolean vlag = true;
        do {
            try {
                System.out.printf("Choose language: English(1)/Dutch(2)/French(3): ");
                action = scanner.nextInt();
                switch (action) {
                    case 1:
                        taal = "";
                        break;
                    case 2:
                        taal = "nl";
                        break;
                    case 3:
                        taal = "fr";
                        break;
                    default:
                        throw new IllegalArgumentException("Choose for English(1)/Dutch(2)/French(3). Try again.");
                }
                vlag = false;
            } catch (IllegalArgumentException il) {
                System.err.printf("%nException: %s%n", il);
            } catch (InputMismatchException i) {
                System.err.printf("%nException: %s: %s%n", i, "The input must consist of exclusively integers. Try again.");
                scanner.nextLine();
            } catch (Exception exception) {
                System.err.printf("%nException: %s%n", exception);
            }

        } while (vlag);

        //taal instellen
        setTaal(taal);
        ResourceBundle rb = ResourceBundle.getBundle("talen/Language", this.taal);

        vlag = true;
        do {
            try {
                System.out.printf(rb.getString("newLoad"));
                action = scanner.nextInt();
                switch (action) {
                    case 1:
                        break;
                    case 2:
                        if (dc.getBeschikbareSpelnamen().isEmpty()) {
                            throw new IllegalArgumentException("Er zijn geen opgeslagen spellen gevonden.");
                        }
                        break;
                    default:
                        throw new IllegalArgumentException(rb.getString("newLoadError"));
                }
                vlag = false;
            } catch (IllegalArgumentException il) {
                System.err.printf("%nException: %s%n", il);
            } catch (InputMismatchException i) {
                System.err.printf("%nException: %s: %s%n", i, rb.getString("integerError"));
                scanner.nextLine();
            } catch (Exception exception) {
                System.err.printf("%nException: %s%n", exception);
            }
        } while (vlag);

        int spelers = 0;
        vlag = true;
        switch (action) {
            case 1:
                do {
                    try {
                        System.out.printf(rb.getString("aantalSpelers"));
                        spelers = scanner.nextInt();
                        dc.vulAantalSpelersIn(spelers);
                        vlag = false;
                    } catch (IllegalArgumentException il) {
                        System.err.printf("%nException: %s%n", il);
                    } catch (InputMismatchException i) {
                        System.err.printf("%nException: %s: %s%n", i, rb.getString("integerError"));
                        scanner.nextLine();
                    } catch (Exception exception) {
                        System.err.printf("%nException: %s%n", exception);
                    }
                } while (vlag);

                for (int i = 0; i != spelers; i++) {
                    vlag = true;
                    do {
                        try {
                            System.out.printf("%s %d: ", rb.getString("naamVragen"), i + 1);
                            String naam = scanner.next();
                            System.out.printf(rb.getString("geslachtVragen"));
                            char geslacht = scanner.next().toUpperCase().charAt(0);
                            dc.vulGegevensIn(naam, geslacht);
                            vlag = false;
                        } catch (IllegalArgumentException il) {
                            System.err.printf("%nException: %s%n", il);
                        } catch (Exception exception) {
                            System.err.printf("%nException: %s%n", exception);
                        }
                    } while (vlag);
                }
                System.out.println("-------------------------------------------------------------------------------------------------------------------------");

                dc.bepaalVolgordeBeurten();

                //overzicht spelers
                System.out.println(rb.getString("nieuwSpelOverzicht"));
                for (List<String> speler : dc.geefOverzichtSpelers()) {
                    System.out.printf("%s %s | ", rb.getString("naam"), speler.get(0));
                    System.out.printf("%s %s | ", rb.getString("niveau"), speler.get(1));
                    System.out.printf("%s %s | ", rb.getString("gevechtssterkte"), speler.get(2));
                    System.out.printf("%s %s | ", rb.getString("ontsnappingsbonus"), speler.get(3));
                    System.out.printf("%s %s | ", rb.getString("goud"), speler.get(4));
                    System.out.printf("%s %s | ", rb.getString("status"), speler.get(5));
                    System.out.printf("%s %s | ", rb.getString("ras"), speler.get(6));
                    System.out.printf("%s %s%n", rb.getString("geslacht"), speler.get(7));
                }
                System.out.println("-------------------------------------------------------------------------------------------------------------------------");

                break;
            case 2:
                ladenSpel();

                //overzicht spelers
                System.out.println(rb.getString("geladenSpelOverzicht"));
                for (List<String> speler : dc.geefOverzichtSpelers()) {
                    System.out.printf("%s %s | ", rb.getString("naam"), speler.get(0));
                    System.out.printf("%s %s | ", rb.getString("niveau"), speler.get(1));
                    System.out.printf("%s %s | ", rb.getString("gevechtssterkte"), speler.get(2));
                    System.out.printf("%s %s | ", rb.getString("ontsnappingsbonus"), speler.get(3));
                    System.out.printf("%s %s | ", rb.getString("goud"), speler.get(4));
                    System.out.printf("%s %s | ", rb.getString("status"), speler.get(5));
                    System.out.printf("%s %s | ", rb.getString("ras"), speler.get(6));
                    System.out.printf("%s %s%n", rb.getString("geslacht"), speler.get(7));
                }
                System.out.println("-------------------------------------------------------------------------------------------------------------------------");
                break;
        }
    }

    /**
     * use case 2 het algemene spelverloop
     */
    public void speelSpel() {
        Scanner sc = new Scanner(System.in);
        ResourceBundle rb = ResourceBundle.getBundle("talen/Language", this.taal);

        //moet alle spelers overlopen en zolang er geen winnaar is
        while (dc.isWinnaar() == false) {
            System.out.printf("%s%s%n", dc.geefNaamSpelerAanBeurt(), rb.getString("spelerAanDeBeurt"));

            int action = 0;
            boolean vlag = true;
            do {
                try {
                    System.out.printf(rb.getString("speelSaveStop"));
                    action = sc.nextInt();
                    switch (action) {
                        case 1:
                            speelBeurt();
                            if (dc.isWinnaar() == true) {
                                System.out.printf("%s %s.%n", rb.getString("winnaar"), dc.geefWinnaar());
                            } else {
                                dc.resetStatussenVanSpelers();

                                //overzicht kaarten in hand
                                System.out.println(rb.getString("hand"));
                                for (List<String> kaart : dc.geefOverzichtKaartenInHand(dc.geefNaamSpelerAanBeurt())) {
                                    System.out.printf("%s | ", kaart.get(0));
                                    System.out.printf("%s %s | ", rb.getString("kaartID"), kaart.get(1));
                                    System.out.printf("%s %s | ", rb.getString("naam"), kaart.get(2));
                                    switch (kaart.get(0)) {
                                        case "Equipment":
                                            System.out.printf("%s %s | ", rb.getString("waarde"), kaart.get(4));
                                            System.out.printf("%s %s | ", rb.getString("soort"), kaart.get(5));
                                            break;
                                        case "Monster":
                                            System.out.printf("%s %s | ", rb.getString("niveau"), kaart.get(4));
                                            System.out.printf("%s %s | ", rb.getString("slechteDing"), kaart.get(5));
                                            System.out.printf("%s %s | ", rb.getString("beloning"), kaart.get(6));
                                            break;
                                        case "TreasureConsumeable":
                                            System.out.printf("%s %s | ", rb.getString("waarde"), kaart.get(4));
                                            break;
                                    }
                                    System.out.printf("%s %s%n", rb.getString("informatie"), kaart.get(3));
                                }

                                //overzicht afgelegde kaarten
                                System.out.println(rb.getString("afgelegd"));
                                for (List<String> kaart : dc.geefOverzichtAfgelegdeKaarten(dc.geefNaamSpelerAanBeurt())) {
                                    System.out.printf("%s | ", kaart.get(0));
                                    System.out.printf("%s %s | ", rb.getString("kaartID"), kaart.get(1));
                                    System.out.printf("%s %s | ", rb.getString("naam"), kaart.get(2));
                                    switch (kaart.get(0)) {
                                        case "Equipment":
                                            System.out.printf("%s %s | ", rb.getString("waarde"), kaart.get(4));
                                            System.out.printf("%s %s | ", rb.getString("soort"), kaart.get(5));
                                            break;
                                        case "Monster":
                                            System.out.printf("%s %s | ", rb.getString("niveau"), kaart.get(4));
                                            System.out.printf("%s %s | ", rb.getString("slechteDing"), kaart.get(5));
                                            System.out.printf("%s %s | ", rb.getString("beloning"), kaart.get(6));
                                            break;
                                        case "TreasureConsumeable":
                                            System.out.printf("%s %s | ", rb.getString("waarde"), kaart.get(4));
                                            break;
                                    }
                                    System.out.printf("%s %s%n", rb.getString("informatie"), kaart.get(3));
                                }

                                beheerKaarten();
                                while (dc.controleerMaxKaartenInHand() == false) {
                                    System.out.println(rb.getString("maxKaarten"));
                                    beheerKaarten();
                                }
                                dc.bepaalVolgendeSpeler();
                            }
                            break;
                        case 2:
                            opslaanSpel();
                            System.out.println(rb.getString("opgeslagen"));
                            break;
                        case 3:

                            //overzicht spelers
                            for (List<String> speler : dc.geefOverzichtSpelers()) {
                                System.out.printf("%s %s | ", rb.getString("naam"), speler.get(0));
                                System.out.printf("%s %s | ", rb.getString("niveau"), speler.get(1));
                                System.out.printf("%s %s | ", rb.getString("gevechtssterkte"), speler.get(2));
                                System.out.printf("%s %s | ", rb.getString("ontsnappingsbonus"), speler.get(3));
                                System.out.printf("%s %s | ", rb.getString("goud"), speler.get(4));
                                System.out.printf("%s %s | ", rb.getString("status"), speler.get(5));
                                System.out.printf("%s %s | ", rb.getString("ras"), speler.get(6));
                                System.out.printf("%s %s%n", rb.getString("geslacht"), speler.get(7));
                            }
                            System.out.println("-------------------------------------------------------------------------------------------------------------------------");

                            dc.stopSpel();
                            break;
                        default:
                            throw new IllegalArgumentException(rb.getString("speelSaveStopError"));
                    }
                    vlag = false;
                } catch (IllegalArgumentException il) {
                    System.err.printf("Exception: %s%n", il);
                } catch (InputMismatchException i) {
                    System.err.printf("%nException: %s: %s%n", i, rb.getString("integerError"));
                    sc.nextLine();
                } catch (Exception exception) {
                    System.err.printf("Exception: %s%n", exception);
                }
            } while (vlag);
        }
    }

    /**
     * use case 3 verloop van de beurt van een speler
     */
    public void speelBeurt() {
        ResourceBundle rb = ResourceBundle.getBundle("talen/Language", this.taal);

        System.out.println("-------------------------------------------------------------------------------------------------------------------------");

        //per speler een overzicht van zijn kaarten die zichtbaar in het spel zijn
        System.out.println(rb.getString("zichtbareKaarten"));
        for (List<String> speler : dc.geefOverzichtSpelers()) {
            System.out.printf("%s:%n", speler.get(0));
            for (List<String> kaart : dc.geefOverzichtAfgelegdeKaarten(speler.get(0))) {
                System.out.printf("%s | ", kaart.get(0));
                System.out.printf("%s %s | ", rb.getString("kaartID"), kaart.get(1));
                System.out.printf("%s %s | ", rb.getString("naam"), kaart.get(2));
                switch (kaart.get(0)) {
                    case "Equipment":
                        System.out.printf("%s %s | ", rb.getString("waarde"), kaart.get(4));
                        System.out.printf("%s %s | ", rb.getString("soort"), kaart.get(5));
                        break;
                    case "Monster":
                        System.out.printf("%s %s | ", rb.getString("niveau"), kaart.get(4));
                        System.out.printf("%s %s | ", rb.getString("slechteDing"), kaart.get(5));
                        System.out.printf("%s %s | ", rb.getString("beloning"), kaart.get(6));
                        break;
                    case "TreasureConsumeable":
                        System.out.printf("%s %s | ", rb.getString("waarde"), kaart.get(4));
                        break;
                }
                System.out.printf("%s %s%n", rb.getString("informatie"), kaart.get(3));
            }
        }
        System.out.println("-------------------------------------------------------------------------------------------------------------------------");

        //huidige niveau, naam en geslacht  
        System.out.println(rb.getString("spelerOverzicht"));
        for (List<String> speler : dc.geefOverzichtSpelers()) {
            System.out.printf("%s %s | ", rb.getString("naam"), speler.get(0));
            System.out.printf("%s %s | ", rb.getString("niveau"), speler.get(1));
            System.out.printf("%s %s | ", rb.getString("gevechtssterkte"), speler.get(2));
            System.out.printf("%s %s | ", rb.getString("ontsnappingsbonus"), speler.get(3));
            System.out.printf("%s %s | ", rb.getString("goud"), speler.get(4));
            System.out.printf("%s %s | ", rb.getString("status"), speler.get(5));
            System.out.printf("%s %s | ", rb.getString("ras"), speler.get(6));
            System.out.printf("%s %s%n", rb.getString("geslacht"), speler.get(7));
        }
        System.out.println("-------------------------------------------------------------------------------------------------------------------------");

        //een kerkaart wordt getrokken
        dc.geefSpelerEenKerkerkaart(dc.geefNaamSpelerAanBeurt());
        int ID = dc.geefGetrokkenKaartID();

        //informatie van de getrokken kaart weergeven
        System.out.println(rb.getString("getrokkenKaart"));
        System.out.printf("%s | ", dc.geefKaart(ID, dc.geefNaamSpelerAanBeurt()).get(0));
        System.out.printf("%s %s | ", rb.getString("kaartID"), dc.geefKaart(ID, dc.geefNaamSpelerAanBeurt()).get(1));
        System.out.printf("%s %s | ", rb.getString("naam"), dc.geefKaart(ID, dc.geefNaamSpelerAanBeurt()).get(2));
        switch (dc.geefKaart(ID, dc.geefNaamSpelerAanBeurt()).get(0)) {
            case "Equipment":
                System.out.printf("%s %s | ", rb.getString("waarde"), dc.geefKaart(ID, dc.geefNaamSpelerAanBeurt()).get(4));
                System.out.printf("%s %s | ", rb.getString("soort"), dc.geefKaart(ID, dc.geefNaamSpelerAanBeurt()).get(5));
                break;
            case "Monster":
                System.out.printf("%s %s | ", rb.getString("niveau"), dc.geefKaart(ID, dc.geefNaamSpelerAanBeurt()).get(4));
                System.out.printf("%s %s | ", rb.getString("slechteDing"), dc.geefKaart(ID, dc.geefNaamSpelerAanBeurt()).get(5));
                System.out.printf("%s %s | ", rb.getString("beloning"), dc.geefKaart(ID, dc.geefNaamSpelerAanBeurt()).get(6));
                break;
            case "TreasureConsumeable":
                System.out.printf("%s %s | ", rb.getString("waarde"), dc.geefKaart(ID, dc.geefNaamSpelerAanBeurt()).get(4));
                break;
        }
        System.out.printf("%s %s%n", rb.getString("informatie"), dc.geefKaart(ID, dc.geefNaamSpelerAanBeurt()).get(3));
        System.out.println("-------------------------------------------------------------------------------------------------------------------------");

        //systeem detecteert monster kaart
        if (dc.geefKlasseNaamVanKaart(ID, dc.geefNaamSpelerAanBeurt()).equals("Monster")) {
            System.out.println(rb.getString("voorbereidingGevecht"));
            dc.startGevecht();
            dc.speelKaart(ID, dc.geefNaamSpelerAanBeurt());
            voorbereidenGevecht();
            vechtMonster();

            System.out.println("-------------------------------------------------------------------------------------------------------------------------");
            //Aanduiding speler die aan de beurt is
            System.out.printf("%s%s%n", dc.geefNaamSpelerAanBeurt(), rb.getString("spelerAanDeBeurt"));

            //per speler een overzicht van zijn kaarten die zichtbaar in het spel zijn
            System.out.println(rb.getString("zichtbareKaarten"));
            for (List<String> speler : dc.geefOverzichtSpelers()) {
                System.out.printf("%s:%n", speler.get(0));
                for (List<String> kaart : dc.geefOverzichtAfgelegdeKaarten(speler.get(0))) {
                    System.out.printf("%s | ", kaart.get(0));
                    System.out.printf("%s %s | ", rb.getString("kaartID"), kaart.get(1));
                    System.out.printf("%s %s | ", rb.getString("naam"), kaart.get(2));
                    switch (kaart.get(0)) {
                        case "Equipment":
                            System.out.printf("%s %s | ", rb.getString("waarde"), kaart.get(4));
                            System.out.printf("%s %s | ", rb.getString("soort"), kaart.get(5));
                            break;
                        case "Monster":
                            System.out.printf("%s %s | ", rb.getString("niveau"), kaart.get(4));
                            System.out.printf("%s %s | ", rb.getString("slechteDing"), kaart.get(5));
                            System.out.printf("%s %s | ", rb.getString("beloning"), kaart.get(6));
                            break;
                        case "TreasureConsumeable":
                            System.out.printf("%s %s | ", rb.getString("waarde"), kaart.get(4));
                            break;
                    }
                    System.out.printf("%s %s%n", rb.getString("informatie"), kaart.get(3));
                }
            }
            System.out.println("-------------------------------------------------------------------------------------------------------------------------");

            //huidige niveau, naam en geslacht  
            System.out.println(rb.getString("spelerOverzicht"));
            for (List<String> speler : dc.geefOverzichtSpelers()) {
                System.out.printf("%s %s | ", rb.getString("naam"), speler.get(0));
                System.out.printf("%s %s | ", rb.getString("niveau"), speler.get(1));
                System.out.printf("%s %s | ", rb.getString("gevechtssterkte"), speler.get(2));
                System.out.printf("%s %s | ", rb.getString("ontsnappingsbonus"), speler.get(3));
                System.out.printf("%s %s | ", rb.getString("goud"), speler.get(4));
                System.out.printf("%s %s | ", rb.getString("status"), speler.get(5));
                System.out.printf("%s %s | ", rb.getString("ras"), speler.get(6));
                System.out.printf("%s %s%n", rb.getString("geslacht"), speler.get(7));
            }
            System.out.println("-------------------------------------------------------------------------------------------------------------------------");

        } //systeem detecteert curse kaart
        else if (dc.geefKlasseNaamVanKaart(ID, dc.geefNaamSpelerAanBeurt()).equals("Curse")) {
            dc.voerGespeeldeKaartEffectUit(ID, dc.geefNaamSpelerAanBeurt());
            System.out.printf("%s %s%n", dc.geefNaamSpelerAanBeurt(), rb.getString("vervloekt"));
            System.out.printf("%s %s%n", rb.getString("kaartEffect"), dc.geefKaartEffect(ID, dc.geefNaamSpelerAanBeurt()));
            dc.speelKaart(ID, dc.geefNaamSpelerAanBeurt());
        }

    }

    /**
     * use case 4 voorbereiding van een gevecht
     */
    public void voorbereidenGevecht() {
        ResourceBundle rb = ResourceBundle.getBundle("talen/Language", this.taal);
        Scanner scanner = new Scanner(System.in);

        //speler aan de beurt helpt zichzelf in het gevecht
        dc.spelerBeinvloedtGevecht("help", dc.geefNaamSpelerAanBeurt());

        //overzicht van het gevecht
        System.out.println(rb.getString("gevechtOverzicht"));

        //overzicht helpende spelers
        System.out.println(rb.getString("ally"));
        for (List<String> speler : dc.geefOverzichtAlly()) {
            System.out.printf("%s %s | ", rb.getString("naam"), speler.get(0));
            System.out.printf("%s %s | ", rb.getString("niveau"), speler.get(1));
            System.out.printf("%s %s | ", rb.getString("gevechtssterkte"), speler.get(2));
            System.out.printf("%s %s | ", rb.getString("ontsnappingsbonus"), speler.get(3));
            System.out.printf("%s %s | ", rb.getString("goud"), speler.get(4));
            System.out.printf("%s %s | ", rb.getString("status"), speler.get(5));
            System.out.printf("%s %s | ", rb.getString("ras"), speler.get(6));
            System.out.printf("%s %s%n", rb.getString("geslacht"), speler.get(7));
        }

        //overzicht helpende kaarten
        System.out.println(rb.getString("positieveKaarten"));
        for (List<String> kaart : dc.geefOverzichtSupport()) {
            System.out.printf("%s | ", kaart.get(0));
            System.out.printf("%s %s | ", rb.getString("kaartID"), kaart.get(1));
            System.out.printf("%s %s | ", rb.getString("naam"), kaart.get(2));
            switch (kaart.get(0)) {
                case "Equipment":
                    System.out.printf("%s %s | ", rb.getString("waarde"), kaart.get(4));
                    System.out.printf("%s %s | ", rb.getString("soort"), kaart.get(5));
                    break;
                case "Monster":
                    System.out.printf("%s %s | ", rb.getString("niveau"), kaart.get(4));
                    System.out.printf("%s %s | ", rb.getString("slechteDing"), kaart.get(5));
                    System.out.printf("%s %s | ", rb.getString("beloning"), kaart.get(6));
                    break;
                case "TreasureConsumeable":
                    System.out.printf("%s %s | ", rb.getString("waarde"), kaart.get(4));
                    break;
            }
            System.out.printf("%s %s%n", rb.getString("informatie"), kaart.get(3));
        }

        //totaal niveau spelers in gevecht
        System.out.printf("%s %d.%n", rb.getString("totaalNiveau"), dc.geefTotaleNiveauAlly());

        System.out.println("-------------------------------------------------------------------------------------------------------------------------");
        System.out.println("VS");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------");

        //overzicht monsters in het gevecht
        System.out.println(rb.getString("enemy"));
        for (List<String> kaart : dc.geefOverzichtEnemy()) {
            System.out.printf("%s | ", kaart.get(0));
            System.out.printf("%s %s | ", rb.getString("kaartID"), kaart.get(1));
            System.out.printf("%s %s | ", rb.getString("naam"), kaart.get(2));
            switch (kaart.get(0)) {
                case "Equipment":
                    System.out.printf("%s %s | ", rb.getString("waarde"), kaart.get(4));
                    System.out.printf("%s %s | ", rb.getString("soort"), kaart.get(5));
                    break;
                case "Monster":
                    System.out.printf("%s %s | ", rb.getString("niveau"), kaart.get(4));
                    System.out.printf("%s %s | ", rb.getString("slechteDing"), kaart.get(5));
                    System.out.printf("%s %s | ", rb.getString("beloning"), kaart.get(6));
                    break;
                case "TreasureConsumeable":
                    System.out.printf("%s %s | ", rb.getString("waarde"), kaart.get(4));
                    break;
            }
            System.out.printf("%s %s%n", rb.getString("informatie"), kaart.get(3));
        }

        //overzicht van kaarten die tegenwerken
        System.out.println(rb.getString("negatieveKaarten"));
        for (List<String> kaart : dc.geefOverzichtOppose()) {
            System.out.printf("%s | ", kaart.get(0));
            System.out.printf("%s %s | ", rb.getString("kaartID"), kaart.get(1));
            System.out.printf("%s %s | ", rb.getString("naam"), kaart.get(2));
            switch (kaart.get(0)) {
                case "Equipment":
                    System.out.printf("%s %s | ", rb.getString("waarde"), kaart.get(4));
                    System.out.printf("%s %s | ", rb.getString("soort"), kaart.get(5));
                    break;
                case "Monster":
                    System.out.printf("%s %s | ", rb.getString("niveau"), kaart.get(4));
                    System.out.printf("%s %s | ", rb.getString("slechteDing"), kaart.get(5));
                    System.out.printf("%s %s | ", rb.getString("beloning"), kaart.get(6));
                    break;
                case "TreasureConsumeable":
                    System.out.printf("%s %s | ", rb.getString("waarde"), kaart.get(4));
                    break;
            }
            System.out.printf("%s %s%n", rb.getString("informatie"), kaart.get(3));
        }

        //totaal niveau monsters in gevecht
        System.out.printf("%s %d.%n", rb.getString("totaalNiveau"), dc.geefTotaleNiveauEnemy());
        System.out.println("-------------------------------------------------------------------------------------------------------------------------");

        //overzicht van de kaarten in hand
        System.out.println(rb.getString("overzichtKaarten"));
        for (List<String> kaart : dc.geefOverzichtKaartenInHand(dc.geefNaamSpelerAanBeurt())) {
            System.out.printf("%s | ", kaart.get(0));
            System.out.printf("%s %s | ", rb.getString("kaartID"), kaart.get(1));
            System.out.printf("%s %s | ", rb.getString("naam"), kaart.get(2));
            switch (kaart.get(0)) {
                case "Equipment":
                    System.out.printf("%s %s | ", rb.getString("waarde"), kaart.get(4));
                    System.out.printf("%s %s | ", rb.getString("soort"), kaart.get(5));
                    break;
                case "Monster":
                    System.out.printf("%s %s | ", rb.getString("niveau"), kaart.get(4));
                    System.out.printf("%s %s | ", rb.getString("slechteDing"), kaart.get(5));
                    System.out.printf("%s %s | ", rb.getString("beloning"), kaart.get(6));
                    break;
                case "TreasureConsumeable":
                    System.out.printf("%s %s | ", rb.getString("waarde"), kaart.get(4));
                    break;
            }
            System.out.printf("%s %s%n", rb.getString("informatie"), kaart.get(3));

        }
        System.out.println("-------------------------------------------------------------------------------------------------------------------------");

        //speler aan de beurt wilt hulp of niet
        boolean vlag = true;
        int hulpAntwoord = 0;
        do {
            try {
                System.out.printf(rb.getString("hulpJaNee"));
                hulpAntwoord = scanner.nextInt();
                if (hulpAntwoord != 1 && hulpAntwoord != 2) {
                    throw new IllegalArgumentException(rb.getString("jaNeeError"));
                }
                vlag = false;
            } catch (IllegalArgumentException il) {
                System.err.printf("%nException: %s%n", il);
            } catch (InputMismatchException i) {
                System.err.printf("%nException: %s: %s%n", i, rb.getString("integerError"));
                scanner.nextLine();
            } catch (Exception exception) {
                System.err.printf("%nException: %s%n", exception);
            }
        } while (vlag);

        //speleraanbeurt speelt kaart
        speelKaart(dc.geefNaamSpelerAanBeurt());

        //tegenspelers kiezen om te helpen
        for (String naam : dc.geefNamenSpelersZonderSpelerAanBeurt()) {
            int tegenspelerAntwoord = 0;
            System.out.printf("%s %s%n", naam, rb.getString("magIetsDoen"));

            //overzicht van kaarten in hand
            System.out.println(rb.getString("overzichtKaarten"));
            for (List<String> kaart : dc.geefOverzichtKaartenInHand(naam)) {
                System.out.printf("%s | ", kaart.get(0));
                System.out.printf("%s %s | ", rb.getString("kaartID"), kaart.get(1));
                System.out.printf("%s %s | ", rb.getString("naam"), kaart.get(2));
                switch (kaart.get(0)) {
                    case "Equipment":
                        System.out.printf("%s %s | ", rb.getString("waarde"), kaart.get(4));
                        System.out.printf("%s %s | ", rb.getString("soort"), kaart.get(5));
                        break;
                    case "Monster":
                        System.out.printf("%s %s | ", rb.getString("niveau"), kaart.get(4));
                        System.out.printf("%s %s | ", rb.getString("slechteDing"), kaart.get(5));
                        System.out.printf("%s %s | ", rb.getString("beloning"), kaart.get(6));
                        break;
                    case "TreasureConsumeable":
                        System.out.printf("%s %s | ", rb.getString("waarde"), kaart.get(4));
                        break;
                }
                System.out.printf("%s %s%n", rb.getString("informatie"), kaart.get(3));
            }
            System.out.println("-------------------------------------------------------------------------------------------------------------------------");

            vlag = true;
            do {
                try {
                    if (hulpAntwoord == 1) {
                        System.out.printf(rb.getString("helpenTegenwerkenNietsDoen"));
                        tegenspelerAntwoord = scanner.nextInt();
                    } else {
                        System.out.println(rb.getString("tegenwerkenNietsDoen"));
                        tegenspelerAntwoord = scanner.nextInt();
                    }
                    if (tegenspelerAntwoord != 1 && tegenspelerAntwoord != 2 && tegenspelerAntwoord != 3) {
                        throw new IllegalArgumentException(rb.getString("helpenTegenwerkenNietsDoenError"));
                    } else if (hulpAntwoord == 2 && tegenspelerAntwoord == 1) {
                        throw new IllegalArgumentException(rb.getString("tegenwerkenNietsDoenError"));
                    }
                    vlag = false;
                } catch (IllegalArgumentException il) {
                    System.err.printf("%nException: %s%n", il);
                } catch (InputMismatchException i) {
                    System.err.printf("%nException: %s: %s%n", i, rb.getString("integerError"));
                    scanner.nextLine();
                } catch (Exception exception) {
                    System.err.printf("%nException: %s%n", exception);
                }
            } while (vlag);
            switch (tegenspelerAntwoord) {
                case 1:
                    dc.spelerBeinvloedtGevecht("help", naam);
                    break;
                case 2:
                    dc.spelerBeinvloedtGevecht("counter", naam);
                    break;
                case 3:
                    dc.spelerBeinvloedtGevecht("do nothing", naam);
                    break;
            }
            if (tegenspelerAntwoord == 1 | tegenspelerAntwoord == 2) {

                //tegenspeler speelt kaart
                speelKaart(naam);
            }
        }

        //speleraanbeurt speelt als laatste nog een kaart
        System.out.printf("%s:%n", dc.geefNaamSpelerAanBeurt());
        speelKaart(dc.geefNaamSpelerAanBeurt());
        System.out.println(rb.getString("eindeVoorbereiding"));

    }

    /**
     * use case 5 een bepaalde speler wilt een kaart spelen
     *
     * @param naam
     */
    public void speelKaart(String naam) {
        ResourceBundle rb = ResourceBundle.getBundle("talen/Language", this.taal);
        Scanner scanner = new Scanner(System.in);

        boolean vlag = true;
        int kaartSpelenAntwoord = 0;
        int ID = 0;
        String vervloekteSpeler = "";
        do {
            vlag = true;
            do {
                try {
                    System.out.printf(rb.getString("kaartSpelenJaNee"));
                    kaartSpelenAntwoord = scanner.nextInt();
                    switch (kaartSpelenAntwoord) {
                        case 1:

                            //overzicht kaarten in hand
                            System.out.println(rb.getString("overzichtKaarten"));
                            for (List<String> kaart : dc.geefOverzichtKaartenInHand(naam)) {
                                System.out.printf("%s | ", kaart.get(0));
                                System.out.printf("%s %s | ", rb.getString("kaartID"), kaart.get(1));
                                System.out.printf("%s %s | ", rb.getString("naam"), kaart.get(2));
                                switch (kaart.get(0)) {
                                    case "Equipment":
                                        System.out.printf("%s %s | ", rb.getString("waarde"), kaart.get(4));
                                        System.out.printf("%s %s | ", rb.getString("soort"), kaart.get(5));
                                        break;
                                    case "Monster":
                                        System.out.printf("%s %s | ", rb.getString("niveau"), kaart.get(4));
                                        System.out.printf("%s %s | ", rb.getString("slechteDing"), kaart.get(5));
                                        System.out.printf("%s %s | ", rb.getString("beloning"), kaart.get(6));
                                        break;
                                    case "TreasureConsumeable":
                                        System.out.printf("%s %s | ", rb.getString("waarde"), kaart.get(4));
                                        break;
                                }
                                System.out.printf("%s %s%n", rb.getString("informatie"), kaart.get(3));
                            }
                            System.out.println("-------------------------------------------------------------------------------------------------------------------------");

                            System.out.printf(rb.getString("kiesKaart"));
                            ID = scanner.nextInt();
                            scanner.nextLine();

                            //controleer ofdat kaart gespeeld mag worden
                            dc.controleerGespeeldeKaart(ID, naam);

                            if (dc.geefKlasseNaamVanKaart(ID, naam).equals("Curse")) {

                                //overzicht van helpende spelers
                                System.out.println(rb.getString("vervloekOverzicht"));
                                for (List<String> speler : dc.geefOverzichtAlly()) {
                                    System.out.printf("%s %s | ", rb.getString("naam"), speler.get(0));
                                    System.out.printf("%s %s | ", rb.getString("niveau"), speler.get(1));
                                    System.out.printf("%s %s | ", rb.getString("gevechtssterkte"), speler.get(2));
                                    System.out.printf("%s %s | ", rb.getString("ontsnappingsbonus"), speler.get(3));
                                    System.out.printf("%s %s | ", rb.getString("goud"), speler.get(4));
                                    System.out.printf("%s %s | ", rb.getString("status"), speler.get(5));
                                    System.out.printf("%s %s | ", rb.getString("ras"), speler.get(6));
                                    System.out.printf("%s %s%n", rb.getString("geslacht"), speler.get(7));
                                }
                                System.out.println("-------------------------------------------------------------------------------------------------------------------------");

                                System.out.printf(rb.getString("naamVervloek"));
                                vervloekteSpeler = scanner.nextLine();
                                dc.verplaatsKaartVanSpelerNaarSpeler(ID, naam, vervloekteSpeler);
                                dc.voerGespeeldeKaartEffectUit(ID, vervloekteSpeler);
                                System.out.printf("%s %s%n", vervloekteSpeler, rb.getString("vervloekt"));
                                System.out.printf("%s %s%n", rb.getString("kaartEffect"), dc.geefKaartEffect(ID, vervloekteSpeler));
                                dc.speelKaart(ID, vervloekteSpeler);
                            } else {
                                dc.voerGespeeldeKaartEffectUit(ID, naam);
                                dc.speelKaart(ID, naam);
                            }
                            break;
                        case 2:
                            break;
                        default:
                            throw new IllegalArgumentException(rb.getString("jaNeeError"));
                    }

                    //overzicht van het gevecht
                    System.out.println(rb.getString("gevechtOverzicht"));

                    //overzicht helpende spelers
                    System.out.println(rb.getString("ally"));
                    for (List<String> speler : dc.geefOverzichtAlly()) {
                        System.out.printf("%s %s | ", rb.getString("naam"), speler.get(0));
                        System.out.printf("%s %s | ", rb.getString("niveau"), speler.get(1));
                        System.out.printf("%s %s | ", rb.getString("gevechtssterkte"), speler.get(2));
                        System.out.printf("%s %s | ", rb.getString("ontsnappingsbonus"), speler.get(3));
                        System.out.printf("%s %s | ", rb.getString("goud"), speler.get(4));
                        System.out.printf("%s %s | ", rb.getString("status"), speler.get(5));
                        System.out.printf("%s %s | ", rb.getString("ras"), speler.get(6));
                        System.out.printf("%s %s%n", rb.getString("geslacht"), speler.get(7));
                    }

                    //overzicht helpende kaarten
                    System.out.println(rb.getString("positieveKaarten"));
                    for (List<String> kaart : dc.geefOverzichtSupport()) {
                        System.out.printf("%s | ", kaart.get(0));
                        System.out.printf("%s %s | ", rb.getString("kaartID"), kaart.get(1));
                        System.out.printf("%s %s | ", rb.getString("naam"), kaart.get(2));
                        switch (kaart.get(0)) {
                            case "Equipment":
                                System.out.printf("%s %s | ", rb.getString("waarde"), kaart.get(4));
                                System.out.printf("%s %s | ", rb.getString("soort"), kaart.get(5));
                                break;
                            case "Monster":
                                System.out.printf("%s %s | ", rb.getString("niveau"), kaart.get(4));
                                System.out.printf("%s %s | ", rb.getString("slechteDing"), kaart.get(5));
                                System.out.printf("%s %s | ", rb.getString("beloning"), kaart.get(6));
                                break;
                            case "TreasureConsumeable":
                                System.out.printf("%s %s | ", rb.getString("waarde"), kaart.get(4));
                                break;
                        }
                        System.out.printf("%s %s%n", rb.getString("informatie"), kaart.get(3));
                    }

                    //totaal niveau spelers in gevecht
                    System.out.printf("%s %d.%n", rb.getString("totaalNiveau"), dc.geefTotaleNiveauAlly());

                    System.out.println("-------------------------------------------------------------------------------------------------------------------------");
                    System.out.println("VS");
                    System.out.println("-------------------------------------------------------------------------------------------------------------------------");

                    //overzicht monsters in het gevecht
                    System.out.println(rb.getString("enemy"));
                    for (List<String> kaart : dc.geefOverzichtEnemy()) {
                        System.out.printf("%s | ", kaart.get(0));
                        System.out.printf("%s %s | ", rb.getString("kaartID"), kaart.get(1));
                        System.out.printf("%s %s | ", rb.getString("naam"), kaart.get(2));
                        switch (kaart.get(0)) {
                            case "Equipment":
                                System.out.printf("%s %s | ", rb.getString("waarde"), kaart.get(4));
                                System.out.printf("%s %s | ", rb.getString("soort"), kaart.get(5));
                                break;
                            case "Monster":
                                System.out.printf("%s %s | ", rb.getString("niveau"), kaart.get(4));
                                System.out.printf("%s %s | ", rb.getString("slechteDing"), kaart.get(5));
                                System.out.printf("%s %s | ", rb.getString("beloning"), kaart.get(6));
                                break;
                            case "TreasureConsumeable":
                                System.out.printf("%s %s | ", rb.getString("waarde"), kaart.get(4));
                                break;
                        }
                        System.out.printf("%s %s%n", rb.getString("informatie"), kaart.get(3));
                    }

                    //overzicht van kaarten die tegenwerken
                    System.out.println(rb.getString("negatieveKaarten"));
                    for (List<String> kaart : dc.geefOverzichtOppose()) {
                        System.out.printf("%s | ", kaart.get(0));
                        System.out.printf("%s %s | ", rb.getString("kaartID"), kaart.get(1));
                        System.out.printf("%s %s | ", rb.getString("naam"), kaart.get(2));
                        switch (kaart.get(0)) {
                            case "Equipment":
                                System.out.printf("%s %s | ", rb.getString("waarde"), kaart.get(4));
                                System.out.printf("%s %s | ", rb.getString("soort"), kaart.get(5));
                                break;
                            case "Monster":
                                System.out.printf("%s %s | ", rb.getString("niveau"), kaart.get(4));
                                System.out.printf("%s %s | ", rb.getString("slechteDing"), kaart.get(5));
                                System.out.printf("%s %s | ", rb.getString("beloning"), kaart.get(6));
                                break;
                            case "TreasureConsumeable":
                                System.out.printf("%s %s | ", rb.getString("waarde"), kaart.get(4));
                                break;
                        }
                        System.out.printf("%s %s%n", rb.getString("informatie"), kaart.get(3));
                    }

                    //totaal niveau monsters in gevecht
                    System.out.printf("%s %d.%n", rb.getString("totaalNiveau"), dc.geefTotaleNiveauEnemy());
                    System.out.println("-------------------------------------------------------------------------------------------------------------------------");

                    //per speler een overzicht van zijn kaarten die zichtbaar in het spel zijn
                    System.out.println(rb.getString("zichtbareKaarten"));
                    for (List<String> speler : dc.geefOverzichtSpelers()) {
                        System.out.printf("%s:%n", speler.get(0));
                        for (List<String> kaart : dc.geefOverzichtAfgelegdeKaarten(speler.get(0))) {
                            System.out.printf("%s | ", kaart.get(0));
                            System.out.printf("%s %s | ", rb.getString("kaartID"), kaart.get(1));
                            System.out.printf("%s %s | ", rb.getString("naam"), kaart.get(2));
                            switch (kaart.get(0)) {
                                case "Equipment":
                                    System.out.printf("%s %s | ", rb.getString("waarde"), kaart.get(4));
                                    System.out.printf("%s %s | ", rb.getString("soort"), kaart.get(5));
                                    break;
                                case "Monster":
                                    System.out.printf("%s %s | ", rb.getString("niveau"), kaart.get(4));
                                    System.out.printf("%s %s | ", rb.getString("slechteDing"), kaart.get(5));
                                    System.out.printf("%s %s | ", rb.getString("beloning"), kaart.get(6));
                                    break;
                                case "TreasureConsumeable":
                                    System.out.printf("%s %s | ", rb.getString("waarde"), kaart.get(4));
                                    break;
                            }
                            System.out.printf("%s %s%n", rb.getString("informatie"), kaart.get(3));
                        }
                    }
                    System.out.println("-------------------------------------------------------------------------------------------------------------------------");

                    vlag = false;
                } catch (IllegalArgumentException il) {
                    System.err.printf("%nException: %s%n", il);
                } catch (InputMismatchException i) {
                    System.err.printf("%nException: %s: %s%n", i, rb.getString("integerError"));
                    scanner.nextLine();
                } catch (Exception exception) {
                    System.err.printf("%nException: %s%n", exception);
                }
            } while (vlag);

        } while (kaartSpelenAntwoord == 1);
    }

    /**
     * use case 6 gevecht met de monsters
     */
    public void vechtMonster() {
        ResourceBundle rb = ResourceBundle.getBundle("talen/Language", this.taal);

        //monster is verslagen
        if (dc.evalueerGevecht() == true) {
            System.out.println(rb.getString("verslagen"));
            System.out.println(rb.getString("levelOmhoog"));
            System.out.println(rb.getString("beloning"));
            dc.deelBeloningenUit();
        } //monster is niet verslagen
        else {
            System.out.println(rb.getString("nietVerslagen"));

            //helpende spelers proberen te ontsnappen
            System.out.println(rb.getString("ontsnappen"));
            for (String naam : dc.geefNamenVanHelpendeSpelers()) {
                dc.rolDobbelsteen(naam);
                System.out.printf("%s %s %d.%n", naam, rb.getString("worp"), dc.geefAantalOgenWorp());

                //speler is niet ontsnapt
                if (dc.geefAantalOgenWorp() < 5) {
                    System.out.printf("%s %s%n", naam, rb.getString("nietOntsnapt"));

                    //speler overleeft
                    if (dc.controleerSpelerOverleeftGevecht(naam)) {
                        System.out.printf("%d %s%n", dc.geefNegatieveEffectenVanMonsters(), rb.getString("levelVerloren"));
                    } //speler sterft
                    else {
                        System.out.printf("%s %s%n", naam, rb.getString("gestorven"));
                        System.out.println(rb.getString("gestorvenEffect"));
                        dc.spelerSterft(naam);
                    }
                } //speler is ontsnapt 
                else {
                    System.out.printf("%s %s%n", naam, rb.getString("ontsnapt"));
                }
            }
        }

    }

    /**
     * use case 7 de speler aan de beurt beheert zijn kaarten
     */
    public void beheerKaarten() {
        ResourceBundle rb = ResourceBundle.getBundle("talen/Language", this.taal);
        Scanner scanner = new Scanner(System.in);

        boolean vlag = true;
        int antwoord = 0;
        int ID = 0;
        do {
            vlag = true;
            do {
                try {
                    System.out.printf(rb.getString("spelenVerkopenWeggooien"));
                    antwoord = scanner.nextInt();
                    System.out.println("-------------------------------------------------------------------------------------------------------------------------");
                    switch (antwoord) {
                        case 1:
                            System.out.println(rb.getString("overzichtKaarten"));

                            //overzicht kaarten in hand
                            System.out.println(rb.getString("hand"));
                            for (List<String> kaart : dc.geefOverzichtKaartenInHand(dc.geefNaamSpelerAanBeurt())) {
                                System.out.printf("%s | ", kaart.get(0));
                                System.out.printf("%s %s | ", rb.getString("kaartID"), kaart.get(1));
                                System.out.printf("%s %s | ", rb.getString("naam"), kaart.get(2));
                                switch (kaart.get(0)) {
                                    case "Equipment":
                                        System.out.printf("%s %s | ", rb.getString("waarde"), kaart.get(4));
                                        System.out.printf("%s %s | ", rb.getString("soort"), kaart.get(5));
                                        break;
                                    case "Monster":
                                        System.out.printf("%s %s | ", rb.getString("niveau"), kaart.get(4));
                                        System.out.printf("%s %s | ", rb.getString("slechteDing"), kaart.get(5));
                                        System.out.printf("%s %s | ", rb.getString("beloning"), kaart.get(6));
                                        break;
                                    case "TreasureConsumeable":
                                        System.out.printf("%s %s | ", rb.getString("waarde"), kaart.get(4));
                                        break;
                                }
                                System.out.printf("%s %s%n", rb.getString("informatie"), kaart.get(3));
                            }
                            System.out.println("-------------------------------------------------------------------------------------------------------------------------");

                            System.out.printf(rb.getString("kiesKaart"));
                            ID = scanner.nextInt();
                            dc.controleerGespeeldeKaart(ID, dc.geefNaamSpelerAanBeurt());
                            dc.voerGespeeldeKaartEffectUit(ID, dc.geefNaamSpelerAanBeurt());
                            dc.speelKaart(ID, dc.geefNaamSpelerAanBeurt());
                            break;
                        case 2:
                            System.out.println(rb.getString("overzichtKaarten"));

                            //overzicht kaarten in hand
                            System.out.println(rb.getString("hand"));
                            for (List<String> kaart : dc.geefOverzichtKaartenInHand(dc.geefNaamSpelerAanBeurt())) {
                                System.out.printf("%s | ", kaart.get(0));
                                System.out.printf("%s %s | ", rb.getString("kaartID"), kaart.get(1));
                                System.out.printf("%s %s | ", rb.getString("naam"), kaart.get(2));
                                switch (kaart.get(0)) {
                                    case "Equipment":
                                        System.out.printf("%s %s | ", rb.getString("waarde"), kaart.get(4));
                                        System.out.printf("%s %s | ", rb.getString("soort"), kaart.get(5));
                                        break;
                                    case "Monster":
                                        System.out.printf("%s %s | ", rb.getString("niveau"), kaart.get(4));
                                        System.out.printf("%s %s | ", rb.getString("slechteDing"), kaart.get(5));
                                        System.out.printf("%s %s | ", rb.getString("beloning"), kaart.get(6));
                                        break;
                                    case "TreasureConsumeable":
                                        System.out.printf("%s %s | ", rb.getString("waarde"), kaart.get(4));
                                        break;
                                }
                                System.out.printf("%s %s%n", rb.getString("informatie"), kaart.get(3));
                            }

                            //overzicht afgelegde kaarten
                            System.out.println(rb.getString("afgelegd"));
                            for (List<String> kaart : dc.geefOverzichtAfgelegdeKaarten(dc.geefNaamSpelerAanBeurt())) {
                                System.out.printf("%s | ", kaart.get(0));
                                System.out.printf("%s %s | ", rb.getString("kaartID"), kaart.get(1));
                                System.out.printf("%s %s | ", rb.getString("naam"), kaart.get(2));
                                switch (kaart.get(0)) {
                                    case "Equipment":
                                        System.out.printf("%s %s | ", rb.getString("waarde"), kaart.get(4));
                                        System.out.printf("%s %s | ", rb.getString("soort"), kaart.get(5));
                                        break;
                                    case "Monster":
                                        System.out.printf("%s %s | ", rb.getString("niveau"), kaart.get(4));
                                        System.out.printf("%s %s | ", rb.getString("slechteDing"), kaart.get(5));
                                        System.out.printf("%s %s | ", rb.getString("beloning"), kaart.get(6));
                                        break;
                                    case "TreasureConsumeable":
                                        System.out.printf("%s %s | ", rb.getString("waarde"), kaart.get(4));
                                        break;
                                }
                                System.out.printf("%s %s%n", rb.getString("informatie"), kaart.get(3));
                            }
                            System.out.println("-------------------------------------------------------------------------------------------------------------------------");

                            System.out.printf(rb.getString("kiesKaart"));
                            ID = scanner.nextInt();
                            scanner.nextLine();
                            dc.verkoopKaart(ID);
                            break;
                        case 3:
                            System.out.println(rb.getString("overzichtKaarten"));

                            //overzicht kaarten in hand
                            System.out.println(rb.getString("hand"));
                            for (List<String> kaart : dc.geefOverzichtKaartenInHand(dc.geefNaamSpelerAanBeurt())) {
                                System.out.printf("%s | ", kaart.get(0));
                                System.out.printf("%s %s | ", rb.getString("kaartID"), kaart.get(1));
                                System.out.printf("%s %s | ", rb.getString("naam"), kaart.get(2));
                                switch (kaart.get(0)) {
                                    case "Equipment":
                                        System.out.printf("%s %s | ", rb.getString("waarde"), kaart.get(4));
                                        System.out.printf("%s %s | ", rb.getString("soort"), kaart.get(5));
                                        break;
                                    case "Monster":
                                        System.out.printf("%s %s | ", rb.getString("niveau"), kaart.get(4));
                                        System.out.printf("%s %s | ", rb.getString("slechteDing"), kaart.get(5));
                                        System.out.printf("%s %s | ", rb.getString("beloning"), kaart.get(6));
                                        break;
                                    case "TreasureConsumeable":
                                        System.out.printf("%s %s | ", rb.getString("waarde"), kaart.get(4));
                                        break;
                                }
                                System.out.printf("%s %s%n", rb.getString("informatie"), kaart.get(3));
                            }

                            //overzicht afgelegde kaarten
                            System.out.println(rb.getString("afgelegd"));
                            for (List<String> kaart : dc.geefOverzichtAfgelegdeKaarten(dc.geefNaamSpelerAanBeurt())) {
                                System.out.printf("%s | ", kaart.get(0));
                                System.out.printf("%s %s | ", rb.getString("kaartID"), kaart.get(1));
                                System.out.printf("%s %s | ", rb.getString("naam"), kaart.get(2));
                                switch (kaart.get(0)) {
                                    case "Equipment":
                                        System.out.printf("%s %s | ", rb.getString("waarde"), kaart.get(4));
                                        System.out.printf("%s %s | ", rb.getString("soort"), kaart.get(5));
                                        break;
                                    case "Monster":
                                        System.out.printf("%s %s | ", rb.getString("niveau"), kaart.get(4));
                                        System.out.printf("%s %s | ", rb.getString("slechteDing"), kaart.get(5));
                                        System.out.printf("%s %s | ", rb.getString("beloning"), kaart.get(6));
                                        break;
                                    case "TreasureConsumeable":
                                        System.out.printf("%s %s | ", rb.getString("waarde"), kaart.get(4));
                                        break;
                                }
                                System.out.printf("%s %s%n", rb.getString("informatie"), kaart.get(3));
                            }
                            System.out.println("-------------------------------------------------------------------------------------------------------------------------");

                            System.out.printf(rb.getString("kiesKaart"));
                            ID = scanner.nextInt();
                            scanner.nextLine();
                            dc.gooiKaartWeg(ID);
                            break;
                        case 4:
                            break;
                        default:
                            throw new IllegalArgumentException(rb.getString("spelenVerkopenWeggooienError"));
                    }
                    if (antwoord != 4) {
                        System.out.println(rb.getString("overzichtKaarten"));

                        //overzicht kaarten in hand
                        System.out.println(rb.getString("hand"));
                        for (List<String> kaart : dc.geefOverzichtKaartenInHand(dc.geefNaamSpelerAanBeurt())) {
                            System.out.printf("%s | ", kaart.get(0));
                            System.out.printf("%s %s | ", rb.getString("kaartID"), kaart.get(1));
                            System.out.printf("%s %s | ", rb.getString("naam"), kaart.get(2));
                            switch (kaart.get(0)) {
                                case "Equipment":
                                    System.out.printf("%s %s | ", rb.getString("waarde"), kaart.get(4));
                                    System.out.printf("%s %s | ", rb.getString("soort"), kaart.get(5));
                                    break;
                                case "Monster":
                                    System.out.printf("%s %s | ", rb.getString("niveau"), kaart.get(4));
                                    System.out.printf("%s %s | ", rb.getString("slechteDing"), kaart.get(5));
                                    System.out.printf("%s %s | ", rb.getString("beloning"), kaart.get(6));
                                    break;
                                case "TreasureConsumeable":
                                    System.out.printf("%s %s | ", rb.getString("waarde"), kaart.get(4));
                                    break;
                            }
                            System.out.printf("%s %s%n", rb.getString("informatie"), kaart.get(3));
                        }

                        //overzicht afgelegde kaarten
                        System.out.println(rb.getString("afgelegd"));
                        for (List<String> kaart : dc.geefOverzichtAfgelegdeKaarten(dc.geefNaamSpelerAanBeurt())) {
                            System.out.printf("%s | ", kaart.get(0));
                            System.out.printf("%s %s | ", rb.getString("kaartID"), kaart.get(1));
                            System.out.printf("%s %s | ", rb.getString("naam"), kaart.get(2));
                            switch (kaart.get(0)) {
                                case "Equipment":
                                    System.out.printf("%s %s | ", rb.getString("waarde"), kaart.get(4));
                                    System.out.printf("%s %s | ", rb.getString("soort"), kaart.get(5));
                                    break;
                                case "Monster":
                                    System.out.printf("%s %s | ", rb.getString("niveau"), kaart.get(4));
                                    System.out.printf("%s %s | ", rb.getString("slechteDing"), kaart.get(5));
                                    System.out.printf("%s %s | ", rb.getString("beloning"), kaart.get(6));
                                    break;
                                case "TreasureConsumeable":
                                    System.out.printf("%s %s | ", rb.getString("waarde"), kaart.get(4));
                                    break;
                            }
                            System.out.printf("%s %s%n", rb.getString("informatie"), kaart.get(3));
                        }
                        System.out.println("-------------------------------------------------------------------------------------------------------------------------");

                        System.out.printf("%s%s%n", dc.geefNaamSpelerAanBeurt(), rb.getString("spelerAanDeBeurt"));
                    }
                    vlag = false;
                } catch (IllegalArgumentException il) {
                    System.err.printf("%nException: %s%n", il);
                } catch (InputMismatchException i) {
                    System.err.printf("%nException: %s: %s%n", i, rb.getString("integerError"));
                    scanner.nextLine();
                } catch (Exception exception) {
                    System.err.printf("%nException: %s%n", exception);
                }
            } while (vlag);

        } while (antwoord != 4);
    }

    /**
     * use case 8 spel opslagen
     */
    public void opslaanSpel() {
        ResourceBundle rb = ResourceBundle.getBundle("talen/Language", this.taal);
        Scanner scanner = new Scanner(System.in);
        String toegelatenTekens = "abcdefghijklmnopqrstuvwxyz1234567890";
        int numbers = 0;
        boolean vlag = true;
        do {
            try {
                System.out.printf(rb.getString("naamSpel"));
                String antwoord = scanner.next();
                if (antwoord.length() >= 6 && antwoord.length() <= 12) {
                    for (char letter : antwoord.toLowerCase().toCharArray()) {
                        if (toegelatenTekens.indexOf(letter) < 0) {
                            throw new IllegalArgumentException(rb.getString("naamSpelError"));
                        }
                        if (Character.isDigit(letter)) {
                            numbers++;
                        }
                    }
                    if (numbers >= 3) {
                        if (dc.checkIfNameExists(antwoord)) {
                            System.out.printf(rb.getString("overschrijven"));
                            int overschrijven = scanner.nextInt();
                            switch (overschrijven) {
                                case 1:
                                    dc.verwijderVorigeSpel(antwoord);
                                    dc.slaOp(antwoord);
                                    break;
                                case 2:
                                    break;
                                default:
                                    throw new IllegalArgumentException(rb.getString("jaNeeError"));
                            }
                        } else {
                            dc.slaOp(antwoord);
                        }

                        vlag = false;
                    } else {
                        throw new IllegalArgumentException(rb.getString("cijferError"));
                    }
                } else {
                    throw new IllegalArgumentException(rb.getString("lengteError"));
                }
            } catch (IllegalArgumentException il) {
                System.err.printf("%nException: %s%n", il);
            } catch (InputMismatchException i) {
                System.err.printf("%nException: %s: %s%n", i, rb.getString("integerError"));
                scanner.nextLine();
            } catch (Exception exception) {
                System.err.printf("%nException: %s%n", exception);
            }
        } while (vlag);
    }

    /**
     * use case 9 spel laden
     */
    public void ladenSpel() {
        Scanner scanner = new Scanner(System.in);
        ResourceBundle rb = ResourceBundle.getBundle("talen/Language", this.taal);

        for (String naam : dc.getBeschikbareSpelnamen()) {
            System.out.printf("%s %s | %s %d.%n", rb.getString("naam"), naam, rb.getString("aantalSpelersSpel"), dc.getAantalSpelers(naam));
        }

        boolean vlag = true;
        do {
            try {
                System.out.printf(rb.getString("kiesSpel"));
                String antwoord = scanner.next();
                dc.voorbereidenLaden(antwoord);
                vlag = false;
            } catch (IllegalArgumentException il) {
                System.err.printf("%nException: %s%n", il);
            } catch (InputMismatchException i) {
                System.err.printf("%nException: %s: %s%n", i, rb.getString("integerError"));
                scanner.nextLine();
            } catch (Exception exception) {
                System.err.printf("%nException: %s%n", exception);
            }
        } while (vlag);
    }

}
