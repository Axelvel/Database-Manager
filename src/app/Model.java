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
    private DatabaseConnection databaseConnection = new DatabaseConnection("src/database/ap4b_db.db",inventory,users);

    public Model() throws SQLException {
        // Creating new users
        User user1 = new User("abc", "pass", "Jean", "Robert", true);
        User user2 = new User("xyz", "word", "Jeanne", "Roberta", true);

        users.addUser(user1);
        users.addUser(user2);

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

}
