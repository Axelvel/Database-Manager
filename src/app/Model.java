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

    private User currentUser;

    public Model() throws SQLException {
        // DATABASE CONNECTION AND INITIALIZATION
        DatabaseConnection.getInstance().connect();
        DatabaseConnection.getInstance().refreshDatabase();
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

}
