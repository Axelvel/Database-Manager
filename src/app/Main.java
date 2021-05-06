package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("view.fxml")));

        root.setStyle("-fx-font-family: \"Verdana\"");

        primaryStage.setTitle("IT Asset Manager");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.sizeToScene();

        Vbox userFields = createContent();

        primaryStage.show();
    }

    public static VBox createContent()
    {
        VBox vboxLayout = new VBox();
        vboxLayout.setPadding(new Insets(10));
        vboxLayout.setSpacing(8);

        Text titleLabel = new Text("Welcome ! \n" + "Please login");
        Text userLabel = new Text("Username :");
        Text passwordLabel = new Text("Password :");

        return vboxLayout;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
