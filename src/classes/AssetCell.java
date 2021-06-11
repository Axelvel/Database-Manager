package classes;

import app.Type;
import classes.Asset;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

public class AssetCell extends ListCell<Asset> {
    //private FXMLLoader mLLoader;

    @FXML
    private Pane cellPane;
    @FXML
    private ImageView iconView;
    @FXML
    private Label codeLabel;
    @FXML
    private Label conditionLabel;
    @FXML
    private Label availabilityLabel;
    @FXML
    private Button detailsButton;

    private Image icon;

    @Override
    public void updateItem(Asset asset, boolean empty) {

        super.updateItem(asset, empty);
        if (empty || asset == null) {
            setText(null);
            setGraphic(null);
        } else {
            FXMLLoader mLLoader = null;
            if (mLLoader == null) {
                URL fxmlLocation = getClass().getResource("/app/views/AssetListCellView.fxml");

                mLLoader = new FXMLLoader(fxmlLocation);
                mLLoader.setController(this);
                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            codeLabel.setText(asset.getCode());
            conditionLabel.setText(asset.getStatus());

            if(asset.isAvailable()) {
                availabilityLabel.setText("Available");
            }else{
                availabilityLabel.setText("Not available");
            }
            FileInputStream inputstream = null;
            if(asset.getType() == Type.COMPUTER){
                try {
                    inputstream = new FileInputStream("src/resources/computerIcon.png");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                icon = new Image(inputstream);
            }else if(asset.getType() == Type.KEYBOARD){
                try {
                    inputstream = new FileInputStream("src/resources/keyboardIcon.png");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                icon = new Image(inputstream);
            }
            iconView.setImage(icon);
            iconView.setFitWidth(40);
            iconView.setFitHeight(40);

            setText(null);
            setGraphic(cellPane);
            }
    }
}
