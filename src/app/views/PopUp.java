package app.views;

import app.Type;
import classes.Asset;
import classes.User;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Pop up window that show whenever the application has to give an information to the user
 */
public class PopUp {

    private static Stage initialize() throws FileNotFoundException {
        Stage popupwindow = new Stage();
        popupwindow.initModality(Modality.APPLICATION_MODAL);
        FileInputStream inputstream = new FileInputStream("src/resources/infoIcon.png");
        Image popupIcon = new Image(inputstream);
        popupwindow.getIcons().add(popupIcon);
        return popupwindow;
    }

    public static void displayMessage(String infoType, String text) throws IOException {
        Stage popupwindow = initialize();
        popupwindow.setTitle(infoType);

        Label message= new Label(text);

        Button closeButton= new Button("Close");
        closeButton.setOnAction(e -> popupwindow.close());

        VBox layout= new VBox(10);
        layout.getChildren().addAll(message, closeButton);
        layout.setAlignment(Pos.CENTER);
        Scene scene= new Scene(layout, 300, 150);
        popupwindow.setScene(scene);
        popupwindow.showAndWait();
    }

    public static void displayAssetDetails(Asset asset) throws IOException {
        Stage popupwindow = initialize();
        popupwindow.setTitle("ASSET DETAILS");

        Label title = new Label("ASSET INFORMATIONS");
        title.setFont(new Font("Arial", 40));

        TextFlow content = new TextFlow();

        Text codeTitle=new Text("Code : ");
        codeTitle.setStyle("-fx-font-weight: bold");
        Text code=new Text(asset.getCode()+"\n");
        code.setStyle("-fx-font-weight: regular");

        Text typeTitle=new Text("Type  : ");
        typeTitle.setStyle("-fx-font-weight: bold");
        Text type=new Text(asset.getType()+"\n");
        type.setStyle("-fx-font-weight: regular");

        Text statusTitle=new Text("Status : ");
        statusTitle.setStyle("-fx-font-weight: bold");
        Text status=new Text(asset.getStatus()+"\n");
        status.setStyle("-fx-font-weight: regular");

        Text availabilityTitle=new Text("Availability : ");
        availabilityTitle.setStyle("-fx-font-weight: bold");
        Text availability=new Text(asset.isAvailable()+"\n");
        availability.setStyle("-fx-font-weight: regular");

        Text brandTitle=new Text("Brand : ");
        brandTitle.setStyle("-fx-font-weight: bold");
        Text brand=new Text(asset.getBrand()+"\n");
        brand.setStyle("-fx-font-weight: regular");

        content.getChildren().addAll(codeTitle,code,typeTitle,type,statusTitle,status,availabilityTitle,availability,brandTitle,brand);

        if(asset.getType() == Type.COMPUTER){
            Text osTitle=new Text("Os : ");
            osTitle.setStyle("-fx-font-weight: bold");
            Text os=new Text(asset.getOs()+"\n");
            os.setStyle("-fx-font-weight: regular");

            Text memoryTitle=new Text("Memory : ");
            memoryTitle.setStyle("-fx-font-weight: bold");
            Text memory=new Text(asset.getMemory()+"\n");
            memory.setStyle("-fx-font-weight: regular");

            Text ramTitle=new Text("Ram : ");
            ramTitle.setStyle("-fx-font-weight: bold");
            Text ram=new Text(asset.getRam()+"\n");
            ram.setStyle("-fx-font-weight: regular");

            content.getChildren().addAll(osTitle,os,memoryTitle,memory,ramTitle,ram);
        }else if(asset.getType() == Type.KEYBOARD){
            Text wirelessTitle=new Text("Wireless : ");
            wirelessTitle.setStyle("-fx-font-weight: bold");
            Text wireless=new Text(asset.getWireless()+"\n");
            wireless.setStyle("-fx-font-weight: regular");

            Text switchesTitle=new Text("Switches : ");
            switchesTitle.setStyle("-fx-font-weight: bold");
            Text switches=new Text(asset.getSwitches()+"\n");
            switches.setStyle("-fx-font-weight: regular");

            content.getChildren().addAll(wirelessTitle,wireless,switchesTitle,switches);
        }

        content.setTextAlignment(TextAlignment.CENTER);
        Button closeButton= new Button("Close");
        closeButton.setOnAction(e -> popupwindow.close());

        VBox layout= new VBox(10);
        layout.getChildren().addAll(content,closeButton);
        layout.setAlignment(Pos.CENTER);
        Scene scene= new Scene(layout, 300, 250);
        popupwindow.setScene(scene);
        popupwindow.showAndWait();


    }

    public static void displayUserDetails(User user) throws IOException{
        Stage popupwindow = initialize();
        popupwindow.setTitle("USER DETAILS");
        Label title = new Label("USER DETAILS");
        title.setFont(new Font("Arial", 40));

        TextFlow content = new TextFlow();

        if(user.isAdmin()){
            Label admin=new Label("ADMIN");
            admin.setFont(new Font("Arial", 20));
            admin.setTextFill(Color.RED);
            content.getChildren().add(admin);
        }

        Text usernameTitle=new Text("\nUsername : ");
        usernameTitle.setStyle("-fx-font-weight: bold");
        Text username=new Text(user.getUsername()+"\n");
        username.setStyle("-fx-font-weight: regular");

        Text nameTitle=new Text("Last name  : ");
        nameTitle.setStyle("-fx-font-weight: bold");
        Text name=new Text(user.getLastName()+"\n");
        name.setStyle("-fx-font-weight: regular");

        Text surnameTitle=new Text("Surname  : ");
        surnameTitle.setStyle("-fx-font-weight: bold");
        Text surname=new Text(user.getName()+"\n");
        surname.setStyle("-fx-font-weight: regular");

        content.getChildren().addAll(usernameTitle,username,nameTitle,name,surnameTitle,surname);
        content.setTextAlignment(TextAlignment.CENTER);
        Button closeButton= new Button("Close");
        closeButton.setOnAction(e -> popupwindow.close());

        VBox layout= new VBox(10);
        layout.getChildren().addAll(content,closeButton);
        layout.setAlignment(Pos.CENTER);
        Scene scene= new Scene(layout, 300, 250);
        popupwindow.setScene(scene);
        popupwindow.showAndWait();
    }

}
