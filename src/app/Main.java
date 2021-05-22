package app;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Objects;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{


        //TODO: Create new controller object?

        Model dataModel = new Model();
        //dataModel.database.showDatabase();

        Controller controller = new LoginController(dataModel);

        //Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("view.fxml")));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("view.fxml"));
        loader = new FXMLLoader(getClass().getResource("loginView.fxml"));
        loader.setController(controller);
        Parent root = loader.load();

        root.setStyle("-fx-font-family: \"Verdana\"");

        primaryStage.setTitle("IT Asset Manager");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.sizeToScene();





        primaryStage.show();




    }


    public static void main(String[] args) {
        launch(args);
    }
}
