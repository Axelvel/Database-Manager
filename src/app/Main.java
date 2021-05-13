package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view.fxml"));
        root.setStyle("-fx-font-family: \"Verdana\"");
        primaryStage.setTitle("IT Asset Manager");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

        Connection dbConnector = DriverManager.getConnection("jdbc:sqlite:C:/Users/teimo/Documents/COURS/INFO02/AP4B/Projet/Database-Manager/database/ap4b_db.db");

        Statement stmt = dbConnector.createStatement();
        ResultSet rs = stmt.executeQuery ("SELECT * FROM test_table");
        while (rs.next()) {
            System.out.println("ID : " + rs.getString("id"));
            System.out.println("test : " + rs.getString("test_column"));
        }


        //String currentPath = new java.io.File(".").getCanonicalPath();
        //System.out.println("Current dir:" + currentPath);
        }

    public static void main(String[] args) {
        launch(args);
    }
}
