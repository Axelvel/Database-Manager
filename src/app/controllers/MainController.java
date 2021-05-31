package app.controllers;

import classes.Controller;
import app.Model;
import classes.Asset;
import javafx.concurrent.Task;
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
    private ListView inventoryList;

    @FXML
    private ListView userList;

    @FXML
    private TabPane managerPane;

    @FXML
    private Tab inventoryPane;

    @FXML
    private Tab userPane;

    @FXML
    private Button disconnectButton;

    public void refreshDataList(){
        inventoryList.getItems().clear();
        userList.getItems().clear();

        this.dataModel.getInventory().getDatabase().forEach(asset -> {
            inventoryList.getItems().add(asset.getCode() + " (Availability: " + asset.isAvailable() + " / Status: " + asset.getStatus() + " )");

        });

        this.dataModel.getUsers().getUsers().forEach(user -> {
            userList.getItems().add(user.getName() + user.getLastName() + " / Admin: " + user.isAdmin());

        });
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
        Controller controller = new AddAssetController(dataModel);
        changeScene(window, "../views/addAssetView.fxml", controller, 400, 600);
    }

    @FXML
    private void deleteAsset() throws SQLException {
        int index = getIndex();
        if (index != -1) {
            this.dataModel.getDb().deleteAsset(this.dataModel.getInventory().getDatabase().get(index));
            dataModel.getDb().refreshDatabase();
            refreshDataList();
        }
    }

    @FXML
    private void updateAsset() throws Exception {
        int index = getIndex();
        if (index != -1) {
            Asset asset = dataModel.getInventory().getDatabase().get(index);
            Stage window = (Stage) root.getScene().getWindow();
            Controller controller = new UpdateAssetController(dataModel, asset);
            changeScene(window, "../views/updateAssetView.fxml", controller, 400, 600);
        }
    }

    @FXML
    private void goToAddUser() throws Exception {
        Stage window = (Stage) root.getScene().getWindow();
        Controller controller = new AddUserController(dataModel);
        changeScene(window, "../views/addUserView.fxml", controller, 400, 400);
    }

    @FXML
    private int getIndex() {
        return inventoryList.getSelectionModel().getSelectedIndex();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Task<Void> task = new Task<>() {
            @Override public Void call() throws SQLException {
                refreshDataList();
                refreshLabel();
                return null;
            }
        };

        new Thread(task).start();

    }
}
