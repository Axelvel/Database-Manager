package app.controllers;

import classes.*;
import app.Model;
import database.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.sql.SQLException;
import java.net.URL;
import java.util.ResourceBundle;


public class MainController extends Controller implements Initializable {

    public MainController(Model dataModel) {
        super(dataModel);
    }

    @FXML
    private GridPane root;

    @FXML
    private Label label;

    @FXML
    private ListView<Asset> inventoryList;

    @FXML
    private ListView<User> userList;

    @FXML
    private TabPane managerPane;

    @FXML
    private Tab inventoryPane;

    @FXML
    private Tab userPane;

    @FXML
    private Button disconnectButton;

    private ObservableList<Asset> assetObservableList;
    private ObservableList<User> userObservableList;

    public void refreshDataList() {

        inventoryList.getItems().clear();
        userList.getItems().clear();

        assetObservableList = FXCollections.observableArrayList();
        assetObservableList.addAll(DatabaseConnection.getInstance().getInventory().getDatabase());
        inventoryList.setItems(assetObservableList);
        inventoryList.setCellFactory(assetListView -> new AssetCell());

        userObservableList = FXCollections.observableArrayList();
        userObservableList.addAll(DatabaseConnection.getInstance().getUsers().getUsers());
        userList.setItems(userObservableList);
        userList.setCellFactory(assetListView -> new UserCell());
        /*DatabaseConnection.getInstance().getUsers().getUsers().forEach(user -> {
            userList.getItems().add(user.getName() + user.getLastName() + " / Admin: " + user.isAdmin());
        });*/
    }

    public void refreshLabel() {
        this.label.setText("Welcome back, " + dataModel.getCurrentUser().getName() + " " + dataModel.getCurrentUser().getLastName());
    }


    @FXML
    private void goToLogin() throws Exception {

        dataModel.setCurrentUser(null);
        Stage window = (Stage) root.getScene().getWindow();
        Controller controller = new LoginController(dataModel);
        changeScene(window, "../views/loginView.fxml", controller, 300, 275);
    }

    @FXML
    private void goToAdd() throws Exception {
        Stage window = (Stage) root.getScene().getWindow();
        Controller controller = new AddAssetController(dataModel,this);
        changeScene(window, "../views/addAssetView.fxml", controller, 400, 600);
    }

    @FXML
    private void deleteAction() throws SQLException{
        int tabIndex = managerPane.getSelectionModel().getSelectedIndex();
        // 0 si "Inventory", 1 si c'est tab "Users"

        if (tabIndex == 0) {
            deleteAsset();
        }
        else {
            deleteUser();
        }
    }

    private void deleteAsset() throws SQLException {
        int index = getInventoryIndex();
        if (index != -1) {
            DatabaseConnection.getInstance().deleteAsset(DatabaseConnection.getInstance().getInventory().getDatabase().get(index));
        }
        DatabaseConnection.getInstance().refreshDatabase();
        refreshDataList();
    }


    private void deleteUser() throws SQLException {
        int index = getUserIndex();

        String username = DatabaseConnection.getInstance().getUsers().getUsers().get(index).getUsername();

        int userIndex = DatabaseConnection.getInstance().getUsers().getUserIndex(username);
        User deletedUser = DatabaseConnection.getInstance().getUsers().getUsers().get(userIndex);

        if (index != -1) {
            DatabaseConnection.getInstance().getUsers().deleteUser(username);
            DatabaseConnection.getInstance().deleteUser(deletedUser);
        }

        DatabaseConnection.getInstance().refreshDatabase();
        refreshDataList();
    }

    @FXML
    private void updateAsset() throws Exception {
        int index = getInventoryIndex();
        if (index != -1) {
            Asset asset = DatabaseConnection.getInstance().getInventory().getDatabase().get(index);
            Stage window = (Stage) root.getScene().getWindow();
            Controller controller = new UpdateAssetController(dataModel, this, asset);
            //SELON LE TYPE D'ASSET? CHAMPS DIFFERENTS
            changeScene(window, "../views/updateAssetView.fxml", controller, 400, 600);

        }
    }

    @FXML
    private void goToAddUser() throws Exception {
        Stage window = (Stage) root.getScene().getWindow();
        Controller controller = new AddUserController(dataModel,this);
        changeScene(window, "../views/addUserView.fxml", controller, 400, 400);
    }

    private int getInventoryIndex() {
        return inventoryList.getSelectionModel().getSelectedIndex();
    }

    private int getUserIndex() {
        return userList.getSelectionModel().getSelectedIndex();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refreshDataList();
        refreshLabel();
    }

}
