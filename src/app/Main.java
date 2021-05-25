package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{



        Model dataModel = new Model();
        Controller controller = new LoginController(dataModel);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("loginView.fxml"));
        loader.setController(controller);
        Parent root = loader.load();

        root.setStyle("-fx-font-family: \"Verdana\"");

        primaryStage.setTitle("IT Asset Manager");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.sizeToScene();

        primaryStage.show();


        }

    public static void main(String[] args) throws SQLException {
        /*DATABASE TEST*/
        DatabaseConnection connection = new DatabaseConnection("database/ap4b_db.db");
        connection.connect();
        ResultSet rs = connection.query("SELECT * FROM test_table");

        while (rs.next()) {
            System.out.println("ID : " + rs.getString("id"));
            System.out.println("test : " + rs.getString("test_column"));
        }

        connection.close();

        launch(args);
    }
}
