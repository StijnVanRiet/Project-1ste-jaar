package domein;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author stijn
 */
public final class Speler {

    private String naam;
    private int niveau;
    private int combatStrength;
    private int runAwayBonus;
    private int gold;
    private String status;
    private SpelerRas ras;
    private SpelerGeslacht geslacht;
    private Stapel hand;
    private Stapel afgelegd;

    /**
     *
     * @param naam
     * @param geslacht
     */
    public Speler(String naam, char geslacht) {
        setNaam(naam);
        setNiveau(1);
        setCombatStrength(0);
        setRunAwayBonus(0);
        setGold(0);
        setStatus("do nothing");
        setRas("human");
        setGeslacht(geslacht);
        setHand(new ArrayList<>());
        setAfgelegd(new ArrayList<>());
    }

    /**
     *
     * @param naam
     * @param niveau
     * @param gold
     * @param geslacht
     */
    public Speler(String naam, int niveau, int gold, char geslacht) {
        setNaam(naam);
        setNiveau(niveau);
        setCombatStrength(niveau);
        //setCombatStrength(combatStrength);
        setRunAwayBonus(0);
        //setRunAwayBonus(runAwayBonus);
        setGold(gold);
        //setStatus(status);
        setStatus("do nothing");
        //setRas(ras);
        setRas("human");
        setGeslacht(geslacht);
        setHand(new ArrayList<>());
        setAfgelegd(new ArrayList<>());
    }

    private void setNaam(String naam) {
        String toegelatenTekens = "abcdefghijklmnopqrstuvwxyz_-";
        if (naam.length() >= 6 && naam.length() <= 12) {
            for (char letter : naam.toLowerCase().toCharArray()) {
                if (toegelatenTekens.indexOf(letter) < 0) {
                    throw new IllegalArgumentException("The name of a player can only consist of the following characters: a-z, A-Z, _ or -. Try again.");
                }
            }
            this.naam = naam;
        } else {
            throw new IllegalArgumentException("The name of a player is at least 6 characters long and at most 12 characters long. Try again.");
        }
    }

    /**
     * geeft naam
     *
     * @return naam
     */
    public String getNaam() {
        return naam;
    }

    private void setNiveau(int niveau) {
        this.niveau = niveau;
        if (this.niveau <= 0) {
            this.niveau = 1;
        }
    }

    /**
     * geeft level
     *
     * @return level
     */
    public int getNiveau() {
        return niveau;
    }

    private void setCombatStrength(int combatStrength) {
        this.combatStrength = combatStrength;
    }

    /**
     * geeft extra levels in een gevecht
     *
     * @return level
     */
    public int getCombatStrength() {
        return combatStrength;
    }

    private void setRunAwayBonus(int runAwayBonus) {
        this.runAwayBonus = runAwayBonus;
    }

    /**
     * geeft de bonus bij weglopen
     *
     * @return bonus
     */
    public int getRunAwayBonus() {
        return runAwayBonus;
    }

    private void setGold(int gold) {
        this.gold = gold;
    }

    /**
     * geeft gold
     *
     * @return gold
     */
    public int getGold() {
        return gold;
    }

    private void setStatus(String status) {
        this.status = status;
    }

    /**
     * geeft status
     *
     * @return status
     */
    public String getStatus() {
        return status;
    }

    private void setGeslacht(char geslacht) {
        switch (geslacht) {
            case 'M':
                this.geslacht = SpelerGeslacht.MALE;
                break;
            case 'F':
                this.geslacht = SpelerGeslacht.FEMALE;
                break;
            default:
                throw new IllegalArgumentException("Gender can only be male(M) or female(F). Try again.");
        }
    }

    /**
     * geeft geslacht
     *
     * @return geslacht
     */
    public char getGeslacht() {
        return geslacht.toString().charAt(0);
    }

    private void setRas(String ras) {
        ras = ras.toLowerCase();
        switch (ras) {
            case "human":
                this.ras = SpelerRas.HUMAN;
                break;
            case "elf":
                this.ras = SpelerRas.ELF;
                break;
            case "dwarf":
                this.ras = SpelerRas.DWARF;
                break;
            case "halfling":
                this.ras = SpelerRas.HALFLING;
                break;
        }
    }

    /**
     * geeft ras
     *
     * @return ras
     */
    public String getRas() {
        return ras.toString().toLowerCase();
    }

    private void setHand(List<Kaart> hand) {
        this.hand = new Stapel(hand);
    }

    /**
     * geeft hand
     *
     * @return hand
     */
    public Stapel getHand() {
        return hand;
    }

    private void setAfgelegd(List<Kaart> afgelegd) {
        this.afgelegd = new Stapel(afgelegd);
    }

    /**
     * geeft afgelegd
     *
     * @return afgelegd
     */
    public Stapel getAfgelegd() {
        return afgelegd;
    }

    /**
     * controleert ofdat een kaart gespeeld mag worden
     *
     * @param kaartID
     * @param spelerAanBeurtNaam
     */
    public void controleerGespeeldeKaart(int kaartID, String spelerAanBeurtNaam) {
        Kaart kaart = selecteerKaartOmTeSpelen(kaartID);

        //als speler aan de beurt is
        if (naam.equals(spelerAanBeurtNaam)) {
            if (status.equals("do nothing")) {
                if (!(kaart instanceof Equipment) && !(kaart instanceof Race) && !(kaart.getInfo().contains("Go Up"))) {
                    throw new IllegalArgumentException("You cannot play this card now. Try again.");
                }
            } else if (kaart instanceof DungeonConsumeable) {
                if (kaart.geefEffect(kaart.getInfo()) > 0) {
                    throw new IllegalArgumentException("You cannot play this card now. Try again.");
                }
            } else {
                if (!(kaart instanceof TreasureConsumeable) && !(kaart instanceof Equipment) && !(kaart instanceof Race)) {
                    throw new IllegalArgumentException("You cannot play this card now. Try again.");
                }
            }
        } //als speler niet aan de beurt is
        else {
            if (kaart instanceof Equipment || kaart instanceof Race) {
                throw new IllegalArgumentException("You cannot play this card now. Try again.");
            } else if (status.equals("help")) {
                if (kaart instanceof Monster || kaart instanceof Curse) {
                    throw new IllegalArgumentException("You can only play positive cards while helping. Try again.");
                } else if (kaart instanceof DungeonConsumeable && kaart.geefEffect(kaart.getInfo()) > 0) {
                    throw new IllegalArgumentException("You can only play positive cards while helping. Try again.");
                }
            } else if (status.equals("counter")) {
                if (kaart instanceof DungeonConsumeable && kaart.geefEffect(kaart.getInfo()) < 0) {
                    throw new IllegalArgumentException("You can only play negative cards while countering. Try again.");
                } else if (kaart instanceof TreasureConsumeable && kaart.getInfo().contains("Go Up A Level")) {
                    throw new IllegalArgumentException("You cannot play this card now. Try again.");
                }
            }
        }

    }

    /**
     * geeft een lijst van eigenschappen
     *
     * @return list
     */
    public List<String> geefEigenschappen() {
        List<String> eigenschappen = new ArrayList<>();
        eigenschappen.add(naam);
        eigenschappen.add(Integer.toString(niveau));
        eigenschappen.add(Integer.toString(combatStrength));
        eigenschappen.add(Integer.toString(runAwayBonus));
        eigenschappen.add(Integer.toString(gold));
        eigenschappen.add(status);
        eigenschappen.add(ras.toString());
        eigenschappen.add(geslacht.toString());
        return eigenschappen;
    }

    /**
     * geeft de kaart dat getrokken is
     *
     * @return kaart
     */
    public Kaart geefGetrokkenKaart() {
        int aantalKaartenInHand = hand.getKaarten().size();
        List<Kaart> kaarten = hand.getKaarten();
        return kaarten.get(aantalKaartenInHand - 1);
    }

    /**
     * geeft de ID van de kaart dat getrokken is
     *
     * @return kaartID
     */
    public int geefGetrokkenKaartID() {
        int aantalKaartenInHand = hand.getKaarten().size();
        List<Kaart> kaarten = hand.getKaarten();
        Kaart kaart = kaarten.get(aantalKaartenInHand - 1);
        return kaart.getKaartID();
    }

    /**
     * bepaalt ofdat er teveel kaarten in hand zijn
     *
     * @return true or false
     */
    public boolean controleerMaxKaartenInHand() {
        int aantalKaartenInHand = hand.getKaarten().size();
        return aantalKaartenInHand <= 5;
    }

    /**
     * verliest levels
     *
     * @param effect
     */
    public void verliesLevel(int effect) {
        setNiveau(niveau - effect);
    }

    /**
     * gaat een level omhoog
     */
    public void gaEenLevelOmhoog() {
        setNiveau(niveau + 1);
    }

    /**
     * voegt gevechtskracht toe
     *
     * @param combatStrength
     */
    public void voegCombatStrengthToe(int combatStrength) {
        setCombatStrength(this.combatStrength + combatStrength);
    }

    /**
     * voegt wegloop bonus toe
     *
     * @param runAwayBonus
     */
    public void voegRunAwayBonusToe(int runAwayBonus) {
        setRunAwayBonus(this.runAwayBonus + runAwayBonus);
    }

    /**
     * verandert de status
     *
     * @param status
     */
    public void veranderStatus(String status) {
        setStatus(status);
    }

    /**
     * verandert het ras
     *
     * @param ras
     */
    public void veranderRas(String ras) {
        setRas(ras);
    }

    /**
     * voegt gold toe
     *
     * @param value
     */
    public void voegGoldToe(int value) {
        if (getRas().equals("halfling")) {
            setGold(gold + 2 * value);
        } else {
            setGold(gold + value);
        }
        if (gold >= 1000 && niveau < 9) {
            gaEenLevelOmhoog();
            setGold(gold - 1000);
        }
    }

    /**
     * reset gevechtskracht en wegloop bonus
     */
    public void resetCombatEnRunAway() {
        setCombatStrength(0);
        setRunAwayBonus(0);
    }

    /**
     * laat speler sterven
     */
    public void sterf() {
        setNiveau(1);
        resetCombatEnRunAway();
        setGold(0);
        setStatus("recently deceased");
        setRas("human");
        hand.maakLeeg();
        afgelegd.maakLeeg();
    }

    /**
     * selecteert een kaart om te spelen
     *
     * @param kaartID
     * @return kaart
     */
    public Kaart selecteerKaartOmTeSpelen(int kaartID) {
        Kaart kaart = null;
        for (Kaart eenKaart : hand.getKaarten()) {
            if (eenKaart.getKaartID() == kaartID) {
                kaart = eenKaart;
            }
        }
        if (kaart == null) {
            throw new IllegalArgumentException("Card not found. Try again.");
        } else {
            return kaart;
        }
    }

    /**
     * selecteert een kaart om te beheren
     *
     * @param kaartID
     * @return kaart
     */
    public Kaart selecteerKaartOmTeBeheren(int kaartID) {
        Kaart kaart = null;
        List<Kaart> kaartenInBezit = new ArrayList<>();
        for (Kaart eenKaart : hand.getKaarten()) {
            kaartenInBezit.add(eenKaart);
        }
        for (Kaart eenKaart : afgelegd.getKaarten()) {
            kaartenInBezit.add(eenKaart);
        }
        for (Kaart eenKaart : kaartenInBezit) {
            if (eenKaart.getKaartID() == kaartID) {
                kaart = eenKaart;
            }
        }
        if (kaart == null) {
            throw new IllegalArgumentException("Card not found. Try again.");
        } else {
            return kaart;
        }
    }

    /**
     * voegt een kaart toe aan hand
     *
     * @param kaart
     */
    public void voegKaartToeAanHand(Kaart kaart) {
        hand.voegKaartToe(kaart);
    }
    
    /**
     * voegt een kaart toe aan afgelegde ruimte
     *
     * @param kaart
     */
    public void voegKaartToeAanAfgelegdeRuimte(Kaart kaart) {
        afgelegd.voegKaartToe(kaart);
    }

    /**
     * verwijdert kaart uit bezit
     *
     * @param kaart
     */
    public void verwijderKaartUitBezit(Kaart kaart) {
        if (hand.getKaarten().contains(kaart)) {
            hand.verwijderKaart(kaart);
        } else {
            afgelegd.verwijderKaart(kaart);
        }
    }

}
