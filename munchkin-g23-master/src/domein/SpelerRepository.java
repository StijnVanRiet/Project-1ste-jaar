package domein;

import java.util.List;
import persistentie.SpelerMapper;

/**
 *
 * @author stijn
 */
public class SpelerRepository {

    private final SpelerMapper spelerMapper;

    /**
     *
     */
    public SpelerRepository() {
        spelerMapper = new SpelerMapper();
    }

    /**
     *
     * @param speler
     */
    public void voegToe(Speler speler) {
        spelerMapper.voegToe(speler);
    }

    /**
     *
     * @param spelID
     */
    public void verwijderSpelers(int spelID) {
        spelerMapper.verwijderSpelers(spelID);
    }

    /**
     *
     * @param spelID
     * @return
     */
    public List<Speler> geefSpelers(int spelID) {
        List<Speler> spelers = spelerMapper.geefSpelers(spelID);
        return spelers;
    }
    
    /**
     *
     * @param naam
     * @param geslacht
     * @param niveau
     * @param gold
     * @param spelID
     * @param aanBeurt
     * @return
     */
    public int spelerOpslaan(String naam, char geslacht, int niveau, int gold, int spelID, boolean aanBeurt){
        int spelerID = spelerMapper.spelerOpslaan(naam, geslacht, niveau, gold, spelID, aanBeurt);   
        return spelerID;
    }
    
    /**
     *
     * @param spelID
     * @return
     */
    public int getAantalSpelers(int spelID){
        int aantalSpelers = spelerMapper.getAantalSpelers(spelID);
        
        return aantalSpelers;
    }
    
    /**
     *
     * @param spelID
     * @param spelerNaam
     * @return
     */
    public int getSpelerID(int spelID, String spelerNaam){
        int spelerID = spelerMapper.getSpelerID(spelID, spelerNaam);
        
        return spelerID;
    }
    
    /**
     *
     * @param naam
     * @param spelID
     * @return
     */
    public boolean checkAanBeurt(String naam, int spelID){
        boolean aanBeurt = spelerMapper.checkAanBeurt(naam, spelID);
        
        return aanBeurt;
    }

}
