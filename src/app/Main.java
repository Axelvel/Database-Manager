package app;

import app.controllers.LoginController;
import classes.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Model dataModel = new Model();
        Controller logController = new LoginController(dataModel);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("views/loginView.fxml"));
        loader.setController(logController);
        Parent root = loader.load();

        root.setStyle("-fx-font-family: \"Verdana\"");

        primaryStage.setTitle("IT Asset Manager");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.initStyle(StageStyle.DECORATED);
        //primaryStage.sizeToScene();


        primaryStage.show();


        }

    public static void main(String[] args){
        launch(args);
    }
}
