package classes;

import app.views.PopUp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;


/**
 * Custom user cell, used for the cell factory of the ListCell in the MainView
 */
public class UserCell extends ListCell<User> {

    private User user;
    @FXML
    private Pane cellPane;
    @FXML
    private ImageView iconView;
    @FXML
    private Label usernameLabel;
    @FXML
    private Button userDetailsButton;

    @Override
    public void updateItem(User user, boolean empty) {

        super.updateItem(user, empty);
        this.user = user;
        if (empty || user == null) {
            setText(null);
            setGraphic(null);
        } else {
            FXMLLoader mLLoader = null;
            if (mLLoader == null) {
                URL fxmlLocation = getClass().getResource("/app/views/UserListCellView.fxml");

                mLLoader = new FXMLLoader(fxmlLocation);
                mLLoader.setController(this);
                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            usernameLabel.setText(user.getUsername());
            Image icon;
            FileInputStream inputstream = null;
            if(user.isAdmin()){
                try {
                    inputstream = new FileInputStream("src/resources/adminIcon.png");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }else{
                try {
                    inputstream = new FileInputStream("src/resources/userIcon.png");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            icon = new Image(inputstream);

            iconView.setImage(icon);
            iconView.setFitWidth(40);
            iconView.setFitHeight(40);
            
            setText(null);
            setGraphic(cellPane);
        }
    }

    /**
     * Open a popup containing all the user information
     * @throws IOException
     */
    @FXML
    public void showUserDetails() throws IOException {
        PopUp.displayUserDetails(user);
    }

}
