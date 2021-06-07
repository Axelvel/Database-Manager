package app.controllers;

import classes.Controller;
import app.Model;
import app.Type;
import classes.Computer;
import classes.Keyboard;
import database.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.ResultSet;

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
    public void goBack() throws Exception {
        Stage window = (Stage) root.getScene().getWindow();
        Controller controller = new MainController(this.dataModel);
        changeScene(window, "../views/mainView.fxml", controller, 600, 700);
    }

    @FXML
    public void addAsset() throws Exception {
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

        if(typeComputer.isSelected()){

            //count the number of computers in the database
            int nbComputers = DatabaseConnection.getInstance().count("computers_table") + 1;
            String code = "COMP" + nbComputers;

            String os = null;
            if(osToggle1.isSelected()){
                os = "Windows";
            }else if(osToggle2.isSelected()){
                os = "Linux";
            }else if(osToggle3.isSelected()){
                os = "MacOS";
            }

            Computer c = new Computer(code, Type.COMPUTER,status,true,brandTextField.getText(),os,
                    Integer.parseInt(memoryTextField.getText()),Integer.parseInt(ramTextField.getText()));

            DatabaseConnection.getInstance().addAsset(c);
            DatabaseConnection.getInstance().refreshDatabase();
            goBack();
        } else if(typeKeyboard.isSelected()){
            int nbComputers = DatabaseConnection.getInstance().count("keyboards_table") + 1;
            String code = "KEYB" + nbComputers;

            String switches = null;
            if(switchesMechanical.isSelected()){
                switches = "Mechanical";
            } else if(switchesMembrane.isSelected()){
                switches = "Membrane";
            } else if(switchesRubber.isSelected()){
                switches = "Rubber Domes";
            }

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
