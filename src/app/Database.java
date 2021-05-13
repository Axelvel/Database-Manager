package app;

import java.util.ArrayList;
import java.util.List;

public class Database {

    private List<Asset> database = new ArrayList<Asset>();

    public void addAsset(Asset asset) {
        database.add(asset);
    }

    public void removeAsset(int code) {
        database.removeIf(asset -> (asset.getCode() == code));
    }

    public void showDatabase() {
        database.forEach(asset -> {
            asset.showAsset();
        });
    }

    public int count() {
       return database.size();
    }

    public int available() {
          int i = (int) database.stream().filter(Asset::isAvailable).count();

        return  i;
    }

    public int status() {
        int i = (int) database.stream().filter(Asset::getStatus).count();

        return i;
    }
}
