package app.controllers;

import app.Main;
import classes.Controller;
import app.Model;
import app.Type;
import classes.Computer;
import classes.Keyboard;
import database.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.util.Enumeration;

public class AddAssetController extends Controller {

    private MainController mainCtrl;

    public AddAssetController(Model dataModel, MainController mainCtrl) {
        super(dataModel);
        this.mainCtrl = mainCtrl;
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
    private RadioButton osToggle1;

    @FXML
    private RadioButton osToggle2;

    @FXML
    private RadioButton osToggle3;

    @FXML
    private RadioButton wireless;

    @FXML
    private RadioButton switchesMechanical;

    @FXML
    private RadioButton switchesMembrane;

    @FXML
    private RadioButton switchesRubber;

    @FXML
    private RadioButton typeComputer;

    @FXML
    private RadioButton typeKeyboard;

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
    private StackPane attributesStack;

    @FXML
    private ToggleGroup statusGroup;

    @FXML
    private ToggleGroup osGroup;

    @FXML
    private ToggleGroup switchesGroup;

    @FXML
    public void goBack() throws Exception {
        Stage window = (Stage) root.getScene().getWindow();
        //Controller controller = new MainController(this.dataModel);
        mainCtrl.refreshDataList();
        changeScene(window, "../views/mainView.fxml", mainCtrl, 600, 700);
    }

    @FXML
    public void addAsset() throws Exception {
        RadioButton selectedRadioButton = (RadioButton) statusGroup.getSelectedToggle();
        String status = selectedRadioButton.getText();

        if(typeComputer.isSelected()){

            //count the number of computers in the database
            int nbComputers = DatabaseConnection.getInstance().count("computers_table") + 1;
            String code = "COMP" + nbComputers;

            selectedRadioButton = (RadioButton) osGroup.getSelectedToggle();
            String os = selectedRadioButton.getText();

            Computer c = new Computer(code, Type.COMPUTER,status,true,brandTextField.getText(),os,
                    Integer.parseInt(memoryTextField.getText()),Integer.parseInt(ramTextField.getText()));

            DatabaseConnection.getInstance().addAsset(c);
            //DatabaseConnection.getInstance().refreshDatabase();
            goBack();

        } else if(typeKeyboard.isSelected()){
            int nbComputers = DatabaseConnection.getInstance().count("keyboards_table") + 1;
            String code = "KEYB" + nbComputers;

            selectedRadioButton = (RadioButton) switchesGroup.getSelectedToggle();
            String switches = selectedRadioButton.getText();

            boolean isWireless = false;
            if(wireless.isSelected()){
                isWireless = true;
            }

            Keyboard k = new Keyboard(code,Type.KEYBOARD,status,true,brandTextField.getText(),isWireless,switches);

            DatabaseConnection.getInstance().addAsset(k);
            goBack();
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
