package domein;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author stijn
 */
public class Monster extends Kerkerkaart {

    private final int level;
    private final String badStuff;
    private final int reward;

    /**
     *
     * @param kaartID
     * @param naam
     * @param info
     * @param level
     * @param badStuff
     * @param reward
     */
    public Monster(int kaartID, String naam, String info, int level, String badStuff, int reward) {
        super(kaartID, naam, info);
        this.level = level;
        this.badStuff = badStuff;
        this.reward = reward;
    }

    /**
     * geeft monster level
     *
     * @return level
     */
    public int getLevel() {
        return level;
    }

    /**
     * geeft bad stuff
     *
     * @return bad stuff
     */
    public String getBadStuff() {
        return badStuff;
    }

    /**
     * geeft beloning
     *
     * @return beloning
     */
    public int getReward() {
        return reward;
    }

    /**
     * geeft een lijst van informatie
     *
     * @return list
     */
    @Override
    public List<String> geefInformatie() {
        List<String> informatie = new ArrayList<>();
        informatie.add(this.getClass().getSimpleName());
        informatie.add(Integer.toString(getKaartID()));
        informatie.add(getNaam());
        informatie.add(getInfo());
        informatie.add(Integer.toString(level));
        informatie.add(badStuff);

        String r = "";
        if (reward == 1) {
            r = " Treasure";
        } else {
            r = " Treasures";
        }
        informatie.add(Integer.toString(reward) + r);

        return informatie;
    }

    /**
     * geeft het effect van de bad stuff
     *
     * @return effect bad stuff
     */
    public int geefBadStuff() {
        int integer = 0;
        String number = "0";
        for (char c : getBadStuff().toCharArray()) {
            if (Character.isDigit(c)) {
                number += c;
            }
        }
        integer = Integer.parseInt(number);
        return integer;
    }

    /**
     * selecteert het juiste effect voor een bepaalde speler
     *
     * @param speler
     * @return effect
     */
    public int selecteerEffect(Speler speler) {
        int effect = 0;
        String ras = "";
        String[] infoWoorden = getInfo().split("\\s");
        for (String woord : infoWoorden) {
            if (woord.contains("Human") || woord.contains("Elf") || woord.contains("Dwarf") || woord.contains("Halfling")) {
                ras = woord.toLowerCase();
            }
        }
        if (!ras.equals("") && speler.getRas().equals(ras)) {
            effect = geefEffect(getInfo());
        } else if (ras.equals("")) {
            effect = geefEffect(getInfo());
        }
        return effect;
    }

}
