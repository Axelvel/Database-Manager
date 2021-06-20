package app.controllers;

import app.views.PopUp;
import classes.Controller;
import app.Model;
import classes.User;
import database.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Controller class for the LoginController fxml file
 */
public class LoginController extends Controller {

    public LoginController(Model dataModel) {
        super(dataModel);
    }

    @FXML
    private GridPane root;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    /**
     * After connection, switch the scene to the main view
     * @throws Exception
     */
    private void gotoMain() throws Exception {
        Stage window = (Stage) root.getScene().getWindow();
        Controller controller = new MainController(this.dataModel);
        changeScene(window, "../views/MainView.fxml", controller, 800, 700);
        window.centerOnScreen();
    }

    /**
     * Checks if the username and the password matches a user in the database
     * and if yes, connect the user
     * @throws Exception
     */
    @FXML
    public void login() throws Exception {

        String username = usernameField.getText();
        String password = passwordField.getText();

        if (DatabaseConnection.getInstance().getUsers().passwordMatch(username, password)) {
            int userIndex = DatabaseConnection.getInstance().getUsers().getUserIndex(username);
            User user = DatabaseConnection.getInstance().getUsers().getUsers().get(userIndex);
            dataModel.setCurrentUser(user);
            gotoMain();
        } else {
            PopUp.displayMessage("WARNING","Wrong username or password");
        }
    }

    /**
     * Quit the application and close the database connection
     */
    @FXML
    public void quit(){
        DatabaseConnection.getInstance().close();
        System.exit(0);
    }
}
