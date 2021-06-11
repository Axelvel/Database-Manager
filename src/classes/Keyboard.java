package classes;

import app.Type;

/**
 * Keyboard class
 * Each keyboard can be wireless or not, and has different types of switches (membranes, mechanical and rubber domes)
 */
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

    public String toString(){
        return super.toString() + "\nCharacteristics :\nBrand : " + getBrand() + "\nWireless : " + getWireless() + "\nSwitches : "
                + getSwitches();
    }

    @Override
    public String getBrand(){return this.brand;}
    public String getSwitches(){return this.switches;}
    public boolean getWireless(){return this.wireless;}
    public void setSwitches(String s){ switches = s; }
    public void setWireless(boolean w){ wireless = w;}
}
