package app.controllers;

import app.views.PopUp;
import classes.Controller;
import app.Model;
import classes.User;
import database.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


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


    private void gotoProfile() throws Exception {
        Stage window = (Stage) root.getScene().getWindow();
        Controller controller = new MainController(this.dataModel);
        changeScene(window, "../views/mainView.fxml", controller, 600, 700);
        window.setMaximized(true);
    }

    @FXML
    public void login() throws Exception {

        String username = usernameField.getText();
        String password = passwordField.getText();

        if (DatabaseConnection.getInstance().getUsers().passwordMatch(username, password)) {
            System.out.println("Login!");
            int userIndex = DatabaseConnection.getInstance().getUsers().getUserIndex(username);
            User user = DatabaseConnection.getInstance().getUsers().getUsers().get(userIndex);
            dataModel.setCurrentUser(user);
            gotoProfile();
        } else {
            PopUp.displayMessage("WARNING","Wrong username or password");
        }
    }
}
