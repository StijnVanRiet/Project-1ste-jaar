package persistentie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author stijn
 */
public class SpelMapper {
    
    private static final String INSERT_SPEL = "INSERT INTO ID222177_g23.Spel (naam)"
        + "VALUES (?)";

    /**
     *
     * @param naam
     * @return
     */
    public boolean checkIfNameExists(String naam) {

        boolean nameExists;
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                ){
            String myString = "SELECT * FROM ID222177_g23.Spel WHERE naam = \"" + naam + "\"";
                PreparedStatement query = conn.prepareStatement(myString);
                ResultSet rs = query.executeQuery();
            nameExists = rs.next();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return nameExists;
    }
    
    /**
     *
     * @param naam
     * @return
     */
    public int getSpelID(String naam){
        
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                ) {
                String myString = "SELECT ID FROM ID222177_g23.Spel WHERE naam = \"" + naam + "\"";
                PreparedStatement query = conn.prepareStatement(myString);
                ResultSet rs = query.executeQuery();
                
                int spelID = 0;
                if(rs.next()){
                    spelID = rs.getInt("ID");
                }
                
                return spelID;
                
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    /**
     *
     * @param naam
     */
    public void verwijderVorigeSpel(String naam){
        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement("DELETE FROM ID222177_g23.Spel WHERE naam = \"" + naam + "\"")) {
            query.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    /**
     *
     * @param naam
     * @return
     */
    public int spelOpslaan(String naam){
        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(INSERT_SPEL, Statement.RETURN_GENERATED_KEYS)) {
            query.setString(1, naam);
            query.executeUpdate();
            
            ResultSet rs = query.getGeneratedKeys();
            int spelID = 0;
            
            if(rs.next()){
                spelID = rs.getInt(1);
            }

            return spelID;

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    /**
     *
     * @return
     */
    public List<String> getBeschikbareSpelNamen(){
                List<String> namen = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement("SELECT naam FROM ID222177_g23.Spel");
                ResultSet rs = query.executeQuery()) {

            while (rs.next()) {
                String naam = rs.getString("naam");

                namen.add(naam);

            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return namen;
    }
}

