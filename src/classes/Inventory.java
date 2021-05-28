package classes;

import java.util.ArrayList;
import java.util.List;

/**
 * class used in order to store the assets found in the sqlite database
 */
public class Inventory {

    private List<Asset> database = new ArrayList<Asset>();

    public List<Asset> getDatabase() {
        return this.database;
    }

    public void addAsset(Asset asset) {
        database.add(asset);
    }
    public void removeAsset(String code) {
        database.removeIf(asset -> (asset.getCode() == code));
    }
    public Asset getAsset(int index){
        return database.get(index);
    }

    public int count() {
       return database.size();
    }

    public int nbAvailable() {
          int i = (int) database.stream().filter(Asset::isAvailable).count();

        return  i;
    }

    public void clear(){
        database.clear();
    }

    public void showDatabase() {
        database.forEach(asset -> {
            asset.showAsset();
        });
    }
}
