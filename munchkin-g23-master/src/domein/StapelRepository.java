package domein;

import java.util.List;
import persistentie.StapelMapper;

/**
 *
 * @author stijn
 */
public class StapelRepository {

    private final StapelMapper stapelMapper;

    /**
     *
     */
    public StapelRepository() {
        stapelMapper = new StapelMapper();
    }
    
    /**
     *
     * @param spelID
     * @return
     */
    public List<Integer> verwijderStapels(int spelID){
        List<Integer> kaartIDs = stapelMapper.getStapelIDs(spelID);
        stapelMapper.verwijderStapels(spelID);        
        return kaartIDs;
    }
    
    /**
     *
     * @param naam
     * @param spelerID
     * @param spelID
     * @return
     */
    public int stapelOpslaan(String naam, int spelerID, int spelID){
        int stapelID = stapelMapper.stapelOpslaan(naam, spelerID, spelID);        
        return stapelID;
    }
    
    /**
     *
     * @param soort
     * @param spelID
     * @return
     */
    public int getStapelID(String soort, int spelID){
        int stapelID = stapelMapper.getStapelID(soort, spelID);
        
        return stapelID;
    }
    
    /**
     *
     * @param soort
     * @param spelerID
     * @return
     */
    public int getStapelIDBySpelerID(String soort, int spelerID){
        int stapelID = stapelMapper.getStapelIDBySpelerID(soort, spelerID);
        
        return stapelID;
    }
}
