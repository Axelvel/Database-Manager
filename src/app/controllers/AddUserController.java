package app.controllers;

import app.views.PopUp;
import classes.Controller;
import app.Model;
import classes.User;
import database.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.sql.ResultSet;

/**
 * Controller class for the AddUserView fxml file
 */
public class AddUserController extends Controller {

    private MainController mainCtrl;

    public AddUserController(Model dataModel, MainController mainCtrl) {
        super(dataModel);
        this.mainCtrl = mainCtrl;
    }

    @FXML
    private GridPane root;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField lastnameField;

    @FXML
    private RadioButton statusToggle1;

    @FXML
    private RadioButton statusToggle2;

    /**
     * Add an user profile depending on what informations the current user has given
     * @throws Exception
     */
    @FXML
    public void addUser() throws Exception {

        String username = usernameField.getText();
        ResultSet checkUserExists = DatabaseConnection.getInstance().query(
                "SELECT * FROM users_table WHERE user_username = '"+username+"'");
        if(checkUserExists.next()){
            PopUp.displayMessage("WARNING","Username already taken");
        }else{
            String password = passwordField.getText();

            String name = nameField.getText();
            String lastname = lastnameField.getText();

            boolean status = statusToggle2.isSelected();

            User user = new User(username, password,  name, lastname, status);
            DatabaseConnection.getInstance().addUser(user);
            goBack();
        }
    }

    /**
     * Switch the scene with the main view
     * @throws Exception
     */
    @FXML
    public void goBack() throws Exception {
        Stage window = (Stage) root.getScene().getWindow();
        mainCtrl.refreshDataList();
        changeScene(window, "../views/MainView.fxml", mainCtrl, 800, 700);
    }
}
