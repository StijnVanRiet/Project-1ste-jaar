package persistentie;

import domein.Speler;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//nog toevoegen in vp

/**
 *
 * @author stijn
 */

public class SpelerMapper {

    private static final String INSERT_SPELER = "INSERT INTO ID222177_g23.Speler (naam, geslacht, niveau, gold, spelID, aanBeurt)"
            + "VALUES (?, ?, ?, ?, ?, ?)";
    private static final String DELETE_SPELER = "DELETE FROM ID222177_g23.Speler";

    /**
     *
     * @param speler
     */
    public void voegToe(Speler speler) {

        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(INSERT_SPELER)) {
            query.setString(1, speler.getNaam());
            query.setInt(2, speler.getNiveau());
            query.setString(3, speler.getRas());
            query.setString(4, String.valueOf(speler.getGeslacht()));
            query.setString(5, speler.getStatus());
            query.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     *
     * @param spelID
     */
    public void verwijderSpelers(int spelID) {
        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement("DELETE FROM ID222177_g23.Speler WHERE spelID = " + spelID)) {
            query.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     *
     * @param spelID
     * @return
     */
    public List<Speler> geefSpelers(int spelID) {
        List<Speler> spelers = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement("SELECT * FROM ID222177_g23.Speler WHERE SpelID = " + spelID);
                ResultSet rs = query.executeQuery()) {

            while (rs.next()) {
                String naam = rs.getString("naam");
                int niveau = rs.getInt("niveau");
            //   int combatStrength = rs.getInt("combatStrength");
            //    int runAwayBonus = rs.getInt("runAwayBonus");
                int gold = rs.getInt("gold");
            //    String status = rs.getString("status");
            //    String ras = rs.getString("ras");
                char geslacht = rs.getString("geslacht").charAt(0);

                spelers.add(new Speler(naam, niveau, /*combatStrength, runAwayBonus, */gold, /*status, ras,*/ geslacht));

            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

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
        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(INSERT_SPELER, Statement.RETURN_GENERATED_KEYS)) {
            query.setString(1, naam);
            query.setString(2, String.valueOf(geslacht));
            query.setInt(3, niveau);
            query.setInt(4, gold);
            query.setInt(5, spelID);
            query.setBoolean(6, aanBeurt);
            query.executeUpdate();
            
            ResultSet rs = query.getGeneratedKeys();
            
            int spelerID = 0;
            
            if(rs.next()){
            spelerID = rs.getInt(1);
            }

            return spelerID;

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    /**
     *
     * @param spelID
     * @return
     */
    public int getAantalSpelers(int spelID){
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement("SELECT * FROM ID222177_g23.Speler WHERE spelID = \"" + spelID + "\"");
                ResultSet rs = query.executeQuery()) {
            
            int aantalSpelers = 0;
            while (rs.next()) {
                aantalSpelers++;
            }
            
            return aantalSpelers;
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    /**
     *
     * @param spelID
     * @param naam
     * @return
     */
    public int getSpelerID(int spelID, String naam){
        int spelerID = 0;
            try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement("SELECT * FROM ID222177_g23.Speler WHERE naam = \"" + naam + "\"" + " && spelID = " + spelID);
                ResultSet rs = query.executeQuery()) {

            while (rs.next()) {
                spelerID = rs.getInt("spelerID");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return spelerID;
    }
    
    /**
     *
     * @param naam
     * @param spelID
     * @return
     */
    public boolean checkAanBeurt(String naam, int spelID){
        boolean aanBeurt = false;
            try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement("SELECT * FROM ID222177_g23.Speler WHERE naam = \"" + naam + "\"" + " && spelID = " + spelID);
                ResultSet rs = query.executeQuery()) {

            while (rs.next()) {
                int aanBeurtInt = rs.getInt("aanBeurt");
                if (aanBeurtInt == 1){aanBeurt = true;}
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return aanBeurt;
    }
}
