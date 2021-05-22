package app;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


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
        changeScene(window, "mainView.fxml", controller, 600, 700);
    }

    @FXML
    public void login() throws Exception {

        String username = usernameField.getText();
        int id = this.dataModel.users.getUserId(username);
        String password = passwordField.getText();

        if (dataModel.users.passwordMatch(id, password)) {
            System.out.println("Login!");
            gotoProfile();
        } else {
            System.out.println("Refused");
        }
    }

}
