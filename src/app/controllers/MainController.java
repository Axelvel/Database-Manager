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
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.sql.SQLException;
import java.net.URL;
import java.util.ResourceBundle;


/**
 * Controller class for the MainView fxml file
 */
public class MainController extends Controller implements Initializable {
    private ObservableList<Asset> assetObservableList;
    private ObservableList<User> userObservableList;

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

    @FXML
    private HBox adminPane;

    /**
     * Clear all items in the inventory and users list, and
     * fill it back according to the database content
     */
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

    }

    /**
     * Set the user's name on the main label
     */
    public void refreshLabel() {
        this.label.setText("Welcome back, " + dataModel.getCurrentUser().getName() + " " +
                dataModel.getCurrentUser().getLastName());
    }

    public void hideAdminOptions() {
        if (!dataModel.getCurrentUser().isAdmin()) {
            adminPane.setVisible(false);
            userPane.setDisable(true);
        }
    }


    /**
     * Disconnect and goes back to the login view
     * @throws Exception
     */
    @FXML
    private void goToLogin() throws Exception {
        dataModel.setCurrentUser(null);
        Stage window = (Stage) root.getScene().getWindow();
        Controller controller = new LoginController(dataModel);
        changeScene(window, "../views/LoginView.fxml", controller, 300, 275);
        window.centerOnScreen();
    }

    /**
     * Switch the scene with AddAssetView
     * @throws Exception
     */
    @FXML
    private void goToAdd() throws Exception {
        Stage window = (Stage) root.getScene().getWindow();
        Controller controller = new AddAssetController(dataModel,this);
        changeScene(window, "../views/AddAssetView.fxml", controller, 400, 600);
        window.centerOnScreen();
    }

    /**
     * Checks if the user wants to deleter an asset or an user
     * @throws SQLException
     */
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

    /**
     * Delete the selected asset
     * @throws SQLException
     */
    private void deleteAsset() throws SQLException {
        int index = getInventoryIndex();
        if (index != -1) {
            DatabaseConnection.getInstance().deleteAsset(DatabaseConnection.getInstance().getInventory().getDatabase().get(index));
        }
        DatabaseConnection.getInstance().refreshDatabase();
        refreshDataList();
    }

    /**
     * Delete the selected user
     * @throws SQLException
     */
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

    /**
     * Switch the scene to UpdateAssetController in order
     * to update the selected asset
     * @throws Exception
     */
    @FXML
    private void updateAsset() throws Exception {
        int index = getInventoryIndex();
        if (index != -1) {
            Asset asset = DatabaseConnection.getInstance().getInventory().getDatabase().get(index);
            Stage window = (Stage) root.getScene().getWindow();
            Controller controller = new UpdateAssetController(dataModel, this, asset);
            //SELON LE TYPE D'ASSET? CHAMPS DIFFERENTS
            changeScene(window, "../views/UpdateAssetView.fxml", controller, 800, 800);
            window.centerOnScreen();

        }
    }

    /**
     * Switch the scene to AddUserView
     * @throws Exception
     */
    @FXML
    private void goToAddUser() throws Exception {
        Stage window = (Stage) root.getScene().getWindow();
        Controller controller = new AddUserController(dataModel,this);
        changeScene(window, "../views/AddUserView.fxml", controller, 400, 400);
        window.centerOnScreen();
    }

    /**
     * Get the index of the selected asset
     * @return index
     */
    private int getInventoryIndex() {
        return inventoryList.getSelectionModel().getSelectedIndex();
    }

    /**
     * Get the index of the selected user
     * @return index
     */
    private int getUserIndex() {
        return userList.getSelectionModel().getSelectedIndex();
    }

    /**
     * Initialize the layout when the scene is loaded
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refreshDataList();
        refreshLabel();
        hideAdminOptions();

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
