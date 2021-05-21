package app;

import javafx.fxml.FXML;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class Controller {

    private Users users = new Users();

    //Creating new users
    User user1 = new User(1, "abc", "pass", "Jean", "Robert", true);
    User user2 = new User(2, "xyz", "word", "Jeanne", "Roberta", true);

    private Database db = new Database();

    Computer comp1 = new Computer(1, 1,"Computer1", true,true, "Window10", 1024, 16);
    Asset asset1 = new Asset(2,1, "Asset2", true, false);


    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private ListView dataList;


    public void refreshDataList() {

        dataList.getItems().clear();

        /*
        this.users.getUsers().forEach(user -> {
            dataList.getItems().add(user.getName());
        });
        */

        this.db.getDatabase().forEach(asset -> {
            //AssetCell cell = new AssetCell();
            //cell.updateItem(asset, false);
            //dataList.getItems().add(cell);
            dataList.getItems().add(asset.getName() + " (Availability: " + asset.isAvailable() + " / Status: " + asset.getStatus() + " )");

        });


    }


    private void gotoProfile() {
        try {

            //replaceSceneContent("profile.fxml");
        } catch (Exception ex) {
            //Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @FXML
    private void getIndex() {
        System.out.println(dataList.getSelectionModel().getSelectedIndex());
    }


    boolean t = true;



    @FXML
    public void login() {



        if (t) {

            //Adding users to user list

            users.addUser(user1);
            users.addUser(user2);
            db.addAsset(comp1);
            db.addAsset(asset1);
            t=false;
        }


        refreshDataList();

        //ListCell cell = new ListCell();

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
