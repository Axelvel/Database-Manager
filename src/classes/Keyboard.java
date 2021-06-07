package classes;

import app.Type;

public class Keyboard extends Asset{

    private String brand;
    private boolean wireless;
    private String switches;

    public Keyboard(String code, Type type, String status, boolean available, String brand,
                    boolean wireless, String switches){
        super(code,type,status,available);
        this.brand = brand;
        this.wireless = wireless;
        this.switches = switches;
    }

    @Override
    public void showAsset() {
        super.showAsset();
        System.out.println("\nCharacteristics :\nBrand : " + getBrand() + "\nWireless : " + getWireless() + "\nSwitches : "
                + getSwitches());
    }

    @Override
    public String getBrand(){return this.brand;}
    public String getSwitches(){return this.switches;}
    public boolean getWireless(){return this.wireless;}
    public void setSwitches(String s){ switches = s; }
    public void setWireless(boolean w){ wireless = w;}
}
