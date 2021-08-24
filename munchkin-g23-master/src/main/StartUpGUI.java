package main;

import gui.WelkomScherm;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author stijn
 */
public class StartUpGUI extends Application {
    
    /**
     *
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        WelkomScherm root = new WelkomScherm();
        
        Scene scene = new Scene(root, 500, 300);
        
        primaryStage.setScene(scene);
        
        primaryStage.setTitle("Welkom!");       
        primaryStage.show();
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
