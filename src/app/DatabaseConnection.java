package app;

import java.sql.*;

/**
 * Database connection class.
 * Manage database query, adding new elements to the db, etc...
 */
public class DatabaseConnection {
    private String dbPath = "database file path";
    private Connection connection = null;
    private Statement statement = null;

    /**
     * Constructor
     * @param dbPath : file path to the database
     */
    public DatabaseConnection(String dbPath){
        this.dbPath = dbPath;
    }

    /**
     * Create a connection with the database
     */
    public void connect(){
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:"+dbPath);
            statement = connection.createStatement();
            System.out.println("Connection");
        } catch(SQLException sqlException){
            sqlException.printStackTrace();
            System.out.println("Connection error");
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

    /**
     * Method used in order to ann a new asset to the database
     * TODO
     */
    public void addAsset(Asset asset) throws SQLException {
        String sql = "INSERT INTO assets_table(asset_code,asset_type,asset_status,asset_availability) VALUES(?,?,?,?)";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, asset.getCode());
        pstmt.setString(2, asset.getType().toString());
        pstmt.setString(3, asset.getStatus());
        pstmt.setInt(4, 1);
        pstmt.executeUpdate();

        if (asset.getType() == Type.COMPUTER){
            String sqlType = "INSERT INTO computers_table(computer_code,computer_brand,computer_os,computer_memory,computer_ram) VALUES(?,?,?,?,?)";
            PreparedStatement pstmtType = connection.prepareStatement(sqlType);
            pstmtType.setString(1, asset.getCode());
            pstmtType.setString(2, asset.getBrand());
            pstmtType.setString(3, asset.getOs());
            pstmtType.setInt(4, asset.getMemory());
            pstmtType.setInt(5, asset.getRam());
            pstmtType.executeUpdate();
        }
    }

    public void deleteAsset(Asset asset) throws SQLException {
        String sql ="DELETE FROM assets_table WHERE asset_code = '" + asset.getCode() +"'";
        System.out.println(sql);
        statement.executeUpdate(sql);
        if (asset.getType() == Type.COMPUTER){
            String sql_2 ="DELETE FROM computers_table WHERE computer_code = '" + asset.getCode() +"'";
            statement.executeUpdate(sql_2);
        }
    }

    //TEST
    public void printComputers() throws SQLException {
        String sql = "SELECT * FROM computers_table";
        ResultSet result = statement.executeQuery(sql);
        while(result.next()){
            System.out.println(result.getString("computer_code"));
        }
    }

    public void printAssets() throws SQLException {
        String sql = "SELECT * FROM assets_table";
        ResultSet result = statement.executeQuery(sql);
        while(result.next()){
            System.out.println(result.getString("asset_code"));
        }
    }
}
