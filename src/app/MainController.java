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
            dataList.getItems().add(asset.getName() + " (Availability: " + asset.isAvailable() + " / Status: " + asset.getStatus() + " )");
        });
    }


    @FXML
    private void goToLogin() throws Exception {
        Stage window = (Stage) root.getScene().getWindow();
        Controller controller = new LoginController(dataModel);
        changeScene(window, "loginView.fxml", controller, 300, 275);
    }


    @FXML
    private void getIndex() {
        System.out.println(dataList.getSelectionModel().getSelectedIndex());
    }

}
