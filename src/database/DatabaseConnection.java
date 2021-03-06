package database;

import app.Type;
import classes.*;

import java.sql.*;

/**
 * Database connection class.
 * Manage database query, adding new elements to the db, etc...
 */
public class DatabaseConnection {
    private String dbPath = "database file path";
    private Connection connection = null;
    private Statement statement = null;

    private Inventory inventory;
    private Users users;

    private static DatabaseConnection db = null;
    private DatabaseConnection db1;

    private DatabaseConnection(String dbPath){
        this.dbPath = dbPath;
        this.inventory = new Inventory();
        this.users = new Users();
    }

    public static DatabaseConnection getInstance(){
        if(db == null) {
            db = new DatabaseConnection("src/database/ap4b_db.db");
            return db;
        }
        return db;
    }

    public Inventory getInventory(){
        return inventory;
    }

    public Users getUsers(){
        return users;
    }
    /**
     * Create a connection with the database
     */
    public void connect(){
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:"+dbPath);
            statement = connection.createStatement();
        } catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }

    /**
     * Close the database connection
     */
    public void close() {
        try {
            connection.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        db = null;
    }

    /**
     * This method executes a sql request to the database and returns a ResultSet
     * To go through the ResultSet, use the next() method. For each result, you can get the value of a column
     * by using getString(ColumnName)
     *
     * @param request String : sql request
     * @return result ResultSet : a ResultSet object with the request's results
     */
    public ResultSet query(String request) {
        ResultSet result = null;
        try {
            result = statement.executeQuery(request);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Request error : " + request);
        }
        return result;
    }

    public void updateAsset(Asset asset) throws SQLException {
        try {
            String sqlAsset = "UPDATE assets_table set asset_status = ?, asset_availability = ? WHERE asset_code = '" + asset.getCode() + "';";
            PreparedStatement pstmtAsset = connection.prepareStatement(sqlAsset);
            pstmtAsset.setString(1, asset.getStatus());
            pstmtAsset.setBoolean(2, asset.isAvailable());
            pstmtAsset.executeUpdate();

            if (asset.getType() == Type.COMPUTER) updateComputer(asset);
            if (asset.getType() == Type.KEYBOARD) updateKeyboard(asset);
        }catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updateComputer(Asset c) throws SQLException {
        String sqlComputer = "UPDATE computers_table set computer_brand = ?, computer_os = ?, computer_ram = ?, computer_memory = ? WHERE computer_code = '"+c.getCode()+"';";
        PreparedStatement pstmtAsset = connection.prepareStatement(sqlComputer);
        pstmtAsset.setString(1,c.getBrand());
        pstmtAsset.setString(2,c.getOs());
        pstmtAsset.setInt(3,c.getRam());
        pstmtAsset.setInt(4,c.getMemory());
        pstmtAsset.executeUpdate();
    }

    public void updateKeyboard(Asset k) throws SQLException {
        String sqlComputer = "UPDATE keyboards_table set keyboard_brand = ?, keyboard_wireless = ?, keyboard_switches = ? WHERE keyboard_code = '"+k.getCode()+"';";
        PreparedStatement pstmtAsset = connection.prepareStatement(sqlComputer);
        pstmtAsset.setString(1,k.getBrand());
        pstmtAsset.setBoolean(2,k.getWireless());
        pstmtAsset.setString(3,k.getSwitches());
        pstmtAsset.executeUpdate();
    }
    /**
     * Method used in order to add a new asset to the sql database
     */
    public void addAsset(Asset asset) throws SQLException {
        try {
            String sql = "INSERT INTO assets_table(asset_code,asset_type,asset_status,asset_availability) VALUES(?,?,?,?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, asset.getCode());
            pstmt.setString(2, asset.getType().toString());
            pstmt.setString(3, asset.getStatus());
            pstmt.setInt(4, 1);
            pstmt.executeUpdate();

            if (asset.getType() == Type.COMPUTER) {
                addComputer(asset);
            } else if (asset.getType() == Type.KEYBOARD) {
                addKeyboard(asset);
            }

            inventory.addAsset(asset);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Method used in order to add a new computer to the sql database
     */
    public void addComputer(Asset c) throws SQLException {
        String sqlType = "INSERT INTO computers_table(computer_code,computer_brand,computer_os,computer_memory,computer_ram) VALUES(?,?,?,?,?)";
        PreparedStatement pstmtType = connection.prepareStatement(sqlType);
        pstmtType.setString(1, c.getCode());
        pstmtType.setString(2, c.getBrand());
        pstmtType.setString(3, c.getOs());
        pstmtType.setInt(4, c.getMemory());
        pstmtType.setInt(5, c.getRam());
        pstmtType.executeUpdate();
    }

    /**
     * Method used in order to add a new keyboard to the sql database
     */
    public void addKeyboard(Asset k) throws SQLException{
        String sqlType = "INSERT INTO keyboards_table(keyboard_code,keyboard_brand,keyboard_wireless,keyboard_switches) VALUES(?,?,?,?)";
        PreparedStatement pstmtType = connection.prepareStatement(sqlType);
        pstmtType.setString(1, k.getCode());
        pstmtType.setString(2, k.getBrand());
        pstmtType.setBoolean(3, k.getWireless());
        pstmtType.setString(4, k.getSwitches());
        pstmtType.executeUpdate();
    }

    /**
     * Method used in order to delete an asset from the sqlite database
     * @param asset : asset the user wants to delete
     * @throws SQLException
     */
    public void deleteAsset(Asset asset) throws SQLException {
        String sql ="DELETE FROM assets_table WHERE asset_code = '" + asset.getCode() +"'";
        statement.executeUpdate(sql);
        if (asset.getType() == Type.COMPUTER){
            deleteAssetType("computers_table","computer_code",asset.getCode());
        } else if (asset.getType() == Type.KEYBOARD){
            deleteAssetType("keyboards_table","keyboard_code",asset.getCode());
        }

        inventory.removeAsset(asset.getCode());
    }

    /**
     * Method used in order to delete any asset from the specific table in
     * the sql database
     * @param table : name of the table
     * @param type_code : name of the column
     * @param code : code of the asset
     * @throws SQLException : exception
     */
    public void deleteAssetType(String table, String type_code, String code) throws SQLException {
        String sql = "DELETE FROM "+table+" WHERE "+type_code+ " = '" + code + "'";
        statement.executeUpdate(sql);
    }

    /**
     * refresh the database according to the content of the sqlite database
     * go througn every table in the sqlite database and add each element in the database
     * @throws SQLException : sql exception
     */
    public void refreshDatabase() throws SQLException {
        inventory.clear();
        users.getUsers().clear();

        refreshUsers();
        refreshComputers();
        refreshKeyboards();
    }

    /**
     * go through all the content of the computers_table and add them in the inventory
     * @throws SQLException : exception
     */
    public void refreshComputers() throws SQLException {
        ResultSet rs = query("SELECT computers_table.computer_code, " +
                "computers_table.computer_brand, computers_table.computer_os, computers_table.computer_memory, " +
                "computers_table.computer_ram, assets_table.asset_status, assets_table.asset_availability " +
                "FROM assets_table INNER JOIN computers_table ON " +
                "assets_table.[asset_Code] = computers_table.[computer_Code];\n");

        while(rs.next()) {
            String code = rs.getString("computer_code");
            String brand = rs.getString("computer_brand");
            String os = rs.getString("computer_os");
            int memory = rs.getInt("computer_memory");
            int ram = rs.getInt("computer_ram");
            String status = rs.getString("asset_status");
            boolean availability = rs.getBoolean("asset_availability");
            Computer c = new Computer(code, Type.COMPUTER, status, availability, brand, os, memory, ram);
            inventory.addAsset(c);
        }
    }

    /**
     * go through all the content of the keyboards_table and add them in the inventory
     * @throws SQLException : exception
     */
    public void refreshKeyboards() throws SQLException {
        ResultSet rs = query("SELECT keyboards_table.keyboard_code, keyboards_table.keyboard_brand, " +
                "keyboards_table.keyboard_wireless, keyboards_table.keyboard_switches, assets_table.asset_status, " +
                "assets_table.asset_availability FROM assets_table INNER JOIN keyboards_table ON " +
                "assets_table.asset_code = keyboards_table.keyboard_code;\n");

        while(rs.next()){
            String code = rs.getString("keyboard_code");
            String brand = rs.getString("keyboard_brand");
            boolean wireless = rs.getBoolean("keyboard_wireless");
            String switches = rs.getString("keyboard_switches");
            String status = rs.getString("asset_status");
            boolean availability = rs.getBoolean("asset_availability");
            Keyboard k = new Keyboard(code,Type.KEYBOARD,status,availability,brand,wireless,switches);
            inventory.addAsset(k);
        }
    }

    /**
     * add a new user to users_table
     * @throws SQLException : exception
     */
    public void addUser(User u) throws SQLException {
        String sql = "INSERT INTO users_table(user_username,user_password,user_name,user_last_name,user_status) VALUES (?,?,?,?,?) ON CONFLICT (user_username) DO NOTHING;";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, u.getUsername());
        pstmt.setString(2,u.getPassword());
        pstmt.setString(3, u.getName());
        pstmt.setString(4, u.getLastName());
        pstmt.setBoolean(5,u.isAdmin());
        pstmt.executeUpdate();

        users.addUser(u);
    }

    /**
     * delete a user from users_table
     * @throws SQLException : exception
     */
    public void deleteUser(User u) throws SQLException {
        String sql = "DELETE FROM users_table WHERE user_username = '" + u.getUsername() +"'";
        statement.executeUpdate(sql);
        users.deleteUser(u.getUsername());
    }

    /**
     * go through all the content of the users_table
     * @throws SQLException : exception
     */
    public void refreshUsers() throws SQLException {

        ResultSet rs = query("SELECT * from users_table");
        while(rs.next()){
            String username = rs.getString("user_username");
            String password = rs.getString("user_password");
            String name = rs.getString("user_name");
            String lastName = rs.getString("user_last_name");
            boolean admin = rs.getBoolean("user_status");
            User u = new User(username,password,name,lastName,admin);
            users.addUser(u);
        }
    }

    public int count(String table) throws SQLException {
        ResultSet rs = query("SELECT COUNT() FROM "+table);
        return rs.getInt(1);
    }
}
