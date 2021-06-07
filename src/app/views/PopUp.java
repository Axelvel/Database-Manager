package app.views;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Pop up window that show whenever the application has to give an information to the user
 */
public class PopUp {

    public static void display(String text)
    {
        Stage popupwindow = new Stage();
        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("This is a pop up window");

        Label label1= new Label(text);

        Button button1= new Button("Close");
        button1.setOnAction(e -> popupwindow.close());

        VBox layout= new VBox(10);

        layout.getChildren().addAll(label1, button1);
        layout.setAlignment(Pos.CENTER);
        Scene scene1= new Scene(layout, 300, 150);
        popupwindow.setScene(scene1);

        popupwindow.showAndWait();

    }

}
