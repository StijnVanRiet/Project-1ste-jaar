package persistentie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author stijn
 */
public class StapelMapper {

    private static final String INSERT_STAPEL = "INSERT INTO ID222177_g23.Stapel (naam, spelerID, spelID)"
            + "VALUES (?, ?, ?)";
    
    /**
     *
     * @param naam
     * @param spelerID
     * @param spelID
     * @return
     */
    public int stapelOpslaan(String naam, int spelerID, int spelID){
        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(INSERT_STAPEL, Statement.RETURN_GENERATED_KEYS)) {
            query.setString(1, naam);
            query.setInt(2, spelerID);
            query.setInt(3, spelID);
            query.executeUpdate();
            
            ResultSet rs = query.getGeneratedKeys();
            
            int stapelID = 0;
            
            if(rs.next()){
                stapelID = rs.getInt(1);
            }
            
            return stapelID;

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    /**
     *
     * @param spelID
     */
    public void verwijderStapels(int spelID){
        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement("DELETE FROM ID222177_g23.Stapel WHERE spelID = " + spelID)) {
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
    public List<Integer> getStapelIDs(int spelID){
        List<Integer> IDs = new ArrayList<>();
        
            try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement("SELECT * FROM ID222177_g23.Stapel WHERE spelID = " + spelID);
                ResultSet rs = query.executeQuery()) {

            while (rs.next()) {
                int ID = rs.getInt("stapelID");

                IDs.add(ID);

            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        
        return IDs;
    }
    
    /**
     *
     * @param naam
     * @param spelID
     * @return
     */
    public int getStapelID(String naam, int spelID){
    int stapelID = 0;
            try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement("SELECT * FROM ID222177_g23.Stapel WHERE naam = \"" + naam + "\"" + " && spelID = " + spelID);
                ResultSet rs = query.executeQuery()) {

            while (rs.next()) {
                stapelID = rs.getInt("stapelID");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return stapelID;
    }
    
    /**
     *
     * @param naam
     * @param spelerID
     * @return
     */
    public int getStapelIDBySpelerID(String naam, int spelerID){
        int stapelID = 0;
            try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement("SELECT * FROM ID222177_g23.Stapel WHERE naam = \"" + naam + "\"" + " && spelerID = " + spelerID);
                ResultSet rs = query.executeQuery()) {

            while (rs.next()) {
                stapelID = rs.getInt("stapelID");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return stapelID;
    }
}
