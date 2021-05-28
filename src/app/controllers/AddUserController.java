package app.controllers;

import classes.Controller;
import app.Model;
import classes.User;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AddUserController extends Controller {

    public AddUserController(Model dataModel) {
        super(dataModel);
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

    @FXML
    public void addUser() throws Exception {
        int size = dataModel.users.getUsers().size();
        //int id = dataModel.users.getUsers().get(size - 1).getId() + 1;

        String username = usernameField.getText();
        String password = passwordField.getText();

        String name = nameField.getText();
        String lastname = lastnameField.getText();

        boolean status = statusToggle2.isSelected();

        User user = new User(username, password,  name, lastname, status);
        dataModel.users.addUser(user);
        goBack();
    }

    @FXML
    public void goBack() throws Exception {
        Stage window = (Stage) root.getScene().getWindow();
        Controller controller = new MainController(this.dataModel);
        changeScene(window, "../views/mainView.fxml", controller, 600, 700);
    }
}
