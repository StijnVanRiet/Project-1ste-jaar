package gui;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 *
 * @author stijn
 */
public class WelkomScherm extends Pane {

    /**
     *
     */
    public WelkomScherm() {
        Label lblWelkom = new Label("Welkom bij Munchkin!");

        ImageView ivImage = new ImageView(new Image(getClass().getResourceAsStream("/images/startscherm.jpg")));

        lblWelkom.setLayoutX(200);
        lblWelkom.setLayoutY(10);
        ivImage.setLayoutX(50);
        ivImage.setLayoutY(50);

        this.getChildren().addAll(lblWelkom, ivImage);
    }

}
