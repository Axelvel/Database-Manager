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
    public void addAsset(){
    }
}
