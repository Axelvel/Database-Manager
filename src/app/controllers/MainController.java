package app.controllers;

import classes.Controller;
import app.Model;
import classes.Asset;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
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
    private ListView dataList;

    @FXML
    private Button disconnectButton;

    public void refreshDataList(){
        dataList.getItems().clear();
        this.dataModel.getInventory().getDatabase().forEach(asset -> {
            dataList.getItems().add(asset.getCode() + " (Availability: " + asset.isAvailable() + " / Status: " + asset.getStatus() + " )");

        });
    }


    @FXML
    private void goToLogin() throws Exception {
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
        int index = dataList.getSelectionModel().getSelectedIndex();
        return index;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Task<Void> task = new Task<>() {
            @Override public Void call() throws SQLException {
                refreshDataList();
                return null;
            }
        };

        new Thread(task).start();
    }
}
