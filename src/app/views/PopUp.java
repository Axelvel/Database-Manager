package app.views;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Pop up window that show whenever the application has to give an information to the user
 */
public class PopUp {

    public static void display(String infoType, String text) throws IOException {
        Stage popupwindow = new Stage();
        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle(infoType);

        FileInputStream inputstream = new FileInputStream("src/resources/infoIcon.png");
        Image popupIcon = new Image(inputstream);

        popupwindow.getIcons().add(popupIcon);

        Label infoText= new Label(text);

        Button closeButton= new Button("Close");
        closeButton.setOnAction(e -> popupwindow.close());

        VBox layout= new VBox(10);

        layout.getChildren().addAll(infoText, closeButton);
        layout.setAlignment(Pos.CENTER);
        Scene scene1= new Scene(layout, 300, 150);
        popupwindow.setScene(scene1);

        popupwindow.showAndWait();

    }

}
