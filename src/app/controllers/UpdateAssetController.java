package app.controllers;

import classes.Controller;
import app.Model;
import classes.Asset;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

public class UpdateAssetController extends Controller implements Initializable {

    private Asset asset;

    public UpdateAssetController(Model dataModel, Asset asset) {
        super(dataModel);
        this.asset = asset;
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

    @FXML
    private RadioButton osToggle1;

    @FXML
    private RadioButton osToggle2;

    @FXML
    private RadioButton osToggle3;

    @FXML
    private RadioButton typeComputer;

    @FXML
    private TextField brandTextField;

    @FXML
    private TextField memoryTextField;

    @FXML
    private TextField ramTextField;


    @FXML
    public void goBack() throws Exception {
        Stage window = (Stage) root.getScene().getWindow();
        Controller controller = new MainController(this.dataModel);
        changeScene(window, "../views/mainView.fxml", controller, 600, 700);
    }

    @FXML
    public void updateAsset() throws Exception {

        String code = asset.getCode();
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

        //Computer
        String brand = brandTextField.getText();

        int memory = 0;
        if (memoryTextField.getText() != null) {
             memory = Integer.parseInt(memoryTextField.getText());
        }
        int ram = 0;
        if (ramTextField.getText() != null) {
            ram = Integer.parseInt(ramTextField.getText());
        }

        String os = null;
        if(osToggle1.isSelected()){
            os = "Windows";
        }else if(osToggle2.isSelected()){
            os = "Linux";
        }else if(osToggle3.isSelected()){
            os = "MacOS";
        }

        if (code != null) { //TODO: Type check

            asset.setAvailability(available);
            asset.setStatus(status);


            //Computer
            asset.setBrand(brand);
            asset.setOs(os);
            asset.setMemory(memory);
            asset.setRam(ram);

            goBack();
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if (asset.isAvailable()) {
            availabilityToggle1.setSelected(true);
        } else {
            availabilityToggle2.setSelected(true);
        }

        String status = asset.getStatus();

        switch (status) {
            case "Excellent" -> statusToggle1.setSelected(true);
            case "Good" -> statusToggle2.setSelected(true);
            case "Okay" -> statusToggle3.setSelected(true);
            case "Bad" -> statusToggle4.setSelected(true);
        }

        String os = asset.getOs();

        switch (os) {
            case "Window" -> osToggle1.setSelected(true);
            case "Linux" -> osToggle2.setSelected(true);
            case "MacOS" -> osToggle3.setSelected(true);
        }

        brandTextField.setText(asset.getBrand());


        String ram = String.valueOf(asset.getRam());
        String memory = String.valueOf(asset.getMemory());

        memoryTextField.setText(memory);
        ramTextField.setText(ram);

    }
}
