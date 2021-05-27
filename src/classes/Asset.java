package classes;

import app.Type;

/**
 * Abstract class representing an asset
 * Each assets owns a code, composed of the acronyme of its type and a number,
 * a type, a status and an availability
 */
public abstract class Asset {

    private String code;
    private Type type;
    private String status;
    private boolean availability;

    public Asset(String code, Type type, String status, boolean available) {
        this.code = code;
        this.type = type;
        this.status = status;
        this.availability = available;
    }
    public String getCode() { return this.code; }
    public Type getType() {return this.type;}
    public String getStatus() { return this.status; }

    public boolean isAvailable() { return this.availability; }
    public void showAsset() {
        System.out.println("ASSET " + this.getCode() + "\nType : "+this.getType()
                +"\nStatus :"+getStatus()+"\nAvailability :"+isAvailable());
    }

    public void setCode(String code) { this.code = code; }
    public void setType(Type type) { this.type = type; }
    public void setStatus(String status) { this.status = status; }
    public void setAvailability(boolean available) { this.availability = available; }

    /**VIRTUAL METHODS**/
    public String getBrand(){return null;}
    public String getOs(){return null;}
    public int getMemory(){return 0;}
    public int getRam(){return 0;}
    public String getSwitches(){return null;}
    public boolean getWireless(){return false;}
}