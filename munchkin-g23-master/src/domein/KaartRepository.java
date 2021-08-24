package domein;

import java.util.ArrayList;
import java.util.List;
import persistentie.KaartMapper;

/**
 *
 * @author stijn
 */
public class KaartRepository {

    private final KaartMapper kaartMapper;

    /**
     *
     */
    public KaartRepository() {
        kaartMapper = new KaartMapper();
    }

    /**
     *
     * @return
     */
    public List<Kaart> geefKaarten() {
        List<Kaart> kaarten = kaartMapper.geefKaarten();
        return kaarten;
    }
    
    /**
     *
     * @param kaartID
     * @param stapelID
     */
    public void kaartOpslaan(int kaartID, int stapelID){
        kaartMapper.kaartOpslaan(kaartID, stapelID);
    }
    
    /**
     *
     * @param stapelIDs
     */
    public void verwijderRelaties(List<Integer> stapelIDs){
        for(int i = 0; i < stapelIDs.size(); i++){
            int stapelID = stapelIDs.get(i);
            kaartMapper.verwijderRelatie(stapelID);
        }
    }
    
    /**
     *
     * @param stapelID
     * @return
     */
    public List<Kaart> getKaarten(int stapelID){
        List<Kaart> kaarten = new ArrayList<>();
        List<Integer> kaartIDs = kaartMapper.geefKaartIDsByStapelID(stapelID);
        if (kaartIDs.size() != 0){
            kaarten = kaartMapper.geefKaartenByID(kaartIDs);
        }
        return kaarten;
    }
}
