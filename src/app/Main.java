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
        /*DatabaseConnection connection = new DatabaseConnection("database/ap4b_db.db");
        connection.connect();
        ResultSet rs = connection.query("SELECT * FROM assets_table");

        while (rs.next()) {
            System.out.println("code : " + rs.getString("asset_code"));
            System.out.println("type : " + rs.getString("asset_type"));
            System.out.println("status : " + rs.getString("asset_status"));
            System.out.println("availability : " + rs.getString("asset_availability"));
        }

        ResultSet rs_2 = connection.query("SELECT computers_table.computer_code, computers_table.computer_brand, computers_table.computer_os, computers_table.computer_memory, computers_table.computer_ram, assets_table.asset_status, assets_table.asset_availability\n" +
                "FROM assets_table INNER JOIN computers_table ON assets_table.[asset_Code] = computers_table.[computer_Code];\n");
        ResultSetMetaData rsmd = rs_2.getMetaData();
        int columnsNumber = rsmd.getColumnCount();

        while (rs_2.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1) System.out.print(",  ");
                String columnValue = rs_2.getString(i);
                System.out.print(columnValue);
            }
            System.out.println("");
        }


        connection.close();*/

        launch(args);
    }
}
