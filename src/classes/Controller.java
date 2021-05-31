package classes;

import app.Model;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public abstract class Controller {

    public Model dataModel;

    public void initModel(Model model) {
        this.dataModel = model;
    }

    public Controller(Model model) {
        initModel(model);
    }

    public void changeScene(Stage window, String view, Controller controller, int width, int height) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(view));
        loader.setController(controller);
        Parent scene = loader.load();

        scene.setStyle("-fx-font-family: \"Verdana\"");
        window.setScene(new Scene(scene, width, height));
        window.sizeToScene();
    }


}