package classes;

import classes.Asset;
import javafx.scene.control.ListCell;

public class AssetCell extends ListCell<Asset> {
    @Override
    public void updateItem(Asset asset, boolean empty) {
        super.updateItem(asset, empty);
        if (empty) {
            setText(null);
        } else {
            setText(" (Availability: " + asset.isAvailable() + " / Status: " + asset.getStatus() + " )");
        }
        setGraphic(null);
    }
}
