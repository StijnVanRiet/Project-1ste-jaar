package domein;

import java.util.List;
import persistentie.SpelMapper;

/**
 *
 * @author stijn
 */
public class SpelRepository {

    private final SpelMapper spelMapper;

    /**
     *
     */
    public SpelRepository() {
        spelMapper = new SpelMapper();
    }
    
    /**
     *
     * @param naam
     * @return
     */
    public boolean checkIfNameExists(String naam){
        boolean nameExists = spelMapper.checkIfNameExists(naam);
        return nameExists;
    }
    
    /**
     *
     * @param naam
     * @return
     */
    public int getSpelID(String naam){
        int spelID = spelMapper.getSpelID(naam);
        
        return spelID;
    }
    
    /**
     *
     * @param naam
     */
    public void verwijderVorigeSpel(String naam){
        spelMapper.verwijderVorigeSpel(naam);
    }
    
    /**
     *
     * @param naam
     * @return
     */
    public int spelOpslaan(String naam){
        int spelID = spelMapper.spelOpslaan(naam);
        
        return spelID;
    }
    
    /**
     *
     * @return
     */
    public List<String> getBeschikbareSpelNamen() {
        List<String> namen = spelMapper.getBeschikbareSpelNamen();
        
        return namen;
    }
}
