package app;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class MainController extends Controller{

    public MainController(Model dataModel) {
        super(dataModel);
        //TODO: Refresh dataList when scene is loaded
    }


    @FXML
    private GridPane root;

    @FXML
    private ListView dataList;

    @FXML
    private Button disconnectButton;

    public void refreshDataList() {
        dataList.getItems().clear();
        this.dataModel.database.getDatabase().forEach(asset -> {
            //dataList.getItems().add(asset.getName() + " (Availability: " + asset.isAvailable() + " / Status: " + asset.getStatus() + " )");
            dataList.getItems().add(asset.getCode() + " (Availability: " + asset.isAvailable() + " / Status: " + asset.getStatus() + " )");
        });
    }


    @FXML
    private void goToLogin() throws Exception {
        Stage window = (Stage) root.getScene().getWindow();
        Controller controller = new LoginController(dataModel);
        changeScene(window, "loginView.fxml", controller, 300, 275);
    }

    @FXML
    private void goToAdd() throws Exception {
        Stage window = (Stage) root.getScene().getWindow();
        Controller controller = new AddAssetController(dataModel);
        changeScene(window, "addAssetView.fxml", controller, 300, 275);
    }

    @FXML
    private void deleteAsset() {
        int index = getIndex();
        if (index != -1) {
            String code = this.dataModel.database.getDatabase().get(index).getCode();
            this.dataModel.database.removeAsset(code);
            refreshDataList();
        }
    }

    @FXML
    private void goToAddUser() throws Exception {
        Stage window = (Stage) root.getScene().getWindow();
        Controller controller = new AddUserController(dataModel);
        changeScene(window, "addUserView.fxml", controller, 400, 400);
    }



    @FXML
    private int getIndex() {
        int index = dataList.getSelectionModel().getSelectedIndex();
        System.out.println(index);
        return index;
    }

}
