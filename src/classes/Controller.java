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
    }       // Binds a model to the controller

    public Controller(Model model) {
        initModel(model);
    }

    /**
     * Rapidly change scene and size
     * @param window window which will display the content
     * @param view name of the fxml view
     * @param controller associated controller
     * @param width desired width
     * @param height desired height
     * @throws Exception
     */
    public void changeScene(Stage window, String view, Controller controller, int width, int height) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(view));
        loader.setController(controller);
        Parent scene = loader.load();

        scene.setStyle("-fx-font-family: \"Verdana\"");
        window.setScene(new Scene(scene, width, height));
        window.sizeToScene();
        window.centerOnScreen();
    }


}