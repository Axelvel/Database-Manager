package app;

import classes.Computer;
import classes.Inventory;
import classes.User;
import classes.Users;
import database.DatabaseConnection;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Model of the program
 * Contains the user, the database and the connection to sqlite databases
 */
public class Model {

    private Users users = new Users();
    private Inventory inventory = new Inventory();
    private User currentUser;
    private DatabaseConnection databaseConnection = new DatabaseConnection("src/database/ap4b_db.db",inventory,users);


    public Model() throws SQLException {
        // DATABASE CONNECTION AND INITIALIZATION
        databaseConnection.connect();
        databaseConnection.refreshDatabase();
    }

    public DatabaseConnection getDb(){
        return this.databaseConnection;
    }

    public Inventory getInventory(){
        return inventory;
    }

    public Users getUsers(){
        return users;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

}
