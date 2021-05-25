package app;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AddAssetController extends Controller {

    public AddAssetController(Model dataModel) {
        super(dataModel);
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
    public void addAsset() throws Exception {

        String code;
        String name;
        String status;
        boolean available;

        int size;
        size = dataModel.database.getDatabase().size();
        code = dataModel.database.getDatabase().get(size - 1).getCode() + 1; //TODO : correct code incrementation

        name = nameField.getText();

        //TODO : correct status attribute
        status = "Good";
        if (statusToggle1.isSelected()) {
            //Status: No
            //status = false;
        } else {
            //Status: Yes
            //status = true;
        }

        if (availabilityToggle1.isSelected()) {
            //Available : No
            available = false;
        } else {
            //Available : Yes
            available = true;
        }

        //TODO : ALLOW DIFFERENT ASSETS TYPE
        if (name != null) {
            Asset asset = new Asset(code, Type.COMPUTER, status, available);
            dataModel.database.addAsset(asset);
            goBack();
        }

    }
}
