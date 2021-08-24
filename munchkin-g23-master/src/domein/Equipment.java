package domein;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author stijn
 */
public class Equipment extends Schatkaart {

    private final char equipmentType;

    /**
     *
     * @param kaartID
     * @param naam
     * @param info
     * @param value
     * @param equipmentType
     */
    public Equipment(int kaartID, String naam, String info, int value, char equipmentType) {
        super(kaartID, naam, info, value);
        this.equipmentType = equipmentType;
    }

    /**
     * geeft type equipment
     *
     * @return type equipment
     */
    public char getEquipmentType() {
        return equipmentType;
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

        if (getValue() == 0) {
            informatie.add("No Value");
        } else {
            informatie.add(Integer.toString(getValue()) + " Gold Pieces");
        }

        switch (equipmentType) {
            case 'H':
                informatie.add("Headgear");
                break;
            case 'A':
                informatie.add("Armor");
                break;
            case 'F':
                informatie.add("Footgear");
                break;
            case 'W':
                informatie.add("Weapon");
                break;
        }

        return informatie;
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
        if (getInfo() != null) {
            String[] infoWoorden = getInfo().split("\\s");
            for (String woord : infoWoorden) {
                if (woord.contains("Human") || woord.contains("Elf") || woord.contains("Dwarf") || woord.contains("Halfling")) {
                    ras = woord.toLowerCase();
                }
            }
            if (getInfo().contains("Only")) {
                if (speler.getRas().equals(ras)) {
                    effect = geefEffect(getInfo());
                } else {
                    effect = 0;
                }
            } else {
                String[] zinnen = getInfo().split("\\.");
                for (String zin : zinnen) {
                    ras = "";
                    String[] zinWoorden = zin.split("\\s");
                    for (String woord : zinWoorden) {
                        if (woord.contains("Human") || woord.contains("Elf") || woord.contains("Dwarf") || woord.contains("Halfling")) {
                            ras = woord.toLowerCase();
                        }
                    }
                    if (!ras.equals("") && speler.getRas().equals(ras)) {
                        effect = geefEffect(zin);
                    } else if (ras.equals("")) {
                        effect = geefEffect(zin);
                    }
                }
            }
        }
        return effect;
    }

    /**
     * selecteert het juiste effect voor een bepaalde speler en verwijdert dat
     * effect van de speler
     *
     * @param speler
     */
    @Override
    public void verwijderEffect(Speler speler) {
        int effectVerwijderen = selecteerEffect(speler);
        if (getInfo().contains("Run Away")) {
            speler.voegRunAwayBonusToe(effectVerwijderen * -1);
        } else {
            speler.voegCombatStrengthToe(effectVerwijderen * -1);
        }
    }

    /**
     * selecteert het juiste effect voor een bepaalde speler en voer dat effect
     * uit op de speler
     *
     * @param speler
     */
    @Override
    public void voerEffectUit(Speler speler) {
        int effectToevoegen = selecteerEffect(speler);
        if (effectToevoegen == 0) {
            throw new IllegalArgumentException("Your race cannot carry this. Try again.");
        }
        if (getInfo().contains("Run Away")) {
            speler.voegRunAwayBonusToe(effectToevoegen);
        } else {
            speler.voegCombatStrengthToe(effectToevoegen);
        }
    }

}
