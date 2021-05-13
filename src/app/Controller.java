package app;

import javafx.fxml.FXML;

import javafx.scene.control.TextField;

public class Controller {

    private Users users = new Users();

    //Creating new users
    User user1 = new User(1, "abc", "pass", "Jean", "Robert", true);
    User user2 = new User(2, "xyz", "word", "Jeanne", "Roberta", true);


    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    public void login() {

        //Adding users to user list
        users.addUser(user1);
        users.addUser(user2);

        String username = usernameField.getText();

        int id = this.users.getUserId(username);

        String password = passwordField.getText();

        if (users.passwordMatch(id, password)) {
            System.out.println("Login!");
        } else {
            System.out.println("Refused");
        }


    }
}
