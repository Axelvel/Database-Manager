package app;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class UpdateAssetController extends Controller {

    private int index;

    public UpdateAssetController(Model dataModel, int index) {
        super(dataModel);
        this.index = index;

    }

    @FXML
    private GridPane root;

    @FXML
    private TextField nameField;

    @FXML
    private RadioButton statusToggle1;

    @FXML
    private RadioButton statusToggle2;

    @FXML
    private RadioButton statusToggle3;

    @FXML
    private RadioButton statusToggle4;

    @FXML
    private RadioButton availabilityToggle1;

    @FXML
    private RadioButton availabilityToggle2;

    public UpdateAssetController(Model dataModel) {
        super(dataModel);
    }


    @FXML
    public void goBack() throws Exception {
        Stage window = (Stage) root.getScene().getWindow();
        Controller controller = new MainController(this.dataModel);
        changeScene(window, "mainView.fxml", controller, 600, 700);
    }

    @FXML
    public void updateAsset() throws Exception {

        String code = dataModel.database.getDatabase().get(index).getCode();
        String status = null;


        if (statusToggle1.isSelected()) {
            status = "Excellent";
        } else if (statusToggle2.isSelected()){
            status = "Good";
        } else if (statusToggle3.isSelected()) {
            status = "Okay";
        } else if (statusToggle4.isSelected()){
            status = "Bad";
        }
        boolean available = availabilityToggle1.isSelected();


        //available = !availabilityToggle1.isSelected();



        if (code != null) {

            Asset asset = dataModel.database.getDatabase().get(index);

            asset.setAvailable(available);
            asset.setStatus(status);

            goBack();
        }

    }
}
