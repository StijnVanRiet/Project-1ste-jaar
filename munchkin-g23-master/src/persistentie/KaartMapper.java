package persistentie;

import domein.Curse;
import domein.Monster;
import domein.Race;
import domein.DungeonConsumeable;
import domein.Equipment;
import domein.TreasureConsumeable;
import domein.Kaart;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//nog toevoegen in vp

/**
 *
 * @author stijn
 */

public class KaartMapper {

    private static final String LOAD_KERKERKAARTEN_CURSE = "SELECT * FROM ID222177_g23.Kaart WHERE kaartType = \"C\" ";
    private static final String LOAD_KERKERKAARTEN_MONSTER = "SELECT * FROM ID222177_g23.Kaart WHERE kaartType = \"M\" ";
    private static final String LOAD_KERKERKAARTEN_RACE = "SELECT * FROM ID222177_g23.Kaart WHERE kaartType = \"R\" ";
    private static final String LOAD_KERKERKAARTEN_CONSUMABLE = "SELECT * FROM ID222177_g23.Kaart WHERE kaartType = \"DC\" ";
    private static final String LOAD_SCHATKAARTEN_EQUIPMENT = "SELECT * FROM ID222177_g23.Kaart WHERE kaartType = \"E\" ";
    private static final String LOAD_SCHATKAARTEN_CONSUMABLE = "SELECT * FROM ID222177_g23.Kaart WHERE kaartType = \"TC\" ";
    private static final String INSERT_KAART_STAPEL = "INSERT INTO ID222177_g23.Stapel_Kaart (kaartID, stapelID)"
            + "VALUES (?, ?)";

    /**
     *
     * @return
     */
    public List<Kaart> geefKaarten() {
        List<Kaart> kaarten = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(LOAD_KERKERKAARTEN_CURSE);
                ResultSet rs = query.executeQuery()) {

            while (rs.next()) {
                int kaartID = rs.getInt("kaartID");
                String naam = rs.getString("naam");
                String info = rs.getString("info");
                kaarten.add(new Curse(kaartID, naam, info));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(LOAD_KERKERKAARTEN_MONSTER);
                ResultSet rs = query.executeQuery()) {

            while (rs.next()) {
                int kaartID = rs.getInt("kaartID");
                String naam = rs.getString("naam");
                String info = rs.getString("info");
                int level = rs.getInt("level");
                String badStuff = rs.getString("badStuff");
                int reward = rs.getInt("reward");
                Monster monster = new Monster(kaartID, naam, info, level, badStuff, reward);
                kaarten.add(monster);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        /*    for (int i=0; i<Kerkerkaarten.size(); i++){
            System.out.println(Kerkerkaarten.get(i).getKaartID());
        }*/
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(LOAD_KERKERKAARTEN_RACE);
                ResultSet rs = query.executeQuery()) {

            while (rs.next()) {
                int kaartID = rs.getInt("kaartID");
                String naam = rs.getString("naam");
                String info = rs.getString("info");
                kaarten.add(new Race(kaartID, naam, info));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(LOAD_KERKERKAARTEN_CONSUMABLE);
                ResultSet rs = query.executeQuery()) {

            while (rs.next()) {
                int kaartID = rs.getInt("kaartID");
                String naam = rs.getString("naam");
                String info = rs.getString("info");
                kaarten.add(new DungeonConsumeable(kaartID, naam, info));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(LOAD_SCHATKAARTEN_EQUIPMENT);
                ResultSet rs = query.executeQuery()) {

            while (rs.next()) {
                int kaartID = rs.getInt("kaartID");
                String naam = rs.getString("naam");
                String info = rs.getString("info");
                int value = rs.getInt("value");
                char equipmentType = rs.getString("equipmentType").charAt(0);
                kaarten.add(new Equipment(kaartID, naam, info, value, equipmentType));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(LOAD_SCHATKAARTEN_CONSUMABLE);
                ResultSet rs = query.executeQuery()) {

            while (rs.next()) {
                int kaartID = rs.getInt("kaartID");
                String naam = rs.getString("naam");
                String info = rs.getString("info");
                int value = rs.getInt("value");
                kaarten.add(new TreasureConsumeable(kaartID, naam, info, value));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return kaarten;
    }
    
    /**
     *
     * @param kaartID
     * @param stapelID
     */
    public void kaartOpslaan(int kaartID, int stapelID){
        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(INSERT_KAART_STAPEL)) {
            query.setInt(1, kaartID);
            query.setInt(2, stapelID);
            query.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    /**
     *
     * @param stapelID
     */
    public void verwijderRelatie(int stapelID){
        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement("DELETE FROM ID222177_g23.Stapel_Kaart WHERE stapelID = " + stapelID)) {
            query.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    /**
     *
     * @param stapelID
     * @return
     */
    public List<Integer> geefKaartIDsByStapelID(int stapelID){
        
        List<Integer> kaartIDs = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement("SELECT * FROM ID222177_g23.Stapel_Kaart WHERE stapelID = " + stapelID);
                ResultSet rs = query.executeQuery()) {
            while (rs.next()) {
                int kaartID = rs.getInt("kaartID");
                kaartIDs.add(kaartID);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return kaartIDs;

    }
    
    /**
     *
     * @param kaartIDs
     * @return
     */
    public List<Kaart> geefKaartenByID(List<Integer> kaartIDs){
        List<Kaart> kaarten = new ArrayList<>();
                int kaartID;
                String naam;
                String info;
                int level;
                String badStuff;
                int reward;
                int value;
                char equipmentType;
                String joined = kaartIDs.toString().replace("[", "").replace("]", "");
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);

                PreparedStatement query = conn.prepareStatement("SELECT * FROM ID222177_g23.Kaart WHERE KaartID IN ("+ joined + ")");
                ResultSet rs = query.executeQuery()) {
            
            while (rs.next()) {
                switch (rs.getString("kaartType")) {
                    case "C":
                        kaartID = rs.getInt("kaartID");
                        naam = rs.getString("naam");
                        info = rs.getString("info");
                        kaarten.add(new Curse(kaartID, naam, info));
                        break;
                    case "M":
                        kaartID = rs.getInt("kaartID");
                        naam = rs.getString("naam");
                        info = rs.getString("info");
                        level = rs.getInt("level");
                        badStuff = rs.getString("badStuff");
                        reward = rs.getInt("reward");
                        kaarten.add(new Monster(kaartID, naam, info, level, badStuff, reward));
                        break;
                    case "R":
                        kaartID = rs.getInt("kaartID");
                        naam = rs.getString("naam");
                        info = rs.getString("info");
                        kaarten.add(new Race(kaartID, naam, info));
                        break;
                    case "DC":
                        kaartID = rs.getInt("kaartID");
                        naam = rs.getString("naam");
                        info = rs.getString("info");
                        kaarten.add(new DungeonConsumeable(kaartID, naam, info));
                        break;
                    case "E":
                        kaartID = rs.getInt("kaartID");
                        naam = rs.getString("naam");
                        info = rs.getString("info");
                        value = rs.getInt("value");
                        equipmentType = rs.getString("equipmentType").charAt(0);
                        kaarten.add(new Equipment(kaartID, naam, info, value, equipmentType));
                        break;
                    case "TC":
                        kaartID = rs.getInt("kaartID");
                        naam = rs.getString("naam");
                        info = rs.getString("info");
                        value = rs.getInt("value");
                        kaarten.add(new TreasureConsumeable(kaartID, naam, info, value));
                        break;
                    default:
                        throw new IllegalArgumentException("ongeldige kaart");
                }
            }
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return kaarten;

    }
}
