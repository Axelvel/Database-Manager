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

        //User Database
        User user1 = new User(1,"Jean", "Robert", true);
        User user2 = new User(2,"Jeanne", "Roberta", true);

        Users UserList = new Users();

        UserList.addUser(user1);
        UserList.addUser(user2);

        UserList.showUserslist();

        UserList.deleteUser(1);

        UserList.showUserslist();

        //Asset Database
        Computer comp1 = new Computer(1, 1,"Computer1", true,true, "Window10", 1024, 16);
        Asset asset1 = new Asset(2,1, "Asset2", true, false);

        Database db = new Database();

        db.addAsset(comp1);
        db.addAsset(asset1);

        db.showDatabase();

        System.out.println(db.available());
        System.out.println(db.status());


        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
