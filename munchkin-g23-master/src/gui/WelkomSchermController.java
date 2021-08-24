/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author PERNIELE
 */
public class WelkomSchermController implements Initializable {
    
    /**
     *
     */
    public WelkomSchermController()
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("WelkomScherm.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        
        try
        {
            loader.load();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
