package app;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Model {

    public Users users = new Users();
    public Database database = new Database();
    private DatabaseConnection assetsDbConnection = new DatabaseConnection("database/ap4b_db.db");


    public Model() throws SQLException {

        //Creating new users
        User user1 = new User(1, "abc", "pass", "Jean", "Robert", true);
        User user2 = new User(2, "xyz", "word", "Jeanne", "Roberta", true);

        users.addUser(user1);
        users.addUser(user2);

        // DATABASE INITIALIZATION
        assetsDbConnection.connect();

        //Creating computers
        ResultSet rs = assetsDbConnection.query("SELECT computers_table.computer_code, " +
                "computers_table.computer_brand, computers_table.computer_os, computers_table.computer_memory, " +
                "computers_table.computer_ram, assets_table.asset_status, assets_table.asset_availability\n" +
                "FROM assets_table INNER JOIN computers_table ON " +
                "assets_table.[asset_Code] = computers_table.[computer_Code];\n");

        while(rs.next()){
            String code = rs.getString("computer_code");
            String brand = rs.getString("computer_brand");
            String os = rs.getString("computer_os");
            int memory = rs.getInt("computer_memory");
            int ram = rs.getInt("computer_ram");
            String status = rs.getString("asset_status");
            boolean availability = rs.getBoolean("asset_availability");
            Computer c = new Computer(code, Type.COMPUTER, status, availability,brand, os, memory, ram);
            database.addAsset(c);
        }

    }

    public DatabaseConnection getDb(){
        return this.assetsDbConnection;
    }

}
