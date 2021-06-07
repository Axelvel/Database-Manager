package app.controllers;

import app.Type;
import classes.Controller;
import app.Model;
import classes.Asset;
import database.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.fxml.Initializable;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UpdateAssetController extends Controller implements Initializable {

    private Asset asset;
    private MainController mainCtrl;

    public UpdateAssetController(Model dataModel, MainController mainCtrl, Asset asset) {
        super(dataModel);
        this.mainCtrl = mainCtrl;
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
    private RadioButton wireless;

    @FXML
    private RadioButton notWireless;

    @FXML
    private RadioButton switchesMechanical;

    @FXML
    private RadioButton switchesMembrane;

    @FXML
    private RadioButton switchesRubber;

    @FXML
    private RadioButton typeComputer;

    @FXML
    private TextField brandTextField;

    @FXML
    private TextField memoryTextField;

    @FXML
    private TextField ramTextField;

    @FXML
    private VBox computerAttributes;

    @FXML
    private VBox keyboardAttributes;

    @FXML
    private ToggleGroup statusGroup;

    @FXML
    private ToggleGroup osGroup;

    @FXML
    private ToggleGroup switchesGroup;


    @FXML
    public void goBack() throws Exception {
        Stage window = (Stage) root.getScene().getWindow();
        Controller controller = new MainController(this.dataModel);
        changeScene(window, "../views/mainView.fxml", controller, 600, 700);
    }

    @FXML
    public void updateAsset() throws Exception {

        RadioButton selectedRadioButton = (RadioButton) statusGroup.getSelectedToggle();
        String status = selectedRadioButton.getText();
        asset.setStatus(selectedRadioButton.getText());
        asset.setAvailability(availabilityToggle1.isSelected());

        if(asset.getType() == Type.COMPUTER) {
            asset.setBrand(brandTextField.getText());

            selectedRadioButton = (RadioButton) osGroup.getSelectedToggle();
            String os = selectedRadioButton.getText();
            asset.setOs(selectedRadioButton.getText());

            if (memoryTextField.getText() != null) asset.setMemory(Integer.parseInt(memoryTextField.getText()));
            if (ramTextField.getText() != null) asset.setRam(Integer.parseInt(ramTextField.getText()));

        }else if(asset.getType() == Type.KEYBOARD){

            selectedRadioButton = (RadioButton) switchesGroup.getSelectedToggle();
            asset.setSwitches(selectedRadioButton.getText());
            asset.setWireless(wireless.isSelected());

        }

        DatabaseConnection.getInstance().updateAsset(asset);
        goBack();

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if (asset.isAvailable()) availabilityToggle1.setSelected(true);

        switch (asset.getStatus()) {
            case "Excellent" -> statusToggle1.setSelected(true);
            case "Good" -> statusToggle2.setSelected(true);
            case "Okay" -> statusToggle3.setSelected(true);
            case "Bad" -> statusToggle4.setSelected(true);
        }

        brandTextField.setText(asset.getBrand());

        if(asset.getType() == Type.COMPUTER){
            switchComputersAttribute();

            switch (asset.getOs()) {
                case "Window" -> osToggle1.setSelected(true);
                case "Linux" -> osToggle2.setSelected(true);
                case "MacOS" -> osToggle3.setSelected(true);
            }
            String ram = String.valueOf(asset.getRam());
            String memory = String.valueOf(asset.getMemory());

            memoryTextField.setText(memory);
            ramTextField.setText(ram);

        }else if(asset.getType() == Type.KEYBOARD){
            switchKeyboardsAttribute();

            if(asset.getWireless()) wireless.setSelected(true);

            switch (asset.getSwitches()) {
                case "Mechanical" -> switchesMechanical.setSelected(true);
                case "Membrane" -> switchesMembrane.setSelected(true);
                case "Rubber domes" -> switchesRubber.setSelected(true);
            }
        }


    }

    public void switchComputersAttribute(){
        computerAttributes.setVisible(true);
        computerAttributes.toFront();
    }

    public void switchKeyboardsAttribute(){
        keyboardAttributes.setVisible(true);
        keyboardAttributes.toFront();
    }
}
