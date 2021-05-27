package app;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class UpdateAssetController extends Controller {

    private final int index;

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
    private RadioButton availabilityToggle1;

    @FXML
    private RadioButton availabilityToggle2;


    @FXML
    public void goBack() throws Exception {
        Stage window = (Stage) root.getScene().getWindow();
        Controller controller = new MainController(this.dataModel);
        changeScene(window, "mainView.fxml", controller, 600, 700);
    }

    @FXML
    public void updateAsset() throws Exception {

        int code;
        String name;
        boolean status;
        boolean available;


        name = nameField.getText();


        if (statusToggle1.isSelected()) {
            //Status: No
            status = false;
        } else {
            //Status: Yes
            status = true;
        }

        if (availabilityToggle1.isSelected()) {
            //Available : No
            available = false;
        } else {
            //Available : Yes
            available = true;
        }

        Asset asset = dataModel.database.getDatabase().get(index);

        asset.s

        if (name != null) {

            dataModel.database.addAsset(asset);
            goBack();
        }

    }
}
