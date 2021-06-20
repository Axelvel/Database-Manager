package app;

import classes.User;
import database.DatabaseConnection;

import java.sql.SQLException;

/**
 * Model of the program
 * Contains the current user, or other useful parameters
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
